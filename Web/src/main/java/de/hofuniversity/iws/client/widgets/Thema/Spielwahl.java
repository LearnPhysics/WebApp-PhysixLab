/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.Thema;

import com.google.gwt.core.client.*;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.ui.*;
import com.google.inject.assistedinject.*;
import de.hofuniversity.iws.client.jsonbeans.GameJson;
import de.hofuniversity.iws.client.widgets.SubWidgets.GameSelector.GameSelectorFactory;
import javax.inject.Inject;

/**
 *
 * @author Oliver
 */
public class Spielwahl extends Composite {

    interface SpielwahlUiBinder extends UiBinder<Widget, Spielwahl> {
    }
    private static SpielwahlUiBinder uiBinder = GWT.create(SpielwahlUiBinder.class);
    @UiField VerticalPanel gamesPanel;

    public interface SpielwahlFactory {

        public Spielwahl create(JsArray<GameJson> games);
    }

    @Inject
    public Spielwahl(GameSelectorFactory factory, @Assisted JsArray<GameJson> games) {
        initWidget(uiBinder.createAndBindUi(this));
        for (int i = 0; i < games.length(); i++) {
            gamesPanel.add(factory.create(games.get(i)));
        }
    }
}
