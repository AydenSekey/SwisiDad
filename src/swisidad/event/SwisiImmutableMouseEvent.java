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
package swisidad.event;

import swisidad.Coordinates;
import swisidad.component.SwisiComponent;
import swisidad.mouse.SwisiMouseButton;

/**
 * Immutable mouse event.
 */
public final class SwisiImmutableMouseEvent implements SwisiMouseEvent {
	private final SwisiComponent source;
	private final Coordinates mousePosition;
	private final SwisiMouseButton button;
	
	/**
	 * Create mouse event without associated mouse button.
	 * @param componentSource source of event.
	 * @param mouseCoordinates coordinates of mouse pointer when event is thrown
	 */
	public SwisiImmutableMouseEvent(SwisiComponent componentSource, Coordinates mouseCoordinates) {
		this(componentSource, mouseCoordinates, null);
	}

	/**
	 * Create mouse event.
	 * @param componentSource source of event.
	 * @param mouseCoordinates coordinates of mouse pointer when event is thrown
	 * @param mouseButton the mouse button associate with the event.
	 */
	public SwisiImmutableMouseEvent(SwisiComponent componentSource, Coordinates mouseCoordinates, SwisiMouseButton mouseButton) {
		source = componentSource;
		mousePosition = mouseCoordinates;
		button = mouseButton;
	}
	
	@Override
	public SwisiMouseButton getButton() {
		return button;
	}
	
	@Override
	public SwisiComponent getSource() {
		return source;
	}

	@Override
	public Coordinates getMousePosition() {
		return mousePosition;
	}
}
