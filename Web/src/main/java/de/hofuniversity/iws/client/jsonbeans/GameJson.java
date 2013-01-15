/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.jsonbeans;

import com.google.gwt.core.client.JavaScriptObject;

/**
 *
 * @author Daniel Heinrich <DannyNullZwo@gmail.com>
 */
public class GameJson   extends JavaScriptObject {

    protected GameJson() {
    }

    public static native GameJson create(String json) /*-{
     return eval('(' + json + ')');
     }-*/;

    public final native String getName() /*-{ 
     return this.name;
     }-*/;

    public final native String getTitle() /*-{ 
     return this.title;
     }-*/;

    public final native String getDescription() /*-{ 
     return this.description;
     }-*/;

    public final native String getWidget() /*-{ 
     return this.widget;
     }-*/;
    
    public final native String getImageUrl() /*-{ 
     return this.image;
     }-*/;
}