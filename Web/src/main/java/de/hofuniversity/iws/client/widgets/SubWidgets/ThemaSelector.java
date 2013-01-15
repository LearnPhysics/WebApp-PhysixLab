/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.SubWidgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.*;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.ui.*;
import com.google.inject.assistedinject.*;
import de.hofuniversity.iws.client.HistoryPageController;
import de.hofuniversity.iws.client.jsonbeans.SubjectJson;
import de.hofuniversity.iws.shared.services.Utilities;
import javax.inject.Inject;

/**
 *
 * @author Oliver
 */
public class ThemaSelector extends Composite {

    interface ThemaSelectorUiBinder extends UiBinder<Widget, ThemaSelector> {
    }
    private static ThemaSelectorUiBinder uiBinder = GWT.create(ThemaSelectorUiBinder.class);
    private static final int MAX_PREVIEW_LENGTH = 300;
    @UiField HTMLPanel wrap;
    @UiField FocusPanel oImg;
    @UiField Image iImg;
    @UiField HeadingElement title;
    @UiField ParagraphElement text;
    private final SubjectJson thema;
    private final HistoryPageController pageController;

    public interface ThemaSelectorFactory {

        public ThemaSelector create(SubjectJson thema);
    }

    @Inject
    public ThemaSelector(HistoryPageController pc, @Assisted SubjectJson thema) {
        initWidget(uiBinder.createAndBindUi(this));
        this.thema = thema;
        pageController = pc;

        iImg.setUrl(thema.getImageUrl());
        title.setInnerText(thema.getTitle());
        text.setInnerText(Utilities.textShortener(thema.getText(), MAX_PREVIEW_LENGTH));

        wrap.getElement().getStyle().setTop(180, Style.Unit.PX);
    }

    @UiHandler("oImg")
    public void openThema(ClickEvent ev) {
        pageController.changeToThema(thema);
    }
}
