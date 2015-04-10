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
package swisidad.listener;

import swisidad.event.SwisiMouseEvent;

/**
 * Interface des écouteurs d'événement de souris pour SwisiDad.
 */
public interface SwisiMouseListener {
	/**
	 * Méthode appelée lorsque le bouton de la souris permettant d'effectuer le drag and drop est pressé.
	 * 
	 * @param event informations sur l'événement du bouton pressé.
	 */
	public void mousePressed(SwisiMouseEvent event);
	
	/**
	 * Méthode appelée lorsque le bouton de la souris permettant d'effectuer le drag and drop est relaché.
	 * 
	 * @param event informations sur l'événement du bouton relaché.
	 */
	public void mouseRelease(SwisiMouseEvent event);
	
	/**
	 * Méthode appeléee lorsque la souris est déplacée pendant que le bouton permettant d'effectuer le drag and drop est pressé.
	 * 
	 * @param event informations sur l'événement de mouvement.
	 */
	public void mouseDragged(SwisiMouseEvent event);
}
