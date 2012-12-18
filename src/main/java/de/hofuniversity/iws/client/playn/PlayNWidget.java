/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.playn;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.*;
import playn.core.*;
import playn.html.HtmlPlatform;

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
        HtmlPlatform.register();
        PhysicGameBox g = new PhysicGameBox(game, panel.getOffsetWidth());
        panel.setHeight(g.getHeight() + "px");
        PlayN.run(g);
    }

    @Override
    protected void onAttach() {
        super.onAttach();
        start();
    }
}
