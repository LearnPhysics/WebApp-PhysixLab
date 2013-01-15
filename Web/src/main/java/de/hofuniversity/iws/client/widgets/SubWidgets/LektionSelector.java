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
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.ui.*;
import com.google.inject.assistedinject.Assisted;
import de.hofuniversity.iws.client.HistoryPageController;
import de.hofuniversity.iws.client.jsonbeans.SubjectJson;
import de.hofuniversity.iws.client.util.SuccessCallback;
import de.hofuniversity.iws.client.widgets.Lektion.Lektion;
import de.hofuniversity.iws.client.widgets.Lektion.Lektion.Builder;
import de.hofuniversity.iws.shared.dto.LessonPreview;
import javax.inject.*;

/**
 *
 * @author Oliver Schütz
 */
public class LektionSelector extends Composite {

    interface LektionSelectorUiBinder extends UiBinder<Widget, LektionSelector> {
    }
    private static LektionSelectorUiBinder uiBinder = GWT.create(LektionSelectorUiBinder.class);
    private final LessonPreview lektion;
    private final SubjectJson subject;
    private final LektionPreview preview;
    private final HistoryPageController pageController;
    private final Provider<Builder> lektionsBuilder;
    @UiField HTMLPanel wrap;
    @UiField FocusPanel oImg;
    @UiField Image iImg;

    public interface LektionSelectorFactory {

        public LektionSelector create(LessonPreview lektion, SubjectJson subject, int x, int y);
    }

    @Inject
    public LektionSelector(Provider<Builder> lektionsBuilder, HistoryPageController pc, @Assisted LessonPreview lektion,
                           @Assisted SubjectJson subject, @Assisted int x, @Assisted int y) {
        initWidget(uiBinder.createAndBindUi(this));
        this.pageController = pc;
        this.lektion = lektion;
        this.subject = subject;
        this.lektionsBuilder = lektionsBuilder;

        iImg.setUrl(lektion.getImageUrl());
        wrap.getElement().getStyle().setLeft(x, Style.Unit.PX);
        wrap.getElement().getStyle().setTop(195 + y, Style.Unit.PX);

        preview = new LektionPreview(lektion);
        preview.setVisible(false);
        wrap.add(preview);

        oImg.addMouseOverHandler(new VisibleHandler());
        oImg.addMouseOutHandler(new InvisibleHandler());
    }

    @UiHandler("oImg")
    public void openLektion(ClickEvent ev) {
        lektionsBuilder.get()
                .withLesson(lektion.getName())
                .withSubject(subject).create(new SuccessCallback<Lektion>() {
            @Override
            public void onSuccess(Lektion result) {
                pageController.changePage(result);
            }
        });
    }

    private class VisibleHandler implements MouseOverHandler {

        @Override
        public void onMouseOver(MouseOverEvent event) {
            preview.setVisible(true);
        }
    }

    private class InvisibleHandler implements MouseOutHandler {

        @Override
        public void onMouseOut(MouseOutEvent event) {
            preview.setVisible(false);
        }
    }
}
