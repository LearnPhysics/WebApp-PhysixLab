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
import de.hofuniversity.iws.client.PhysixLab;
import de.hofuniversity.iws.client.jsonbeans.SubjectJson;
import de.hofuniversity.iws.client.widgets.Thema.Thema;

/**
 *
 * @author Oliver
 */
public class ThemaSelector extends Composite {
    
    private static ThemaSelectorUiBinder uiBinder = GWT.create(ThemaSelectorUiBinder.class);
    
    @UiField HTMLPanel wrap;
    @UiField FocusPanel oImg;
    @UiField Image iImg;
    @UiField HeadingElement title;
    @UiField ParagraphElement text;
    
    private final SubjectJson thema;
    
    interface ThemaSelectorUiBinder extends UiBinder<Widget, ThemaSelector> {
    }
    
    public ThemaSelector(SubjectJson thema) {
        initWidget(uiBinder.createAndBindUi(this));
        this.thema = thema;
        
        iImg.setUrl(thema.getImageUrl());
        title.setInnerText(thema.getTitle());
        text.setInnerText(thema.getText());
        
        wrap.getElement().getStyle().setTop(180, Style.Unit.PX);
    }
    
    @UiHandler("oImg")
    public void openGame(ClickEvent ev) {
        PhysixLab.PAGE_CONTROLLER.changePage(new Thema(thema));
    }
}
