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
package de.hofuniversity.iws.client.playn.games;

import de.hofuniversity.iws.client.playn.StandardPhysicGame;
import de.hofuniversity.iws.client.playn.entitys.*;
import playn.core.*;

import static playn.core.PlayN.graphics;

/**
 *
 * @author Daniel Heinrich <DannyNullZwo@gmail.com>
 */
public class PendelWidget extends StandardPhysicGame {

    private static final float PPOS_X = 2.5f;
    private static final int PPOS_Y = 4;
    public GroupLayer staticLayerBack, dynamicLayer;
    private LearnPendulum pendel;

    public PendelWidget() {
        super(true);
    }

    @Override
    protected void initGame(GroupLayer scaledLayer) {
        staticLayerBack = graphics().createGroupLayer();
        scaledLayer.add(staticLayerBack);
        dynamicLayer = graphics().createGroupLayer();
        scaledLayer.add(dynamicLayer);
        
        SurfaceLayer layer = graphics().createSurfaceLayer(getWidth()*getScaleFactor(), getHeight()*getScaleFactor());
        Surface surface = layer.surface();
        surface.clear();
        surface.setFillColor(Color.rgb(255, 255, 255));
        surface.fillRect(0, 0, surface.width(), surface.height());
        staticLayerBack.add(layer);        

        pendel = LearnPendulum.build(getPhysicWorld(), getScaleFactor())
                .onPosition(PPOS_X, PPOS_Y)
                .create();

        add(pendel);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    public void setPendelLength(float len) {
        pendel.setLength(len);
    }

    @Override
    public void add(Entity entity) {
        super.add(entity);
        dynamicLayer.add(entity.getLayer());
    }

    @Override
    public float getWidth() {
        return 5;
    }

    @Override
    public float getHeight() {
        return 5;
    }

    @Override
    public int getPlayerScore() {
        return 0;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void restart() {
    }
}
