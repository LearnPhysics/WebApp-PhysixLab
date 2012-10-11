package de.hofuniversity.iws.core;

import playn.core.*;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;

import org.netbeans.saas.twitter.TwitterWhatAreYouDoingService;
import org.netbeans.saas.RestResponse;

public class WebApp implements Game {

    @Override
    public void init() {
        // create and add background image layer
        GroupLayer group = graphics().createGroupLayer();

        Image bgImage = assets().getImage("images/bg.png");

        ImageLayer bgLayer = graphics().createImageLayer(bgImage);
        group.add(bgLayer);

        CanvasImage img = graphics().createImage(128, 64);
        img.canvas().fillCircle(64, 32, 32);
        group.add(graphics().createImageLayer(img));

        graphics().rootLayer().add(group);
    }

    @Override
    public void paint(float alpha) {
        // the background automatically paints itself, so no need to do anything here!
    }

    @Override
    public void update(float delta) {
    }

    @Override
    public int updateRate() {
        return 25;
    }
}
