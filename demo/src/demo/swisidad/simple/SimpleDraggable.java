package demo.swisidad.simple;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPanel;

import swisidad.Coordinates;
import swisidad.component.SwisiComponent;
import swisidad.component.SwisiContainer;
import swisidad.component.SwisiDraggable;
import swisidad.event.SwisiMouseEvent;
import swisidad.listener.SwisiMouseListener;
import swisidad.swing.listener.SwisiJMouseListenerAdapter;
import swisidad.swing.tools.CoordinatesPointConverter;

public class SimpleDraggable extends JPanel implements SwisiDraggable {

	public SimpleDraggable() {
		super();
		setBackground(Color.red);
		setPreferredSize(new Dimension(100, 100));
	}
	
	@Override
	public Coordinates getMouseSwisiCoordonates() {
		Point pMouse = getMousePosition();
		return toCoordonate(pMouse);
	}

	@Override
	public Coordinates getSwisiPositionOnScreen() {
		Point pos = getLocationOnScreen();
		return toCoordonate(pos);
	}

	@Override
	public SwisiDraggable graphicalCopy() {
		SimpleDraggable copy = new SimpleDraggable();
		copy.setBackground(Color.PINK);
		return copy;
	}

	@Override
	public SwisiContainer getSwisiContainer() {
		Component parent = getParent();
		return (SwisiContainer) parent;
	}

	@Override
	public void moveTo(int x, int y) {
		setLocation(x, y);
		Dimension d = getPreferredSize();
		setBounds(x, y, d.width, d.height);
	}

	@Override
	public void addMouseListener(SwisiMouseListener listener) {
		if(listener instanceof MouseListener) {
			addMouseListener((MouseListener) listener);
		} else {
			addMouseListener(new SwisiJMouseListenerAdapter(listener) {
				@Override
				public void mouseReleased(MouseEvent e) {
					System.out.println("released");
					super.mouseReleased(e);
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					System.out.println("pressed");
					super.mousePressed(e);
				}
			});
		}
		if(listener instanceof MouseMotionListener) {
			addMouseMotionListener((MouseMotionListener) listener);
		} else {
			addMouseMotionListener(new SwisiJMouseListenerAdapter(listener) {
				@Override
				public void mouseDragged(MouseEvent e) {
					System.out.println("dragged");
					super.mouseDragged(e);
				}
			});
		}
	}

	private Coordinates toCoordonate(Point p) {
		if(p == null) {
			return null;
		}
		return new Coordinates((int) p.getX(), (int) p.getY());
	}
}