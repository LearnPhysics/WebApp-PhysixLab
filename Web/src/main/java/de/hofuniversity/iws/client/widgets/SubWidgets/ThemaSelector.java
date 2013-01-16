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
import com.google.gwt.dom.client.*;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.ui.*;
import com.google.inject.assistedinject.Assisted;
import de.hofuniversity.iws.client.HistoryPageController;
import de.hofuniversity.iws.client.jsonbeans.SubjectJson;
import de.hofuniversity.iws.shared.services.Utilities;
import javax.inject.Inject;

/**
 *
 * @author Oliver Schütz
 */
public class ThemaSelector extends Composite {

    interface ThemaSelectorUiBinder extends UiBinder<Widget, ThemaSelector> {
    }
    private static ThemaSelectorUiBinder uiBinder = GWT.create(ThemaSelectorUiBinder.class);
    private static final int MAX_PREVIEW_LENGTH = 300;
    @UiField HTMLPanel wrap;
    @UiField FocusPanel oImg;
    @UiField Image iImg;
    @UiField HeadingElement title;
    @UiField ParagraphElement text;
    private final SubjectJson thema;
    private final HistoryPageController pageController;

    public interface ThemaSelectorFactory {

        public ThemaSelector create(SubjectJson thema);
    }

    @Inject
    public ThemaSelector(HistoryPageController pc, @Assisted SubjectJson thema) {
        initWidget(uiBinder.createAndBindUi(this));
        this.thema = thema;
        pageController = pc;

        iImg.setUrl(thema.getImageUrl());
        title.setInnerText(thema.getTitle());
        text.setInnerText(Utilities.textShortener(thema.getText(), MAX_PREVIEW_LENGTH));

        wrap.getElement().getStyle().setTop(180, Style.Unit.PX);
    }

    @UiHandler("oImg")
    public void openThema(ClickEvent ev) {
        pageController.changeToThema(thema);
    }
}
