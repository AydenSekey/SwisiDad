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
package swisidad.manager;

import swisidad.component.SwisiDraggable;
import swisidad.component.SwisiGlassPan;
import swisidad.component.SwisiTarget;

/**
 * Interface for drag and drop managers.
 */
public interface SwisiDadManager {

	/**
	 * Add a potential target for a draggable component.
	 * 
	 * @param target a new potential target for draggable component.
	 */
	public void addTarget(SwisiTarget target);

	/**
	 * Get the GlassPane used.
	 * 
	 * @return the GlassPane used.
	 */
	public SwisiGlassPan getGlassPan();

	/**
	 * Define the GlassPane used to view drag.
	 * 
	 * @param glassPan the GlassPane to use.
	 */
	public void setGlassPan(SwisiGlassPan glassPan);

	/**
	 * Take a new draggable component to manage.
	 * 
	 * @param draggable the component to manage.
	 */
	public void manage(SwisiDraggable draggable);
}
