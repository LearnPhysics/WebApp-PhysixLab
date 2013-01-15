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
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.ui.*;
import com.google.inject.assistedinject.Assisted;
import de.hofuniversity.iws.client.HistoryPageController;
import de.hofuniversity.iws.client.widgets.history.HistoryElement;
import javax.inject.Inject;

/**
 *
 * @author Oliver Schütz
 */
public class Crumb extends Composite {

    interface CrumbUiBinder extends UiBinder<Widget, Crumb> {
    }
    private static CrumbUiBinder uiBinder = GWT.create(CrumbUiBinder.class);
    private HistoryElement element;
    private final HistoryPageController pageController;
    @UiField FocusPanel action;
    @UiField ParagraphElement crumb;

    public interface CrumbFactory {

        public Crumb create(HistoryElement element);
    }

    @Inject
    public Crumb(HistoryPageController pageController, @Assisted HistoryElement element) {
        initWidget(uiBinder.createAndBindUi(this));
        this.pageController = pageController;
        this.element = element;
        crumb.setInnerText(" " + element.getTtile() + " ");
    }

    @UiHandler("action")
    public void openGame(ClickEvent ev) {
        pageController.changePage(element.createWidget());
    }
}