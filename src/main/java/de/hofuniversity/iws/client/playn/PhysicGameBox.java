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

    public PhysicGameBox(PhysicGame game, int width) {
        this.width = width;
        this.game = game;
    }

    @Override
    public void init() {
        float ratio = game.getWidth() / game.getHeight();
        int height = (int) (width / ratio);

        graphics().setSize(width, height);

        GroupLayer worldLayer = graphics().createGroupLayer();
        worldLayer.setScale(width / game.getWidth());
        graphics().rootLayer().add(worldLayer);

        game.init(worldLayer);
    }

    @Override
    public void update(float delta) {
        game.update(delta);
    }

    @Override
    public void paint(float alpha) {
        game.paint(alpha);
    }

    @Override
    public int updateRate() {
        return 60;
    }
}
