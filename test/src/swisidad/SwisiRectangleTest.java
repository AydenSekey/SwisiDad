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
package swisidad;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test de la classe SwisiRectangle.
 */
public class SwisiRectangleTest {

	@Test
	public void testIntersect() {
		SwisiRectangle rect1 = new SwisiRectangle(new Coordinate(), 10, 20);
		SwisiRectangle rect2 = new SwisiRectangle(new Coordinate(1, 1), 10, 20);
		assertTrue("Les deux rectangle devrait avoir une intersection", SwisiRectangle.intersect(rect1, rect2));
		
		rect2.setOrigine(new Coordinate(1, 20));
		assertFalse("Les deux rectangle ne devrait pas avoir une intersection", SwisiRectangle.intersect(rect1, rect2));
	}

	@Test
	public void testArea() {
		SwisiRectangle rect = new SwisiRectangle(new Coordinate(), 10, 20);
		final long area = rect.area();
		assertEquals("Aire incorrecte", 200, area);

		rect = new SwisiRectangle(new Coordinate(), 20, 10);
		assertEquals("Aire différente si la hauteur et la largeur sont inversée", area, rect.area());
	}
}
