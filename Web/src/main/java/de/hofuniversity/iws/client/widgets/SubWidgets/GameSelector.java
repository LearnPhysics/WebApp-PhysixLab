/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.SubWidgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.*;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.ui.*;
import com.google.inject.assistedinject.*;
import de.hofuniversity.iws.client.HistoryPageController;
import de.hofuniversity.iws.client.jsonbeans.GameJson;

/**
 *
 * @author Oliver
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

    @AssistedInject
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
