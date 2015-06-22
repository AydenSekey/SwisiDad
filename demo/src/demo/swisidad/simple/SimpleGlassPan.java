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
package demo.swisidad.simple;

import java.awt.Component;
import java.awt.Point;

import javax.swing.JPanel;

import swisidad.Coordinate;
import swisidad.component.SwisiComponent;
import swisidad.component.SwisiGlassPan;

public class SimpleGlassPan extends JPanel implements SwisiGlassPan {

	public SimpleGlassPan() {
		super();
		setLayout(null);
		setOpaque(false);
	}

	@Override
	public void addSwisiComponent(SwisiComponent component) {
		super.add((Component) component);
	}
	
	@Override
	public void addSwisiComponent(SwisiComponent component, Coordinate pos) {
		Component c = (Component) component;
		c.setLocation(pos.getX(), pos.getY());
		super.add(c);
	}

	@Override
	public void removeSwisiComponent(SwisiComponent component) {
		super.remove((Component) component);
	}

	@Override
	public Coordinate getMouseSwisiCoordonate() {
		Point pMouse =  super.getMousePosition();
		return toCoordonate(pMouse);
	}

	@Override
	public Coordinate getSwisiPositionOnScreen() {
		Point pos = super.getLocationOnScreen();
		return toCoordonate(pos);
	}

	private Coordinate toCoordonate(Point p) {
		if(p == null) {
			return null;
		}
		return new Coordinate((int) p.getX(), (int) p.getY());
	}
}
