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
package swisidad.component;

import swisidad.listener.SwisiMouseListener;

/**
 * Interface pour les composants draggable. 
 */
public interface SwisiDraggable extends SwisiToggleVisibiliteComponent, SwisiComponent {
	/**
	 * Crée une copie graphique de l'élément draggué.
	 * 
	 * @return la copie graphique.
	 */
	public SwisiDraggable graphicalCopy();
	
	/**
	 * Donne le container actuel du composant.
	 * 
	 * @return le container du composant.
	 */
	public SwisiContainer getContainer();
	
	/**
	 * Déplace le composant.
	 * 
	 * @param x l'abscisse de la position cible
	 * @param y l'ordonnée de la position cible
	 */
	public void moveTo(int x, int y);
	
	/**
	 * Ajoute un écouteur sur les événements de souris.
	 * 
	 * @param listener l'écouteur d'événement de souris.
	 */
	public void addMouseListener(SwisiMouseListener listener);
}
