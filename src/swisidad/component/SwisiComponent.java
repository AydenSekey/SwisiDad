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

import swisidad.Coordonate;

/**
 * Interface commune à tous les composants SwisiDad.
 */
public interface SwisiComponent {

	/**
	 * Redessine le composant.
	 */
	public void repaint();
	
	/**
	 * Donne la position de la souris par rapport au coin supérieur gauche du conteneur.
	 * 
	 * @return la position de la souris.
	 */
	public Coordonate getMousePosition();
	
	/**
	 * Donne la position du coin suppérieur gauche du composant par rapport à celui de l'écran.
	 * 
	 * @return la position sur l'écran du composant.
	 */
	public Coordonate getPositionOnScreen();
	
	/**
	 * Donne la largeur du composant.
	 * 
	 * @return la largeur.
	 */
	public int getWidth();
	
	/**
	 * Donne la hauteur du composant.
	 * 
	 * @return la hauteur.
	 */
	public int getHeight();
}
