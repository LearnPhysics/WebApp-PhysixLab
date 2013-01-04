/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.UserHome;

import java.util.*;

import de.hofuniversity.iws.client.widgets.SubWidgets.ThemaSelector;
import de.hofuniversity.iws.shared.dto.ThemaDTO;
import de.hofuniversity.iws.shared.services.*;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.inject.Inject;

/**
 *
 * @author Oliver
 */
public class HomeThemenwahl extends Composite {

    private static HomeThemenwahlUiBinder uiBinder = GWT.create(HomeThemenwahlUiBinder.class);
    @Inject
    private final LessonServiceAsync lessonService = (LessonServiceAsync) GWT.create(LessonService.class);
    @UiField
    VerticalPanel themaPanel;

    interface HomeThemenwahlUiBinder extends UiBinder<Widget, HomeThemenwahl> {
    }

    public HomeThemenwahl() {
        initWidget(uiBinder.createAndBindUi(this));

        lessonService.readThemes(new AsyncCallback<List<ThemaDTO>>() {
            @Override
            public void onFailure(Throwable caught) {
                throw new UnsupportedOperationException(caught.getLocalizedMessage());
            }

            @Override
            public void onSuccess(List<ThemaDTO> result) {
                for (ThemaDTO thema : result) {
                    themaPanel.add(new ThemaSelector(thema));
                }
            }
        });
    }
}
