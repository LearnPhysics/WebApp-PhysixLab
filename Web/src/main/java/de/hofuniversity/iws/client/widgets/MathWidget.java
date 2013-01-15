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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.*;

/**
 *
 * @author Daniel Heinrich <DannyNullZwo@gmail.com>
 */
public class MathWidget extends Composite {

    private static final class Updater extends JavaScriptObject {

        protected Updater() {
        }

        public native void updateTex(String tex)/*-{   
         this(tex);
         }-*/;
    }
    private static int count = 0;
    private final Element div;
    private Updater updater;
    private String text;

    public MathWidget() {
        HTMLPanel panel = new HTMLPanel("div", "");
        initWidget(panel);

        div = panel.getElement();
        div.setId("MathWidget" + (count++));
        div.setInnerHTML("\\[\\]");
    }

    public void setMathText(String text) {
        this.text = text;
        if (updater != null && text != null) {
            updater.updateTex(text);
        }
    }

    @Override
    protected void onLoad() {
        renderLatexResult(div);
        updater = ini(div.getId());
        setMathText(text);
    }

    @Override
    protected void onDetach() {
        super.onDetach();
        updater = null;
    }

    public final native void renderLatexResult(com.google.gwt.dom.client.Element e) /*-{
     $wnd.MathJax.Hub.Typeset(e);
     }-*/;

    private final native Updater ini(String id)/*-{   
        var HUB =  $wnd.MathJax.Hub;  // shorthand for the queue
        var QUEUE =  HUB.queue;  // shorthand for the queue

        //
        //  Get the element jax when MathJax has produced it.
        //
     var math = null;
          QUEUE.Push(function () {
          math = HUB.getAllJax(id)[0];
          });
     
          //
          //  The onchange event handler that typesets the
          //  math entered by the user
          //
          var update = function(TeX) {
            QUEUE.Push(function () {
            math.Text("\\displaystyle{"+TeX+"}");
            });
          };
          
          return update;
     }-*/;
}
