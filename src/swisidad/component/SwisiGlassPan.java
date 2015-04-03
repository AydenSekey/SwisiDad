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

/**
 * Interface pour le conteneur virtuel sur le lequel dessiné le composant en train d'être draggué.
 */
public interface SwisiGlassPan extends SwisiContainer, SwisiToggleVisibiliteComponent {
	
	/**
	 * Modifie l'opacité du conteneur.
	 * 
	 * @param opaque <code>true</code> pour le rendre opaque, <code>false</code> pour le rendre transparent.
	 */
	public void setOpaque(boolean opaque);

	/**
	 * Vérifie si le conteneur est opaque.
	 * 
	 * @return <code>true</code> s'il est opaque, sinon <code>false</code>
	 */
	public boolean isOpaque();
}
