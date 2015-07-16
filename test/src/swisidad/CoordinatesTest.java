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

public class CoordinatesTest {

	@Test
	public void testEquals() {
		Coordinates c1 = new Coordinates(1, 2);
		Coordinates c2 = new Coordinates(4, 3);
		
		assertEquals("L'identité n'implique pas l'égalité !", c1, c1);
		assertNotEquals("Inegalité incorrecte", c1, c2);
		
		c2 = new Coordinates(c1.getX(), c1.getY());
		
		assertEquals("Egalité incorrecte", c1, c2);
		assertEquals("Commutativité incorrecte", c2, c1);
	}
	
	@Test
	public void testHashCode() {
		Coordinates c1 = new Coordinates(1, 2);
		Coordinates c2 = new Coordinates(1, 2);
		
		assertEquals("hashCodes de deux objets égaux doivent être égaux", c1.hashCode(), c2.hashCode());
	}
	
	@Test
	public void testRelative() {
		Coordinates cOrigin = new Coordinates();
		Coordinates c1 = new Coordinates(1, 2);
		Coordinates c2 = new Coordinates(3, 4);
	
		Coordinates result = Coordinates.relative(c1, c1);
		assertEquals("relative d'une coordonée à elle-même incorrecte", cOrigin, result);

		result = Coordinates.relative(cOrigin, c1);
		assertEquals("relative d'une coordonée à l'origine incorrecte", c1, result);

		result = Coordinates.relative(c1, cOrigin);
		assertEquals("relative de l'origine à une coordonée incorrecte", new Coordinates(-1, -2), result);

		result = Coordinates.relative(c1, c2);
		assertEquals("relative de deux coordonnées différentes incorrecte", new Coordinates(2, 2), result);
	}

	@Test
	public void testAdd() {
		final Coordinates origine = new Coordinates();
		Coordinates coord1 = new Coordinates(1, 2);
		Coordinates coord2 = new Coordinates(3, 4);
		
		assertEquals("Addition avec l'origine erronée", coord1, Coordinates.add(origine, coord1));
		assertEquals("Addition incorrecte", new Coordinates(4, 6), Coordinates.add(coord1, coord2));
		// Commutativité
		assertEquals("Commutativité avec l'origine erronée", coord1, Coordinates.add(coord1, origine));
		assertEquals("Commutativité incorrecte", new Coordinates(4, 6), Coordinates.add(coord2, coord1));
		// Addition négative
		coord2 = new Coordinates(-1, -3);
		assertEquals("Addition de positif avec négatif incorrecte", new Coordinates(0, -1), Coordinates.add(coord1, coord2));
		coord1 = new Coordinates(-1, -1);
		assertEquals("Addition de négatif avec négatif incorrecte", new Coordinates(-2, -4), Coordinates.add(coord1, coord2));
	}
	
	@Test
	public void testSub() {
		final Coordinates origine = new Coordinates();
		Coordinates coord1 = new Coordinates(1, 2);
		Coordinates coord2 = new Coordinates(3, 4);
		
		assertEquals("Soustraction avec l'origine erronée", new Coordinates(coord1.getX() * -1, coord1.getY() * -1), Coordinates.sub(origine, coord1));
		assertEquals("Soustraction incorrecte", new Coordinates(-2, -2), Coordinates.sub(coord1, coord2));
		// Commutativité
		assertNotEquals("Commutativité incorrecte", new Coordinates(-2, -2), Coordinates.sub(coord2, coord1));
		// Soustraction négative
		coord2 = new Coordinates(-1, -3);
		assertEquals("Soustraction de positif avec négatif incorrecte", new Coordinates(2, 5), Coordinates.sub(coord1, coord2));
		coord1 = new Coordinates(-1, -1);
		assertEquals("Soustraction de négatif avec négatif incorrecte", new Coordinates(0, 2), Coordinates.sub(coord1, coord2));
	}
}
