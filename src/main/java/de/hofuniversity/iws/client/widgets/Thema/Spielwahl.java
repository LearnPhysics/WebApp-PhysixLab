/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.Thema;


import de.hofuniversity.iws.client.widgets.SubWidgets.GameSelector;
import de.hofuniversity.iws.shared.dto.GameDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.ui.*;

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

    public Spielwahl(Iterable<GameDTO> games) {
        initWidget(uiBinder.createAndBindUi(this));
        if (games != null) {
            for (GameDTO game : games) {
                gamesPanel.add(new GameSelector(game));
            }
        }
    }
}
