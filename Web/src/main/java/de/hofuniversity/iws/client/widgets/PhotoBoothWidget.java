/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
