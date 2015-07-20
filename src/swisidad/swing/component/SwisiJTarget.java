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
package swisidad.swing.component;

import java.awt.Component;
import java.awt.Point;

import javax.swing.JPanel;

import swisidad.Coordinates;
import swisidad.component.SwisiComponent;
import swisidad.component.SwisiDraggable;
import swisidad.component.SwisiTarget;
import swisidad.swing.tools.CoordinatesPointConverter;

/**
 * Target basique compatible avec Swing qui accepte les composants reçus par défaut.
 */
public abstract class SwisiJTarget extends JPanel implements SwisiTarget {

	/**
	 * Ajoute l'objet droppé.
	 * 
	 * @param dropCoord les coordonnées de drop.
	 * @return <code>true</code>
	 */
	@Override
	public boolean receive(SwisiDraggable component, Coordinates dropCoord) {
		addSwisiComponent(component, dropCoord);
		return true;
	}

	@Override
	public void removeSwisiComponent(SwisiComponent component) {
		super.remove((Component) component);
	}

	@Override
	public Coordinates getMouseSwisiCoordonates() {
		Point pMouse =  super.getMousePosition();
		return CoordinatesPointConverter.toCoordinates(pMouse);
	}

	@Override
	public Coordinates getSwisiPositionOnScreen() {
		Point pos = super.getLocationOnScreen();
		return CoordinatesPointConverter.toCoordinates(pos);
	}
}
