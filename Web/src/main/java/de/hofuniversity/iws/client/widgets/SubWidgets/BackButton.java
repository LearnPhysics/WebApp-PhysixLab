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
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.ui.*;
import com.google.inject.assistedinject.Assisted;
import de.hofuniversity.iws.client.*;
import de.hofuniversity.iws.client.widgets.history.HistoryElement;
import javax.inject.Inject;

/**
 *
 * @author Oliver Schütz
 */
public class BackButton extends Composite {

    interface BackButtonUiBinder extends UiBinder<Widget, BackButton> {
    }
    private static BackButtonUiBinder uiBinder = GWT.create(BackButtonUiBinder.class);
    private final HistoryPageController pageController;
    private final PhysixLab lab;
    private final HistoryElement element;
    @UiField FocusPanel back;

    public interface BackButtonFactory {

        public BackButton create(HistoryElement element);
    }

    @Inject
    public BackButton(HistoryPageController pageController, PhysixLab lab,
                      @Assisted HistoryElement element) {
        initWidget(uiBinder.createAndBindUi(this));
        this.pageController = pageController;
        this.lab = lab;
        this.element = element;
        if (!element.hasParent()) {
            back.setVisible(false);
        }
    }

    @UiHandler("back")
    public void goBack(ClickEvent ev) {
        if (element.hasParent()) {
            pageController.changePage(element.getParent().createWidget());
        }
    }

    @UiHandler("logout")
    public void logout(ClickEvent ev) {
        lab.logout();
    }
}