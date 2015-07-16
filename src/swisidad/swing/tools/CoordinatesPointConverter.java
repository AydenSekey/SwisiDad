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
package swisidad.swing.tools;

import java.awt.Point;

import swisidad.Coordinates;

public final class CoordinatesPointConverter {
	/* Interdiction de construire une instance */
	private CoordinatesPointConverter() {
		throw new UnsupportedOperationException("Instanciate a converter is forbidden.");
	}
	
	/**
	 * Convertie un point en coordonnées SwisiDad.
	 * 
	 * @param p le point à convertir
	 * @return les coordonnées SwisiDad équivalentes.
	 */
	public static Coordinates toCoordinates(Point p) {
		if(p == null) {
			return null;
		}
		return new Coordinates((int) p.getX(), (int) p.getY());
	}
	
	/**
	 * Convertie des coordonnées SwisiDad en un point AWT.
	 * 
	 * @param coord les coordonnées à convertir.
	 * @return le point AWT équivalent.
	 */
	public static Point toPoint(Coordinates coord) {
		if(coord == null) {
			return null;
		}
		return new Point(coord.getX(), coord.getY());
	}
}
