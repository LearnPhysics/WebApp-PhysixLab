/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.SubWidgets;

import de.hofuniversity.iws.client.PhysixLab;
import de.hofuniversity.iws.client.widgets.Thema.Thema;
import de.hofuniversity.iws.shared.dto.ThemaDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.*;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.ui.*;

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
    
    private ThemaDTO themaBean;
    
    interface ThemaSelectorUiBinder extends UiBinder<Widget, ThemaSelector> {
    }
    
    public ThemaSelector() {
        initWidget(uiBinder.createAndBindUi(this));
    }
    
    public ThemaSelector(ThemaDTO thema) {
        initWidget(uiBinder.createAndBindUi(this));
        this.themaBean = thema;
        setup();
    }
    
    private void setup() {
        iImg.setUrl(themaBean.getImageURL());
        title.setInnerText(themaBean.getTitle());
        text.setInnerText(themaBean.getDescription());
        
        wrap.getElement().getStyle().setTop(180, Style.Unit.PX);
    }
    
    @UiHandler("oImg")
    public void openGame(ClickEvent ev) {
        PhysixLab.PAGE_CONTROLLER.changePage(new Thema(themaBean));
    }
}
