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
package swisidad.swing.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import swisidad.listener.SwisiMouseListener;
import swisidad.swing.tools.MouseEventConverter;

/**
 * Adapter of {@link SwisiMouseListener} to {@link MouseListener}.
 */
public class SwisiJMouseListenerAdapter implements MouseListener, MouseMotionListener {

	private final SwisiMouseListener swisiListener;
	
	/**
	 * Create an adapter of {@link SwisiMouseListener} to {@link MouseListener}.
	 * @param listener the SwisiDad mouse listener to adapt.
	 */
	public SwisiJMouseListenerAdapter(SwisiMouseListener listener) {
		swisiListener = listener;
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		swisiListener.mouseRelease(MouseEventConverter.toSwisiImmutableMouseEvent(e));
	}

	@Override
    public void mousePressed(MouseEvent e) {
        swisiListener.mousePressed(MouseEventConverter.toSwisiImmutableMouseEvent(e));
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		swisiListener.mouseDragged(MouseEventConverter.toSwisiImmutableMouseEvent(e));
	}

	/**
	 * Do nothing.
	 * {@inheritDoc}
	 */
	@Override
	public void mouseClicked(MouseEvent e) {}

	/**
	 * Do nothing.
	 * {@inheritDoc}
	 */
	@Override
	public void mouseEntered(MouseEvent e) {}

	/**
	 * Do nothing.
	 * {@inheritDoc}
	 */
	@Override
	public void mouseExited(MouseEvent e) {}

	/**
	 * Do nothing.
	 * {@inheritDoc}
	 */
	@Override
	public void mouseMoved(MouseEvent e) {}
}
