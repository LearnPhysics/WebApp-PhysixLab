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
