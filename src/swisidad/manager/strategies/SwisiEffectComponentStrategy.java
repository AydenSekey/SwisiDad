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

import swisidad.component.SwisiComponent;

/**
 * Interface of effect strategies for components. 
 */
public interface SwisiEffectComponentStrategy<C extends SwisiComponent> {
	/**
	 * Apply an effect to a component.
	 * @param component the component to apply effect.
	 */
	public void applyEffect(C component);
	
	/**
	 * Apply a reverse of effect apply by {@link #applyEffect(SwisiComponent)}.
	 * @param component the component to apply the reverse effect.
	 */
	public void applyReverseEffect(C component);
}
