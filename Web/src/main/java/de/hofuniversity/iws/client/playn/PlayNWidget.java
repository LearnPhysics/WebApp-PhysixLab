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
        conf.mode = Mode.AUTODETECT;
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
