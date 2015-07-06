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
 * Représente un rectangle.
 */
public class SwisiRectangle {
	private Coordinate origine;
	private final int width;
	private final int height;
	
	/**
	 * Test si deux rectangles s'intersectionne. <br>
	 * Si les bords se touchent, ce n'est pas considérer comme une intersection.
	 * @param rect1 le premier rectangle
	 * @param rect2 le second rectangle
	 * @return <code>true</code> s'il existe une intersection entre les deux rectangles, sinon <code>false</code>.
	 */
	public static boolean intersect(final SwisiRectangle rect1, final SwisiRectangle rect2) {
		boolean intersect = true;
		if(rect1.getOrigine().getX() + rect1.getWidth() <=  rect2.getOrigine().getX()) {
			// rect1 trop à gauche
			intersect = false;
		} else if(rect1.getOrigine().getY() + rect1.getHeight() <= rect2.getOrigine().getY()) {
			// rect1 trop haut
			intersect = false;
		} else if(rect1.getOrigine().getX() >= rect2.getOrigine().getX() + rect2.getWidth()) {
			// rect1 trop à droite
			intersect = false;
		} else if(rect1.getOrigine().getY() >= rect2.getOrigine().getY() + rect2.getHeight()) {
			// rect1 trop bas
			intersect = false;
		}
		return intersect;
	}
	/**
	 * Crée un rectangle.
	 * @param o les coordonnée du coin supérieur gauche du rectangle.
	 * @param w la largeur du rectangle. Doit être strictement positive.
	 * @param h la hauteur du rectangle. Doit être strictement positive.
	 */
	public SwisiRectangle(Coordinate o, int w, int h) {
		if(o == null)
			throw new IllegalArgumentException("L'origine d'un rectangle ne doit pas être null.");
		if(w <= 0 || h <= 0)
			throw new IllegalArgumentException("La hauteur et la largeur doivent être strictement positive");
		origine = o;
		width = w;
		height = h;
	}

	/**
	 * Donne les coordonnées du coin supérieur gauche du rectangle.
	 * @return les coordonnées.
	 */
	public Coordinate getOrigine() {
		return origine;
	}

	/**
	 * Donne la largeur du rectangle.
	 * @return la largeur.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Donne la hauteur du rectangle.
	 * @return la hauteur.
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Modifie les coordonnées du coin supérieur gauche du rectangle.
	 * @param o les nouvelles coordonées. Ne doivent pas être null.
	 */
	public void setOrigine(Coordinate o) {
		if(o == null)
			throw new IllegalArgumentException("l'origine d'un rectangle ne doit pas être null.");
		origine = o;
	}
}
