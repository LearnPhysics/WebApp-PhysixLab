/*
  * Copyright (C) 2012 Oliver Schütz
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
package de.hofuniversity.iws.client.widgets.SubWidgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.ui.*;

/**
 *
 * @author Oliver Schütz
 */
public class SplitterParagraph extends Composite {
    
    private static SplitterParagraphUiBinder uiBinder = GWT.create(SplitterParagraphUiBinder.class);
    @UiField ParagraphElement splitter;
    
    interface SplitterParagraphUiBinder extends UiBinder<Widget, SplitterParagraph> {
    }
    
    public SplitterParagraph() {
        initWidget(uiBinder.createAndBindUi(this));
        splitter.setInnerHTML("&nbsp; &gt; &nbsp;");
    }
}
