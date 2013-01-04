/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.Thema;

import java.util.List;
import de.hofuniversity.iws.client.widgets.SubWidgets.GameSelector;
import de.hofuniversity.iws.shared.dto.GameDTO;
import de.hofuniversity.iws.shared.services.*;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

/**
 *
 * @author Oliver
 */
public class Spielwahl extends Composite {

    private static SpielwahlUiBinder uiBinder = GWT.create(SpielwahlUiBinder.class);
    private final LessonServiceAsync lessonService = (LessonServiceAsync) GWT.create(LessonService.class);
    @UiField
    VerticalPanel gamesPanel;

    interface SpielwahlUiBinder extends UiBinder<Widget, Spielwahl> {
    }

    public Spielwahl(String subject) {
        initWidget(uiBinder.createAndBindUi(this));

        lessonService.getGamesForSession(subject, new AsyncCallback<List<GameDTO>>() {
            @Override
            public void onFailure(Throwable caught) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void onSuccess(List<GameDTO> result) {
                for (GameDTO game : result) {
                    gamesPanel.add(new GameSelector(game));
                }
            }
        });
    }
}
