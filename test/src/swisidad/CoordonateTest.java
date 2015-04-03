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

public class CoordonateTest {

	@Test
	public void testEquals() {
		Coordonate c1 = new Coordonate(1, 2);
		Coordonate c2 = new Coordonate(4, 3);
		
		assertEquals("L'identité n'implique pas l'égalité !", c1, c1);
		assertNotEquals("Inegalité incorrecte", c1, c2);
		
		c2.setX(c1.getX());
		c2.setY(c1.getY());
		
		assertEquals("Egalité incorrecte", c1, c2);
		assertEquals("Commutativité incorrecte", c2, c1);
	}
	
	@Test
	public void testHashCode() {
		Coordonate c1 = new Coordonate(1, 2);
		Coordonate c2 = new Coordonate(1, 2);
		
		assertEquals("hashCodes de deux objets égaux doivent être égaux", c1.hashCode(), c2.hashCode());
	}
	
	@Test
	public void testRelative() {
		Coordonate cOrigin = new Coordonate();
		Coordonate c1 = new Coordonate(1, 2);
		Coordonate c2 = new Coordonate(3, 4);
	
		Coordonate result = Coordonate.relative(c1, c1);
		assertEquals("relative d'une coordonée à elle-même incorrecte", cOrigin, result);

		result = Coordonate.relative(cOrigin, c1);
		assertEquals("relative d'une coordonée à l'origine incorrecte", c1, result);

		result = Coordonate.relative(c1, cOrigin);
		assertEquals("relative de l'origine à une coordonée incorrecte", new Coordonate(-1, -2), result);

		result = Coordonate.relative(c1, c2);
		assertEquals("relative de deux coordonnées différentes incorrecte", new Coordonate(2, 2), result);
	}

}
