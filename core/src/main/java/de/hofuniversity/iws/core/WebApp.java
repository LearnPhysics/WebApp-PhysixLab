package de.hofuniversity.iws.core;

import java.awt.Window;

import com.google.gwt.user.client.rpc.AsyncCallback;
import de.hofuniversity.iws.core.game.entities.Ball;
import de.hofuniversity.iws.core.services.GWTServiceTestAsync;
import javax.inject.Inject;
import playn.core.*;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;

public class WebApp implements Game {

    // scale difference between screen space (pixels) and world space (physics).
    public static float physUnitPerScreenUnit = 1 / 26.666667f;
//    @Inject
//    private GWTServiceTestAsync service;
    private TestWorld world;
    private ImageLayer bgLayer;
    // main layer that holds the world. note: this gets scaled to world space
    private GroupLayer worldLayer;

    @Override
    public void init() {
        // create and add background image layer
        final GroupLayer group = graphics().createGroupLayer();

        Image bgImage = assets().getImage("images/bg.png");
        bgLayer = graphics().createImageLayer(bgImage);
        group.add(bgLayer);

        worldLayer = graphics().createGroupLayer();
        worldLayer.setScale(1f / physUnitPerScreenUnit);
        graphics().rootLayer().add(worldLayer);

        world = new TestWorld(worldLayer);

        final Ball b = new Ball(world, world.world, 5, 10, Color.rgb(128, 128, 255));
        
        b.setLinearVelocity(3, 0);
    }

    @Override
    public void paint(float alpha) {
        world.paint(alpha);
    }

    @Override
    public void update(float delta) {
        world.update(delta);
    }

    @Override
    public int updateRate() {
        return 25;
    }
}
