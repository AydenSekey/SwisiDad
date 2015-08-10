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
 * Interface pour les composants pouvant changer de visibilité.
 */
public interface SwisiToggleVisibiliteComponent extends SwisiComponent {
	/**
	 * Modifie la visibilité du composant.
	 * 
	 * @param visible <code>true</code> rend le composant visible, <code>false</code> le rend invisible.
	 */
	public void setVisible(boolean visible);
}
