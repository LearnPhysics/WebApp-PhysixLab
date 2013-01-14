/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.SubWidgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.ui.*;
import com.google.inject.assistedinject.*;
import de.hofuniversity.iws.client.HistoryPageController;
import de.hofuniversity.iws.client.jsonbeans.SubjectJson;
import de.hofuniversity.iws.client.widgets.Lektion.Lektion;
import de.hofuniversity.iws.client.widgets.Lektion.Lektion.Builder;
import de.hofuniversity.iws.shared.dto.LessonPreview;

/**
 *
 * @author Oliver
 */
public class LektionSelector extends Composite {

    interface LektionSelectorUiBinder extends UiBinder<Widget, LektionSelector> {
    }
    private static LektionSelectorUiBinder uiBinder = GWT.create(LektionSelectorUiBinder.class);
    private final LessonPreview lektion;
    private final SubjectJson subject;
    private final LektionPreview preview;
    private final HistoryPageController pageController;
    private final Builder lektionsBuilder;
    @UiField HTMLPanel wrap;
    @UiField FocusPanel oImg;
    @UiField Image iImg;

    public interface LektionSelectorFactory {

        public LektionSelector create(LessonPreview lektion, SubjectJson subject, int x, int y);
    }

    @AssistedInject
    public LektionSelector(Builder lektionsBuilder, HistoryPageController pc, @Assisted LessonPreview lektion,
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
        Lektion l = lektionsBuilder
                .withLesson(lektion.getName())
                .withSubject(subject).create();
        pageController.changeToLektion(l);
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
