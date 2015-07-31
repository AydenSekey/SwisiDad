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

import java.awt.event.MouseEvent;

import swisidad.component.SwisiComponent;
import swisidad.event.SwisiImmutableMouseEvent;
import swisidad.mouse.SwisiMouseButton;

/**
 * Util class to convert SwisiMouseEvent to Swing MouseEvent and reverse.
 */
public final class MouseEventConverter {
	private MouseEventConverter() {
		throw new UnsupportedOperationException("Instanciate a converter is forbidden");
	}
	
	/**
	 * Convert {@link MouseEvent} to {@link SwisiImmutableMouseEvent}
	 * @param event the event to convert
	 * @return the event after conversion
	 * @throws ClassCastException if event source is not a SwisiComponent.
	 */
	public static SwisiImmutableMouseEvent toSwisiImmutableMouseEvent(MouseEvent event) {
		if(event.getSource() instanceof SwisiComponent) {
			SwisiMouseButton button = null;
			switch(event.getButton()) {
				case MouseEvent.BUTTON1:
					button = SwisiMouseButton.LEFT;
					break;
				case MouseEvent.BUTTON2:
					button = SwisiMouseButton.MIDDLE;
					break;
				case MouseEvent.BUTTON3:
					button = SwisiMouseButton.RIGHT;
					break;
				default:
					break;
			}
			return new SwisiImmutableMouseEvent((SwisiComponent) event.getSource(), CoordinatesPointConverter.toCoordinates(event.getPoint()), button);
		}
		throw new ClassCastException("event source is not a SwisiComponent");
	}

}
