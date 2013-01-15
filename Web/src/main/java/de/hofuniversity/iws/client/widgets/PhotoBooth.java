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

import java.util.*;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public final class PhotoBooth extends JavaScriptObject {

    public static interface Listener {

        public void onImage(String url);
    }

    public static class ListenerCollection {

        private final List<Listener> listeners = new ArrayList<Listener>();

        public void addListener(Listener l) {
            listeners.add(l);
        }

        public void onImage(String url) {
            for (Listener l : listeners) {
                l.onImage(url);
            }
        }
    }

    protected PhotoBooth() {
    }

    public static native PhotoBooth createOn(Element el)/*-{   
     var photo = new $wnd.Photobooth(el);
     return photo;
     }-*/;

    public ListenerCollection createListenerCollection() {
        ListenerCollection lc = new ListenerCollection();
        setListener(lc);
        return lc;
    }

    public native void setListener(ListenerCollection l)/*-{  
     var lc = l;
     this.onImage = function(dataUrl){
        lc.@de.hofuniversity.iws.client.widgets.PhotoBooth.ListenerCollection::onImage(Ljava/lang/String;)(dataUrl);
     };
     }-*/;

    public void resize(int width, int height) {
        nativeResize(this, Math.max(200, width), Math.max(200, height));
    }

    public native void nativeResize(JavaScriptObject booth, int width, int height)/*-{
     booth.resize(width, height);
     }-*/;

    public native void pause()/*-{
     this.pause();
     }-*/;

    public native void resume()/*-{
     this.resume();
     }-*/;

    public native void destroy()/*-{
     this.destroy();
     }-*/;

    public native boolean isSupported()/*-{
     return this.isSupported; 
     }-*/;
}
