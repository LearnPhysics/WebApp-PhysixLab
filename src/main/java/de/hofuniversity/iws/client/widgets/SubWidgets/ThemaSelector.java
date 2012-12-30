/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.SubWidgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import de.hofuniversity.iws.client.PhysixLab;
import de.hofuniversity.iws.client.widgets.Game.Game;
import de.hofuniversity.iws.client.widgets.TestEntities.EntityHolder;
import de.hofuniversity.iws.client.widgets.TestEntities.TestGame;
import de.hofuniversity.iws.client.widgets.TestEntities.TestThema;
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
    
    private TestThema thema;
    
    interface ThemaSelectorUiBinder extends UiBinder<Widget, ThemaSelector> {
    }
    
    public ThemaSelector() {
        initWidget(uiBinder.createAndBindUi(this));
    }
    
    public ThemaSelector(TestThema thema) {
        initWidget(uiBinder.createAndBindUi(this));
        this.thema = thema;
        setup();
    }
    
    private void setup() {
        iImg.setUrl(thema.getImageURL());
        title.setInnerText(thema.getTitle());
        text.setInnerText(thema.getDescription());
        
        wrap.getElement().getStyle().setTop(180, Style.Unit.PX);
    }
    
    @UiHandler("oImg")
    public void openGame(ClickEvent ev) {
        EntityHolder.getInstance().setThema(thema);
        PhysixLab.PAGE_CONTROLLER.changePage(Thema.NAME);
    }
}
