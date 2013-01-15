/*
  * Copyright (C) 2012 Daniel Heinrich
  *
  * This program is free software: you can redistribute it and/or modify
  * it under the terms of the GNU General Public License as published by
  * the Free Software Foundation, either version 3 of the License, or
  * (at your option) any later version.
  *
  * This program is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU General Public License for more details.
  *
  * You should have received a copy of the GNU General Public License
  * along with this program.  If not, see <http://www.gnu.org/licenses/>.
  */
package de.hofuniversity.iws.client.playn;

import playn.core.GroupLayer;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
//don't change the class name, because this interface is used in a gwt generator with hardcoded names
public interface PhysicGame {

    public void init(GroupLayer scaledLayer);

    public void update(float delta);

    public void paint(float alpha);
    
    /**
     * The width of the game world. Nothing to do with the screen size.
     * @return 
     */
    public float getWidth();

    /**
     * The height of the game world. Nothing to do with the screen size.
     * @return 
     */
    public float getHeight();
    
    public int getPlayerScore();
    
    public boolean isFinished();
    
    public void restart();
}
