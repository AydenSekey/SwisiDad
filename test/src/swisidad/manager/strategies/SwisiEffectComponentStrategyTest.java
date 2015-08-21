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
package swisidad.manager.strategies;

import static org.junit.Assert.*;
import javafx.scene.control.Toggle;

import org.junit.Test;

import swisidad.Coordinates;
import swisidad.component.SwisiToggleVisibiliteComponent;

public class SwisiEffectComponentStrategyTest {

	@Test
	public void testInvisibleEffectStrategy() {
        SwisiInvisibleEffectStrategy<SwisiToggleVisibiliteComponent> strategy = new SwisiInvisibleEffectStrategy<>();
		SwisiToggleVisibiliteComponent component = new ToggleVisibilityComponentTest();
		strategy.applyEffect(component);
		assertFalse("Effect don't apply", component.isVisible());
		strategy.applyReverseEffect(component);
		assertTrue("Reverse effect don't apply", component.isVisible());
	}

}

class ToggleVisibilityComponentTest implements SwisiToggleVisibiliteComponent {
	private boolean visible = false;
	
	@Override
	public void repaint() {
	}

	@Override
	public Coordinates getMouseSwisiCoordonates() {
		return null;
	}

	@Override
	public Coordinates getSwisiPositionOnScreen() {
		return null;
	}

	@Override
	public int getWidth() {
		return 0;
	}

	@Override
	public int getHeight() {
		return 0;
	}

	@Override
	public boolean isVisible() {
		return visible;
	}

	@Override
	public void setVisible(boolean visible) {
		this.visible = visible; 
	}
	
}
