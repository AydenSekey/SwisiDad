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
public class Coordonate {
	private int x;
	private int y;
	
	/**
	 * Donne la coordonée relative à une coordonnée d'origine correspondante à une autre coordonnée.
	 * 
	 * @param origin la coordonée d'origine
	 * @param coordonate la coordonée cible
	 * @return les coordonées de la cible relative à l'origine.
	 */
	public static Coordonate relative(Coordonate origin, Coordonate coordonate) {
		return new Coordonate(coordonate.x - origin.x, coordonate.y - origin.y);
	}

	/**
	 * Crée une coordonnée (0;0).
	 */
	public Coordonate() {
		this(0, 0);
	}
	
	/**
	 * Crée une coordonnée.
	 * 
	 * @param x l'abscisse
	 * @param y l'ordonnée
	 */
	public Coordonate(int x, int y) {
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
	 * Modifie l'ordonnée.
	 * 
	 * @param y la nouvelle ordonnée
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Donne l'abscisse de la coordonnée.
	 * 
	 * @return l'abscisse
	 */
	public int getX() {
		return x;
	}

	/**
	 * Modifie l'abscisse de la coordonnée.
	 * 
	 * @param x la nouvelle abscisse.
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Modifie l'abscisse et l'ordonnée de la coordonnée.
	 * @param x la nouvelle abcisse
	 * @param y la nouvelle ordonnée
	 */
	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
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
		if (!(obj instanceof Coordonate)) {
			return false;
		}
		Coordonate other = (Coordonate) obj;
		return  x == other.x && y == other.y;
	}
	
	@Override
	public String toString() {
		return "(" + x + ";" + y + ")";
	}
}
