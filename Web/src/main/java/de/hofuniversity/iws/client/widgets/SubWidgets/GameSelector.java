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
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.ui.*;
import com.google.inject.assistedinject.Assisted;
import de.hofuniversity.iws.client.HistoryPageController;
import de.hofuniversity.iws.client.jsonbeans.GameJson;
import javax.inject.Inject;

/**
 *
 * @author Oliver Schütz
 */
public class GameSelector extends Composite {

    interface GameSelectorUiBinder extends UiBinder<Widget, GameSelector> {
    }
    private static GameSelectorUiBinder uiBinder = GWT.create(GameSelectorUiBinder.class);
    private final HistoryPageController pageController;
    private GameJson game;
    @UiField HTMLPanel wrap;
    @UiField FocusPanel oImg;
    @UiField Image iImg;
    @UiField HeadingElement title;
    @UiField ParagraphElement text;
    
    public interface GameSelectorFactory{
        public GameSelector create(GameJson game);
    }

    @Inject
    public GameSelector(HistoryPageController pc, @Assisted GameJson game) {
        initWidget(uiBinder.createAndBindUi(this));
        pageController = pc;
        this.game = game;

        iImg.setUrl(game.getImageUrl());
        title.setInnerText(game.getTitle());
        text.setInnerText(game.getDescription());

        wrap.getElement().getStyle().setTop(180, Unit.PX);
    }

    @UiHandler("oImg")
    public void openGame(ClickEvent ev) {
        pageController.changeToGame(game);
    }
}
