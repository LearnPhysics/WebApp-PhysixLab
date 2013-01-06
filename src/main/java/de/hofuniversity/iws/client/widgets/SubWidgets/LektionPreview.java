/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.SubWidgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.dom.client.Style;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import de.hofuniversity.iws.shared.dto.LessonPreview;

/**
 *
 * @author Oliver
 */
public class LektionPreview extends Composite {
    
    private static LektionPreviewUiBinder uiBinder = GWT.create(LektionPreviewUiBinder.class);
    
    @UiField HTMLPanel plate;
    @UiField HeadingElement title;
    @UiField ParagraphElement text;
    
    interface LektionPreviewUiBinder extends UiBinder<Widget, LektionPreview> {
    }
    
    public LektionPreview() {
        initWidget(uiBinder.createAndBindUi(this));
    }
    
    public LektionPreview(LessonPreview lesson) {
        initWidget(uiBinder.createAndBindUi(this));
        title.setInnerText(lesson.getName());
    }
}