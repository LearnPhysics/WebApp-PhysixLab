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
package de.hofuniversity.iws.client.widgets.UserHome;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.ui.*;

/**
 *
 * @author Oliver Schütz
 */
public class HomeStatistik extends Composite {
    
    interface HomeStatistikUiBinder extends UiBinder<Widget, HomeStatistik> {
    }
    private static HomeStatistikUiBinder uiBinder = GWT.create(HomeStatistikUiBinder.class);
    
    @UiField ParagraphElement text;
    
    
    public HomeStatistik() {
        initWidget(uiBinder.createAndBindUi(this));
        
        text.setInnerText("Duis autem vel eum iriure dolor in hendrerit in vulputate "
                + "velit esse molestie consequat, vel illum dolore eu feugiat nulla "
                + "facilisis at vero eros et accumsan et iusto odio dignissim qui blandit "
                + "praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. "
                + "Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy "
                + "nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.");
    }
}
