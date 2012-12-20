/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.Thema;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.ui.*;
import de.hofuniversity.iws.client.widgets.SubWidgets.GameSelector;
import de.hofuniversity.iws.client.widgets.TestEntities.*;

/**
 *
 * @author Oliver
 */
public class Spielwahl extends Composite {

    private static SpielwahlUiBinder uiBinder = GWT.create(SpielwahlUiBinder.class);
    @UiField
    VerticalPanel gamesPanel;

    interface SpielwahlUiBinder extends UiBinder<Widget, Spielwahl> {
    }

    public Spielwahl() {
        initWidget(uiBinder.createAndBindUi(this));
        List<TestGame> games = EntityHolder.getInstance().getThema().getGames();
        if (games != null) {
            for (TestGame game : games) {
                gamesPanel.add(new GameSelector(game));
            }
        }
    }
}
