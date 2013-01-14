/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.SubWidgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.*;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.ui.*;
import de.hofuniversity.iws.shared.dto.LessonPreview;

/**
 *
 * @author Oliver
 */
public class LektionPreview extends Composite {

    interface LektionPreviewUiBinder extends UiBinder<Widget, LektionPreview> {
    }
    private static LektionPreviewUiBinder uiBinder = GWT.create(LektionPreviewUiBinder.class);
    @UiField HTMLPanel plate;
    @UiField HeadingElement title;
    @UiField ParagraphElement text;

    public LektionPreview() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    public LektionPreview(LessonPreview lesson) {
        initWidget(uiBinder.createAndBindUi(this));
        title.setInnerText(lesson.getName());
    }
}