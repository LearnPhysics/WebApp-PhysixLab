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
package de.hofuniversity.iws.client.playn.entitys;

import playn.core.Layer;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public interface Entity {

    public void paint(float alpha);

    public void update(float delta);

    public Layer getLayer();

    public float getWidth();

    public float getHeight();

    /**
     * destroy all resources which are asociated with the entity
     */
    public void destroy();

    /**
     * if the entity should get removed from the running game
     * @return 
     */
    public boolean isMarkedForRemoval();
}
