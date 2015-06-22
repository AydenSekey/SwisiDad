/*
Copyright Adrien Duroy (2015)

ad.duroy@gmail.com

This file is part of SwisiDad.

SwisiDad is free software: you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

SwisiDad is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public License
along with SwisiDad.  If not, see <http://www.gnu.org/licenses/>.
*/
package swisidad.manager;

import java.util.HashSet;
import java.util.Set;

import swisidad.Coordinate;
import swisidad.component.SwisiComponent;
import swisidad.component.SwisiContainer;
import swisidad.component.SwisiGlassPan;
import swisidad.component.SwisiTarget;
import swisidad.component.SwisiDraggable;
import swisidad.event.SwisiMouseEvent;
import swisidad.listener.SwisiMouseListener;
import swisidad.manager.exception.ConcurrentDragComponentException;

/**
 * Manager gérant le drag and drop.
 */
public class SwisiDadManager implements SwisiMouseListener {
	private Set<SwisiTarget> targets;
	private SwisiGlassPan glassPan;
	private SwisiDraggable draggable;
	private SwisiDraggable graphicalCopy;
	private Coordinate draggableMouseOriginePosGlassPan;
	
	public SwisiDadManager() {
		targets = new HashSet<>();
		draggable = null;
		graphicalCopy = null;
		draggableMouseOriginePosGlassPan = null;
	}
	
	/**
	 * Demande à gérer un nouveau composant draggable.
	 * 
	 * @param managed le composant à gérer.
	 */
	public void manage(SwisiDraggable managed) {
		managed.addMouseListener(this);
	}
	
	/**
	 * Ajoute une nouvelle cible possible pour le drop.
	 * 
	 * @param target la nouvelle cible.
	 */
	public void addTarget(SwisiTarget target) {
		targets.add(target);
	}

	@Override
	public void mousePressed(SwisiMouseEvent event) {
		SwisiComponent component = event.getSource();
		if(component instanceof SwisiDraggable) {
			pick((SwisiDraggable) component, event.getMousePosition());
		}
	}


	@Override
	public void mouseRelease(SwisiMouseEvent event) {
		drop();
	}

	@Override
	public void mouseDragged(SwisiMouseEvent event) {
		drag(event.getMousePosition());
	}
	
	public SwisiGlassPan getGlassPan() {
		return glassPan;
	}
	
	public void setGlassPan(SwisiGlassPan glass) {
		glassPan = glass;
	}
	
	/**
	 * Déplace le composant en cours de drag.
	 * @param mousePosition la position de la souris par rapport au composant détectant le drag.
	 */
	private void drag(Coordinate mousePosition) {
		if(graphicalCopy == null)
			throw new NullPointerException("La copie graphique ne devrait pas être null pendant le drag.");
		if(mousePosition == null)
			throw new NullPointerException("La position de la souris devrait être fournie dans l'événement");
		Coordinate newCoord = Coordinate.moveFrom(draggableMouseOriginePosGlassPan, mousePosition);  
		graphicalCopy.moveTo(newCoord.getX(), newCoord.getY());
	}

	/**
	 * Trouve la cible actuellement survolée.
	 * 
	 * @return la cible survolée ou <code>null</code> si aucune n'est survolée.
	 */
	private SwisiTarget findTargetSurvole() {
		return null;
	}

	/**
	 * Démarrage du drag d'un composant.
	 * 
	 * @param component le composant à dragguer.
	 * @param mouseClicPos la position de la souris par rapport au composant au démarrage du drag.
	 */
	private void pick(final SwisiDraggable component, final Coordinate mouseClicPos) {
		// Vérification que l'on est pas déjà en train de dragguer un composant.
		if(draggable != null) {
			throw new ConcurrentDragComponentException();
		}
		// Obtention d'une copie graphique
		graphicalCopy = component.graphicalCopy();
		if(graphicalCopy == null) {
			throw new NullPointerException("Graphical copy must not be null !");
		}
		// Mémoriser le composant à dragguer
		draggable = component;
		// Affichage du GlassPan maintenant pour pouvoir obtenir sa position à l'écran
		glassPan.setVisible(true);
		// Calcul des coordonnées du composant à dragguer par rapport au glassPan
		Coordinate coord = coordinateRelativeToGlassPan(draggable);
		int xOrigine = coord.getX() - mouseClicPos.getX();
		int yOrigine = coord.getY() - mouseClicPos.getY();
		draggableMouseOriginePosGlassPan = new Coordinate(xOrigine, yOrigine);
		// Placer la copy sur le GlassPan
		graphicalCopy.moveTo(coord.getX(), coord.getY());
		glassPan.addSwisiComponent(graphicalCopy);
		graphicalCopy.setVisible(true);
	}
	
	private Coordinate coordinateRelativeToGlassPan(SwisiComponent component) {
		Coordinate coord = component.getSwisiPositionOnScreen();
		Coordinate coordGlassPan = glassPan.getSwisiPositionOnScreen();
		return Coordinate.relative(coordGlassPan, coord);
	}

	/**
	 * Effectue le drop du composant en cours de drag.
	 */
	private void drop() {
		// Drop s'il y a un composant à dropper
		if(draggable != null) {
			// Obtention de la cible survolée
			SwisiTarget target = findTargetSurvole();
			if(target != null) {
				// S'il existe une cible survolée
				pushToTarget(target);
			}
			// Supprimer la copy du glassPan
			glassPan.removeSwisiComponent(graphicalCopy);
			// Cacher le GlassPan
			glassPan.setVisible(false);
			// Rendre le vrai composant de nouveau visible
			draggable.setVisible(true);
			finalizeDrop();
		}
	}
	
	/**
	 * Effectue le nettoyage du contexte du manager en fin de drop.
	 */
	private void finalizeDrop() {
		// Destruction de la copie graphique
		graphicalCopy = null;
		// Rendre le manager de nouveau prêt à dragguer un nouveau composant
		draggable = null;
		// Destruction des coordonnées relative du composant draggable par rapport au GlassPan
		draggableMouseOriginePosGlassPan = null;
	}
	
	/**
	 * Demande à une cible la reception du composant draggué.
	 * @param target la cible.
	 */
	private void pushToTarget(final SwisiTarget target) {
		// On récupère l'ancien conteneur possèdant le composant draggué
		SwisiContainer oldContainer = draggable.getSwisiContainer();
		// On demande à la cible trouvée la réception du composant draggué
		if(target.receive(draggable)) {
			// Si la cible à bien réceptionné le composant, on supprime ce dernier de son ancien conteneur
			oldContainer.removeSwisiComponent(draggable);
		}
	}
}
