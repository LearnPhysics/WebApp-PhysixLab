/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.playn;

import com.google.gwt.user.client.ui.*;
import playn.core.PlayN;
import playn.html.HtmlPlatform;
import playn.html.HtmlPlatform.Configuration;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class PlayNWidget extends Composite {

    private final static String ROOT_ID = "playn-root";
    private final PhysicGame game;
    private final SimpleLayoutPanel panel = new SimpleLayoutPanel();

    public PlayNWidget(PhysicGame game) {
        this.game = game;
        panel.getElement().setId(ROOT_ID);
        initWidget(panel);
    }

    private void start() {
        PhysicGameBox g = new PhysicGameBox(game, panel.getOffsetWidth());
        panel.setHeight(g.getHeight() + "px");
        Configuration conf  = new Configuration();
        conf.antiAliasing = true;
//        conf.mode = Mode.CANVAS;
        HtmlPlatform.register(conf);
        PlayN.run(g);
    }

    @Override
    protected void onAttach() {
        super.onAttach();
        start();
    }
}
