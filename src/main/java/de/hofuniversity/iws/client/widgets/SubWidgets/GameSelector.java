/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.SubWidgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import de.hofuniversity.iws.client.PhysixLab;
import de.hofuniversity.iws.client.widgets.Game.Game;
import de.hofuniversity.iws.client.widgets.LoginPage;
import de.hofuniversity.iws.client.widgets.TestEntities.EntityHolder;
import de.hofuniversity.iws.client.widgets.TestEntities.TestGame;
import de.hofuniversity.iws.client.widgets.Thema.Thema;

/**
 *
 * @author Oliver
 */
public class GameSelector extends Composite {

    private static GameSelectorUiBinder uiBinder = GWT.create(GameSelectorUiBinder.class);
    @UiField
    HTMLPanel wrap;
    @UiField
    FocusPanel oImg;
    @UiField
    Image iImg;
    @UiField
    HeadingElement title;
    @UiField
    ParagraphElement text;
    private TestGame game;

    interface GameSelectorUiBinder extends UiBinder<Widget, GameSelector> {
    }

    public GameSelector() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    public GameSelector(TestGame game) {
        this();
        this.game = game;
        
        iImg.setUrl(game.getImageURL());
        title.setInnerText(game.getTitle());
        text.setInnerText(game.getDescription());

        wrap.getElement().getStyle().setTop(180, Unit.PX);
    }

    @UiHandler("oImg")
    public void openGame(ClickEvent ev) {
        EntityHolder.getInstance().setGame(game);
        PhysixLab.PAGE_CONTROLLER.changePage(Game.NAME);
    }
}