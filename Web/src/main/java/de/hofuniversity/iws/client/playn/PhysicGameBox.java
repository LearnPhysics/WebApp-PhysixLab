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

import playn.core.*;

import static playn.core.PlayN.graphics;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class PhysicGameBox implements Game {

    private final PhysicGame game;
    private final int width;
    private final GameEventListener listener;

    public PhysicGameBox(PhysicGame game, int width, GameEventListener listener) {
        this.game = game;
        this.width = width;
        this.listener = listener;
    }

    @Override
    public void init() {
        GroupLayer worldLayer = graphics().createGroupLayer();
        float scale = width / game.getWidth();
        worldLayer.setScale(scale, -scale);
        worldLayer.setTranslation(0, getHeight());
        graphics().rootLayer().add(worldLayer);

        game.init(worldLayer);
    }

    public int getHeight() {
        float ratio = game.getWidth() / game.getHeight();
        return (int) (width / ratio);
    }

    @Override
    public void update(float delta) {
        game.update(delta);
        if (game.isFinished()) {
            listener.gameEnded(new GameEvent(game.getPlayerScore()));
        }
    }

    @Override
    public void paint(float alpha) {
        game.paint(alpha);
    }

    @Override
    public int updateRate() {
        return 25;
    }
}
