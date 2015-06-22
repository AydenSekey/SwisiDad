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
package demo.swisidad.simple;

import java.awt.BorderLayout;
import java.awt.Color;

import swisidad.manager.SwisiDadManager;

public final class DemoSimple {

	public static void main(String[] args) {
		SimpleFenetre fenetre = new SimpleFenetre();
		fenetre.setLocationRelativeTo(null);

		SimpleGlassPan glassPan = new SimpleGlassPan();
		glassPan.setVisible(false);
		fenetre.setGlassPane(glassPan);
		
		SimpleContainer target1 = createContainer(Color.CYAN);
		SimpleContainer target2 = createContainer(Color.GREEN);
		fenetre.add(target1, BorderLayout.WEST);
		fenetre.add(target2, BorderLayout.EAST);

		SimpleDraggable draggable = new SimpleDraggable();
		target1.addSwisiComponent(draggable);
		
		final SwisiDadManager manager = new SwisiDadManager();
		manager.addTarget(target1);
		manager.addTarget(target2);
		manager.setGlassPan(glassPan);
		manager.manage(draggable);

		fenetre.setVisible(true);
	}

	private static SimpleContainer createContainer(Color color) {
		SimpleContainer target = new SimpleContainer();
		target.setBackground(color);
		return target;
	}
}
