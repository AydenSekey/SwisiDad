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

/**
 * Représentation des coordonnées dans SwisiDad.
 */
public final class Coordinate {
	private final int x;
	private final int y;
	
	/**
	 * Donne la coordonée relative à une coordonnée d'origine correspondante à une autre coordonnée.
	 * 
	 * @param origin la coordonée d'origine
	 * @param coordonate la coordonée cible
	 * @return les coordonées de la cible relative à l'origine.
	 */
	public static Coordinate relative(Coordinate origin, Coordinate coordonate) {
		return sub(coordonate, origin);
	}

	/**
	 * Additionne des coordonnées. <br>
	 * Exemple avec comme coordonnées (1;2) et (-1;3) le résultat sera (0;5).
	 * @param coord1 les coordonnées d'origine
	 * @param coord2 les coordonnées de déplacement.
	 * @return les coordonnées résulantes de l'addition.
	 */
	public static Coordinate add(Coordinate coord1, Coordinate coord2) {
		return new Coordinate(coord1.getX() + coord2.getX(), coord1.getY() + coord2.getY());
	}
	
	/**
	 * Soustrait des coordonées à d'autres. <br>
	 * Exemple avec comme coordonnées (1;2) et (-3;2) le résultat sera (4;0).
	 * @param origin les coordonnées auxquelles soustraire les autres
	 * @param coord les coordonnées à soustraire
	 * @return les coordonnées résultant de la soustraction.
	 */
	public static Coordinate sub(Coordinate origin, Coordinate coord) {
		return new Coordinate(origin.getX() - coord.getX(), origin.getY() - coord.getY());
	}

	/**
	 * Crée une coordonnée (0;0).
	 */
	public Coordinate() {
		this(0, 0);
	}
	
	/**
	 * Crée une coordonnée.
	 * 
	 * @param x l'abscisse
	 * @param y l'ordonnée
	 */
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	
	/**
	 * Donne l'ordonnée de la coordonées.
	 * 
	 * @return l'ordonnée.
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Donne l'abscisse de la coordonnée.
	 * 
	 * @return l'abscisse
	 */
	public int getX() {
		return x;
	}

	@Override
	public int hashCode() {
		int result = x;
		result = 256 * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Coordinate)) {
			return false;
		}
		Coordinate other = (Coordinate) obj;
		return  x == other.x && y == other.y;
	}
	
	@Override
	public String toString() {
		return "(" + x + ";" + y + ")";
	}
}
