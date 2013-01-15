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
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.*;

/**
 *
 * @author Oliver Schütz
 */
public class HomeHeader extends Composite {

    interface HomeHeaderUiBinder extends UiBinder<Widget, HomeHeader> {
    }
    private static HomeHeaderUiBinder uiBinder = GWT.create(HomeHeaderUiBinder.class);

    public HomeHeader() {
        initWidget(uiBinder.createAndBindUi(this));
    }
}
