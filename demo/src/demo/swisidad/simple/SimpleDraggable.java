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

import swisidad.Coordinate;
import swisidad.component.SwisiComponent;
import swisidad.component.SwisiContainer;
import swisidad.component.SwisiDraggable;
import swisidad.event.SwisiMouseEvent;
import swisidad.listener.SwisiMouseListener;

public class SimpleDraggable extends JPanel implements SwisiDraggable {

	public SimpleDraggable() {
		super();
		setBackground(Color.red);
		setPreferredSize(new Dimension(100, 100));
	}
	
	@Override
	public Coordinate getMouseSwisiCoordonate() {
		Point pMouse = getMousePosition();
		return toCoordonate(pMouse);
	}

	@Override
	public Coordinate getSwisiPositionOnScreen() {
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
			addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					System.out.println("released");
					listener.mouseRelease(new SwisiMouseEventSwing(e));
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					System.out.println("pressed");
					listener.mousePressed(new SwisiMouseEventSwing(e));
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
				}
			});
		}
		if(listener instanceof MouseMotionListener) {
			addMouseMotionListener((MouseMotionListener) listener);
		} else {
			addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					System.out.println("dragged");
					listener.mouseDragged(new SwisiMouseEventSwing(e));
				}
			});
		}
	}

	private Coordinate toCoordonate(Point p) {
		if(p == null) {
			return null;
		}
		return new Coordinate((int) p.getX(), (int) p.getY());
	}
}

final class SwisiMouseEventSwing implements SwisiMouseEvent {
	private final MouseEvent event;
	
	public SwisiMouseEventSwing(MouseEvent e) {
		event = e;
	}

	@Override
	public SwisiComponent getSource() {
		Object source = event.getSource();
		if(source instanceof SwisiComponent) {
			return (SwisiComponent) source;
		}
		return null;
	}

	@Override
	public Coordinate getMousePosition() {
		Point p = event.getPoint();
		return toCoordonate(p);
	}
	
	private Coordinate toCoordonate(Point p) {
		if(p == null) {
			return null;
		}
		return new Coordinate((int) p.getX(), (int) p.getY());
	}
}