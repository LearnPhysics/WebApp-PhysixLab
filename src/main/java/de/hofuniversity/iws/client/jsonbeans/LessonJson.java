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
public class LessonJson  extends JavaScriptObject {

    private LessonJson() {
    }

    public static native LessonJson create(String json) /*-{
     return eval('(' + json + ')');
     }-*/;

    public final native String getName() /*-{ 
     return this.name;
     }-*/;

    public final native String getThema() /*-{ 
     return this.thema;
     }-*/;

    public final native String getParent() /*-{ 
     return this.parent;
     }-*/;

    public final native String getTitle() /*-{ 
     return this.title;
     }-*/;

    public final native String getText() /*-{ 
     return this.text;
     }-*/;

    public final native String getImage() /*-{ 
     return this.image;
     }-*/;

    public final native String getWidget() /*-{ 
     return this.widget;
     }-*/;

    public final native String getMath() /*-{ 
     return this.math;
     }-*/;

    public final native TestJson getTest() /*-{ 
     return this.test;
     }-*/;
}
