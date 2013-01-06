/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.playn;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.*;
import playn.core.PlayN;
import playn.html.HtmlPlatform.Configuration;
import playn.html.HtmlPlatform.Mode;
import playn.html.StopableHtmlPlatform;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class PlayNWidget extends Composite {

    private final static String ROOT_ID = "playn-root";
    private final PhysicGame game;
    private final SimpleLayoutPanel panel = new SimpleLayoutPanel();
    private StopableHtmlPlatform platform;

    public PlayNWidget(PhysicGame game) {
        this.game = game;
        panel.getElement().setId(ROOT_ID);
        initWidget(panel);
    }

    private void start() {
        PhysicGameBox g = new PhysicGameBox(game, panel.getOffsetWidth());
        panel.setHeight(g.getHeight() + "px");
        Configuration conf = new Configuration();
        conf.antiAliasing = true;
        conf.mode = Mode.CANVAS;
        platform = StopableHtmlPlatform.register(conf);
        PlayN.run(g);
    }

    @Override
    protected void onAttach() {
        super.onAttach();
        new Timer() {
            @Override
            public void run() {
                start();
            }
        }.schedule(200);
    }

    @Override
    protected void onDetach() {
        super.onDetach();
        if (platform != null) {
            platform.stop();
            platform = null;
        }
    }
}
