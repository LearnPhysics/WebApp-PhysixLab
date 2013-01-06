/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.Game;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.*;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.*;
import de.hofuniversity.iws.client.playn.PlayNWidget;
import de.hofuniversity.iws.client.util.AddressStack;
import de.hofuniversity.iws.client.util.CrumbTuple;
import de.hofuniversity.iws.client.widgets.SubWidgets.BackButton;
import de.hofuniversity.iws.client.widgets.SubWidgets.Breadcrumb;
import de.hofuniversity.iws.shared.dto.GameDTO;

/**
 *
 * @author Oliver
 */
public class Game extends Composite {

    public final static String NAME = "game";
    private static GameUiBinder uiBinder = GWT.create(GameUiBinder.class);
    private GameDTO game;
    @UiField
    ScrollPanel sWrap;
    @UiField
    HeadingElement title;
    @UiField
    VerticalPanel outerGame;
    @UiField
    PlayNWidget gamePanel;
    @UiField
    ParagraphElement beschreibung;
    @UiField HTMLPanel page;

    interface GameUiBinder extends UiBinder<Widget, Game> {
    }

    public Game(GameDTO bean) {
        game = bean;
        sWrap.getElement().getStyle().setOverflow(Style.Overflow.HIDDEN);
        sWrap.setVerticalScrollPosition(0);
        title.setInnerText(game.getTitle());
        beschreibung.setInnerText(game.getDescription());

        initWidget(uiBinder.createAndBindUi(this));
        History.newItem(NAME + "?" + bean.getDbo().getName(), false);
        
        AddressStack.getInstance().addAddress(new CrumbTuple(this, bean.getTitle(), 3));
        page.add(new Breadcrumb(3));
        page.add(new BackButton(3));
    }

    @Override
    protected void onAttach() {
        super.onAttach();
        outerGame.getElement().getStyle().setWidth(gamePanel.getOffsetWidth() + 20, Style.Unit.PX);
    }

    @UiFactory
    public PlayNWidget createGameWidget() {
        return new PlayNWidget(game.getWidgetFactory().create());
    }
}