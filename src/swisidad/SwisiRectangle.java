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
	private Coordinates origine;
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
	 * Calcul l'intersection de deux rectangles.
	 * @param rect1 le premier rectangle. Ne doit pas être <code>null</code>.
	 * @param rect2 le deuxième rectangle. Ne doit pas être <code>null</code>.
	 * @return un nouveau rectangle représentant l'intersection ou <code>null</code> s'il n'y a pas d'intersection.
	 * @throws IllegalArgumentException si un argument est <code>null</code>.
	 */
	public static SwisiRectangle intersection(SwisiRectangle rect1,	SwisiRectangle rect2) {
		if(rect1 == null || rect2 == null) {
			throw new IllegalArgumentException("Les paramètres de doivent pas êtres null");
		}
		if(rect1 == rect2) {
			// Si c'est le même objet pas de question, l'intersection d'un rectangle avec lui-même est un rectangle identique à lui-même.
			return new SwisiRectangle(rect1);
		}

		SwisiRectangle intersection = null;
		if(intersect(rect1, rect2)) {
			// Une intersection existe, reste à la calculer :)
			Coordinates rect1OriginOpposite = rect1.oppositeOrigneCornerCoordinate();
			Coordinates rect2OriginOpposite = rect2.oppositeOrigneCornerCoordinate();

			int xLeft = Math.max(rect1.getOrigine().getX(), rect2.getOrigine().getX());
			int xRight = Math.min(rect1OriginOpposite.getX(), rect2OriginOpposite.getX());
			int yTop = Math.max(rect1.getOrigine().getY(), rect2.getOrigine().getY());
			int yBottom = Math.min(rect1OriginOpposite.getY(), rect2OriginOpposite.getY());
			Coordinates intersectionOrigine = new Coordinates(xLeft, yTop);
			int intersectionWidth = xRight - xLeft;
			int intersectionHeight = yBottom - yTop;
			intersection = new SwisiRectangle(intersectionOrigine, intersectionWidth, intersectionHeight);
		}
		return intersection;
	}

	/**
	 * Crée un rectangle.
	 * @param o les coordonnée du coin supérieur gauche du rectangle.
	 * @param w la largeur du rectangle. Doit être strictement positive.
	 * @param h la hauteur du rectangle. Doit être strictement positive.
	 */
	public SwisiRectangle(Coordinates o, int w, int h) {
		if(o == null)
			throw new IllegalArgumentException("L'origine d'un rectangle ne doit pas être null.");
		if(w <= 0 || h <= 0)
			throw new IllegalArgumentException("La hauteur et la largeur doivent être strictement positive");
		origine = o;
		width = w;
		height = h;
	}
	
	/**
	 * Crée un rectangle par copie d'un autre.
	 * @param rect le rectangle à copier.
	 */
	public SwisiRectangle(SwisiRectangle rect) {
		// Référence sur la même Coordinate OK car l'objet est immutable.
		this(rect.getOrigine(), rect.getWidth(), rect.getHeight());
	}

	/**
	 * Donne les coordonnées du coin supérieur gauche du rectangle.
	 * @return les coordonnées.
	 */
	public Coordinates getOrigine() {
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
	public void setOrigine(Coordinates o) {
		if(o == null)
			throw new IllegalArgumentException("l'origine d'un rectangle ne doit pas être null.");
		origine = o;
	}
	
	/**
	 * Calcule l'aire du rectangle.
	 * @return l'aire du rectangle.
	 */
	public long area() {
		return width * height;
	}
	
	/**
	 * Donne les coordonées du coin opposé à celui servant d'origine. C'est à dire les coordonées du coin inférieur droit.
	 * @return les coordonées du coin inférieur droit du rectangle.
	 */
	public Coordinates oppositeOrigneCornerCoordinate() {
		return new Coordinates(origine.getX() + width, origine.getY() + height);
	}
}
