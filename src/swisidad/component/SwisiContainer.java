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

import swisidad.Coordinate;

/**
 * Interface pour les composants conteneur.
 */
public interface SwisiContainer extends SwisiComponent {
	/**
	 * Ajoute un composant draggable au container.
	 * 
	 * @param component le composant à ajouter
	 */
	public void addSwisiComponent(SwisiComponent component);
	
	/**
	 * Ajoute un composant draggable au container à une position spécifique si possible. <br>
	 * Si la position n'est pas valable, cette méthode devrait donner le même résultat que {@link #add(SwisiDraggable)}.
	 * 
	 * @param component le composant à ajouter
	 * @param pos les coordonnées auxquelles positioner le composant dans le container.
	 */
	public void addSwisiComponent(SwisiComponent component, Coordinate pos);
	
	/**
	 * Supprime le composant draggable du container.
	 * 
	 * @param component le composant à supprimer
	 */
	public void removeSwisiComponent(SwisiComponent component);
}
