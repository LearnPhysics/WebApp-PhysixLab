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
public class PlayNWidget extends Composite{

    private final static String ROOT_ID = "playn-root";
    private final PhysicGame game;
    private final int width;

    public PlayNWidget(PhysicGame game, int width) {
        this.game = game;
        this.width = width;
        SimplePanel panel = new SimplePanel();
        panel.getElement().setId(ROOT_ID);
        initWidget(panel);
    }

    private void start() {
        HtmlPlatform.register();
        PlayN.run(new PhysicGameBox(game, width));
    }

    @Override
    protected void onAttach() {
        super.onAttach();
        start();
    }
}
