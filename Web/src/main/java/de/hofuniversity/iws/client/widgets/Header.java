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
package de.hofuniversity.iws.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.ui.*;
import de.hofuniversity.iws.client.HistoryPageController;
import de.hofuniversity.iws.client.widgets.UserHome.UserHome;
import javax.inject.Inject;

/**
 *
 * @author Oliver Schütz
 */
public class Header extends Composite {

    private static HeaderUiBinder uiBinder = GWT.create(HeaderUiBinder.class);

    interface HeaderUiBinder extends UiBinder<Widget, Header> {
    }
    private final HistoryPageController pageController;
    @UiField//so gwt-test-util is happy
    FocusPanel logo;

    @Inject
    public Header(HistoryPageController pageController) {
        initWidget(uiBinder.createAndBindUi(this));
        this.pageController = pageController;
    }

    @UiHandler("logo")
    public void goHome(ClickEvent ev) {
        pageController.changePage(UserHome.NAME);
    }
}
