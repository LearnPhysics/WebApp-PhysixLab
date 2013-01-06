/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.jsonbeans;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class SubjectJson extends JavaScriptObject {

    protected SubjectJson() {
    }

    public static native SubjectJson create(String json) /*-{
     return eval('(' + json + ')');
     }-*/;

    public final native String getName() /*-{ 
     return this.name;
     }-*/;

    public final native String getTitle() /*-{ 
     return this.title;
     }-*/;

    public final native String getText() /*-{ 
     return this.text;
     }-*/;

    public final native String getImageUrl() /*-{ 
     return this.image;
     }-*/;
    
    public final native JsArray<GameJson> getGames() /*-{ 
     return this.games;
     }-*/;
}
