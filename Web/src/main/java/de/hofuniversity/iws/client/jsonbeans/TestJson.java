/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.jsonbeans;

import com.google.gwt.core.client.JavaScriptObject;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class TestJson   extends JavaScriptObject {

    protected TestJson() {
    }

    public static native TestJson create(String json) /*-{
     return eval('(' + json + ')');
     }-*/;

    public final native String getTitle() /*-{ 
     return this.title;
     }-*/;

    public final native String getProblem() /*-{ 
     return this.problem;
     }-*/;

    public final native double getSolution() /*-{ 
     return this.solution;
     }-*/;

    public final native String getWidget() /*-{ 
     return this.widget;
     }-*/;

    public final native String getImage() /*-{ 
     return this.image;
     }-*/;
}
