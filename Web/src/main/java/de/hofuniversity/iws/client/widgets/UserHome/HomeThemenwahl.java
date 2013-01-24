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
package de.hofuniversity.iws.client.widgets.UserHome;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import de.hofuniversity.iws.client.jsonbeans.SubjectJson;
import de.hofuniversity.iws.client.widgets.SubWidgets.ThemaSelector.ThemaSelectorFactory;
import de.hofuniversity.iws.shared.services.LessonServiceAsync;
import javax.inject.Inject;

/**
 *
 * @author Oliver Schütz
 */
public class HomeThemenwahl extends Composite {

    interface HomeThemenwahlUiBinder extends UiBinder<Widget, HomeThemenwahl> {
    }
    private static HomeThemenwahlUiBinder uiBinder = GWT.create(HomeThemenwahlUiBinder.class);
    @UiField VerticalPanel themaPanel;
    @UiField ParagraphElement text;

    @Inject
    public HomeThemenwahl(LessonServiceAsync lessonService, final ThemaSelectorFactory factory) {
        initWidget(uiBinder.createAndBindUi(this));

        lessonService.getSubjects(new AsyncCallback<String[]>() {
            @Override
            public void onFailure(Throwable caught) {
                throw new UnsupportedOperationException(caught.getLocalizedMessage());
            }
            
            /**
             * Add all subject to the selctiion list
             */
            @Override
            public void onSuccess(String[] result) {
                for (String thema : result) {
                    themaPanel.add(factory.create(SubjectJson.create(thema)));
                }
            }
        });

        text.setInnerText("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, "
                          + "sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, "
                          + "sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. "
                          + "Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. "
                          + "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod "
                          + "tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. "
                          + "At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, "
                          + "no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, "
                          + "consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut "
                          + "labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et "
                          + "justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata "
                          + "sanctus est Lorem ipsum dolor sit amet.   Duis autem vel eum iriure dolor in "
                          + "hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat "
                          + "nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit "
                          + "praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. "
                          + "Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy "
                          + "nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.");
    }
}
