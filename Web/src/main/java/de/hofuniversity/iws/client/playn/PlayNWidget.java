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
public class PlayNWidget extends Composite implements GameEventListener {

    private final static String ROOT_ID = "playn-root";
    private final PhysicGame game;
    private final SimpleLayoutPanel panel = new SimpleLayoutPanel();
    private StopableHtmlPlatform platform;
    private GameEventListener listener;

    public PlayNWidget(PhysicGame game) {
        this.game = game;
        panel.getElement().setId(ROOT_ID);
        initWidget(panel);
    }

    private void start() {
        PhysicGameBox gameBox = new PhysicGameBox(game, panel.getOffsetWidth(), this);
        panel.setHeight(gameBox.getHeight() + "px");
        Configuration conf = new Configuration();
        conf.antiAliasing = true;
        conf.mode = Mode.CANVAS;
        platform = StopableHtmlPlatform.register(conf);
        PlayN.run(gameBox);
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

    @Override
    public void gameEnded(GameEvent ev) {
        if (listener != null) {
            listener.gameEnded(ev);
        }
    }

    public void setListener(GameEventListener listener) {
        this.listener = listener;
    }

    public void restartGame() {
        game.restart();
    }
}
