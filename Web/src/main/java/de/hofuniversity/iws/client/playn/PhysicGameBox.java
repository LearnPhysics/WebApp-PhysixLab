/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
