/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.SubWidgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.ui.*;

/**
 *
 * @author Oliver
 */
public class SplitterParagraph extends Composite {
    
    private static SplitterParagraphUiBinder uiBinder = GWT.create(SplitterParagraphUiBinder.class);
    @UiField ParagraphElement splitter;
    
    interface SplitterParagraphUiBinder extends UiBinder<Widget, SplitterParagraph> {
    }
    
    public SplitterParagraph() {
        initWidget(uiBinder.createAndBindUi(this));
        splitter.setInnerHTML("&nbsp; &gt; &nbsp;");
    }
}
