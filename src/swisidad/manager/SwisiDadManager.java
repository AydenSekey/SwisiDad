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

import swisidad.Coordinates;
import swisidad.SwisiRectangle;
import swisidad.component.SwisiComponent;
import swisidad.component.SwisiContainer;
import swisidad.component.SwisiGlassPan;
import swisidad.component.SwisiTarget;
import swisidad.component.SwisiDraggable;
import swisidad.event.SwisiMouseEvent;
import swisidad.listener.SwisiMouseListener;
import swisidad.manager.exception.ConcurrentDragComponentException;
import swisidad.manager.strategies.SwisiButtonStrategy;
import swisidad.manager.strategies.SwisiLeftButtonStrategy;
import swisidad.mouse.SwisiMouseButton;

/**
 * Manager gérant le drag and drop.
 */
public class SwisiDadManager implements SwisiMouseListener {
	private Set<SwisiTarget> targets;
	private SwisiGlassPan glassPan;
	private SwisiDraggable draggable;
	private SwisiDraggable graphicalCopy;
	private Coordinates draggableMouseOriginePosGlassPan;
	/* strategies */
	private SwisiButtonStrategy buttonStrategy;
	
	/**
	 * Crée un gestionnaire de drag and drop.<br>
	 * Le drag and drop sera effecter uniquement avec le bouton gauche de la souris.
	 */
	public SwisiDadManager() {
		this(new SwisiLeftButtonStrategy());
	}
	
	/**
	 * Crée un gestionnaire de drag and drop.
	 * 
	 * @param buttonStrategy stratégie définissant les boutons autorisé pour effectuer le drag and drop. Ne doit pas être null.
	 * @throws NullPointerException if buttonStrategy is <code>null</code>.
	 */
	public SwisiDadManager(SwisiButtonStrategy buttonStrategy) {
		if(buttonStrategy == null)
			throw new NullPointerException("buttonStragegy can't be null");
		targets = new HashSet<>();
		draggable = null;
		graphicalCopy = null;
		draggableMouseOriginePosGlassPan = null;
		this.buttonStrategy = buttonStrategy;
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
		if(isValideButton(event.getButton()) && component instanceof SwisiDraggable) {
			pick((SwisiDraggable) component, event.getMousePosition());
		}
	}

	@Override
	public void mouseRelease(SwisiMouseEvent event) {
		if(isValideButton(event.getButton())) {
			drop();
		}
	}

	@Override
	public void mouseDragged(SwisiMouseEvent event) {
		if(draggable != null) {
			drag(event.getMousePosition());
		}
	}
	
	public SwisiGlassPan getGlassPan() {
		return glassPan;
	}
	
	public void setGlassPan(SwisiGlassPan glass) {
		glassPan = glass;
	}

	private boolean isValideButton(SwisiMouseButton button) {
		return buttonStrategy.valideButton(button);
	}
	
	/**
	 * Déplace le composant en cours de drag.
	 * @param mousePosition la position de la souris par rapport au composant détectant le drag.
	 */
	private void drag(Coordinates mousePosition) {
		if(graphicalCopy == null)
			throw new NullPointerException("La copie graphique ne devrait pas être null pendant le drag.");
		if(mousePosition == null)
			throw new NullPointerException("La position de la souris devrait être fournie dans l'événement");
		Coordinates newCoord = Coordinates.add(draggableMouseOriginePosGlassPan, mousePosition);  
		graphicalCopy.moveTo(newCoord.getX(), newCoord.getY());
	}

	/**
	 * Trouve la cible actuellement survolée.
	 * 
	 * @return la cible survolée ou <code>null</code> si aucune n'est survolée.
	 */
	private SwisiTarget findTargetSurvole() {
		SwisiTarget targetFind = null;
		long intersectFind = 0;
		for(SwisiTarget target : targets) {
			// Eliminer le cas du composant draggué aussi cible
			if(target != draggable) {
				// Calculer l'intersection
				long intersect = intersect(graphicalCopy, target);
				if(intersect > intersectFind) {
					// On conserve celui qui à la plus grosse intersection
					intersectFind = intersect;
					targetFind = target;
				}
			}
		}
		return targetFind;
	}
	
	/**
	 * Test l'instersection de 2 composants.
	 * 
	 * @param component1 le premier composant
	 * @param component2 le deuxième composant
	 * @return une valeur positive correspondant à l'aire d'intersection, 0 s'il n'y a pas d'intersection.
	 */
	private long intersect(SwisiComponent component1, SwisiComponent component2) {
		// Récupération des coordonnées absolues
		Coordinates posCompo1 = component1.getSwisiPositionOnScreen();
		Coordinates posCompo2 = component2.getSwisiPositionOnScreen();
		if(posCompo1 == null || posCompo2 == null) {
			return 0;
		}
		SwisiRectangle rectCompo1 = new SwisiRectangle(posCompo1, component1.getWidth(), component1.getHeight());
		SwisiRectangle rectCompo2 = new SwisiRectangle(posCompo2, component2.getWidth(), component2.getHeight());
		SwisiRectangle intersection = SwisiRectangle.intersection(rectCompo1, rectCompo2);
		if(intersection != null) {
			return intersection.area();
		}
		return 0;
	}

	/**
	 * Démarrage du drag d'un composant.
	 * 
	 * @param component le composant à dragguer.
	 * @param mouseClicPos la position de la souris par rapport au composant au démarrage du drag.
	 */
	private void pick(final SwisiDraggable component, final Coordinates mouseClicPos) {
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
		Coordinates coord = coordinateRelativeToGlassPan(draggable);
		int xOrigine = coord.getX() - mouseClicPos.getX();
		int yOrigine = coord.getY() - mouseClicPos.getY();
		draggableMouseOriginePosGlassPan = new Coordinates(xOrigine, yOrigine);
		draggable.setVisible(false);
		// Placer la copy sur le GlassPan
		graphicalCopy.moveTo(coord.getX(), coord.getY());
		glassPan.addSwisiComponent(graphicalCopy);
		graphicalCopy.setVisible(true);
	}
	
	private Coordinates coordinateRelativeToGlassPan(SwisiComponent component) {
		Coordinates coord = component.getSwisiPositionOnScreen();
		Coordinates coordGlassPan = glassPan.getSwisiPositionOnScreen();
		return Coordinates.relative(coordGlassPan, coord);
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
		// On cacul les coordonnées de drop
		Coordinates dropCoordinate = Coordinates.relative(target.getSwisiPositionOnScreen(), graphicalCopy.getSwisiPositionOnScreen());
		// On demande à la cible trouvée la réception du composant draggué
		if(target.receive(draggable, dropCoordinate)) {
			// Si la cible à bien réceptionné le composant, on supprime ce dernier de son ancien conteneur
			// seulement si la cible est différente du conteneur actuel.
			if(target != oldContainer) {
				oldContainer.removeSwisiComponent(draggable);
			}
		}
	}
}
