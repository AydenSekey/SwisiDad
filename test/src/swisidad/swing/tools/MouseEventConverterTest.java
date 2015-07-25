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

import static org.junit.Assert.*;

import java.awt.Component;
import java.awt.event.MouseEvent;

import org.junit.Test;

import swisidad.Coordinates;
import swisidad.event.SwisiMouseEvent;
import swisidad.swing.component.SwisiJGlassPane;

public class MouseEventConverterTest {

	@Test
	public void testToSwisiImmutableMouseEvent() {
		Component source = new SwisiJGlassPane();
		MouseEvent eventSwing = new java.awt.event.MouseEvent(source, 1, 0L, 0, 1, 2, 3, 4, 1, false, 0);

		SwisiMouseEvent event = MouseEventConverter.toSwisiImmutableMouseEvent(eventSwing);
		assertTrue("Source incorrecte", event.getSource() == source);
		assertEquals("Coordonnées incorrectes", new Coordinates(1, 2), event.getMousePosition());
	}

}
