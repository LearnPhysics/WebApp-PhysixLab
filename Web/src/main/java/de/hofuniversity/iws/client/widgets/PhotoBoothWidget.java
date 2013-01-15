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
package de.hofuniversity.iws.client.widgets;

import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.*;
import de.hofuniversity.iws.client.widgets.PhotoBooth.Listener;
import de.hofuniversity.iws.client.widgets.PhotoBooth.ListenerCollection;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class PhotoBoothWidget extends ResizeComposite {

    private ListenerCollection listeners = new ListenerCollection();
    private PhotoBooth booth;

    public PhotoBoothWidget() {
        Panel p = new SimpleLayoutPanel();
        initWidget(p);
        Style style = getElement().getStyle();
        style.setProperty("minWidth", 200, Unit.PX);
        style.setProperty("minHeight", 200, Unit.PX);
    }

    @Override
    protected void onLoad() {
        super.onLoad();
        booth = PhotoBooth.createOn(getElement());
        booth.setListener(listeners);
        new Timer() {
            @Override
            public void run() {
                onResize();
            }
        }.schedule(200);
    }

    @Override
    protected void onAttach() {
        super.onAttach();
        onResize();
    }

    @Override
    protected void onUnload() {
        super.onUnload();
        booth.destroy();
    }

    public void addListener(Listener l) {
        listeners.addListener(l);
    }

    @Override
    public void onResize() {
        super.onResize();
        if (booth != null) {
            booth.resize(getOffsetWidth(), getOffsetHeight());
        }
    }
}
