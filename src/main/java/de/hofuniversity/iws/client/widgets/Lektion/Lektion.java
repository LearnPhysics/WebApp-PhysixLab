/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.Lektion;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import de.hofuniversity.iws.client.widgets.TestEntities.TestLektion;
import de.hofuniversity.iws.client.widgets.Thema.Thema;

/**
 *
 * @author Oliver
 */
public class Lektion extends Composite {

    public final static String NAME = "lektion";
    private static LektionUiBinder uiBinder = GWT.create(LektionUiBinder.class);
    private TestLektion lektion;
    
    @UiField Lektion.LektionStyle style;
    @UiField SpanElement rail;
    @UiField ScrollPanel sWrap;
    
    @UiField FocusPanel tab1;
    @UiField FocusPanel tab2;

    interface LektionUiBinder extends UiBinder<Widget, Lektion> {
    }

    interface LektionStyle extends CssResource {

        String posLektion();
        String posTest();
        
        String selected();
        
        String tab();
        String tab1();
        String tab2();
    }
    
    public Lektion() {
        initWidget(uiBinder.createAndBindUi(this));
        History.newItem(NAME);
        sWrap.getElement().getStyle().setOverflow(Style.Overflow.HIDDEN);
        sWrap.setVerticalScrollPosition(0);
    }
  
    @UiHandler("tab1")
    public void changeToTab1(ClickEvent ev) {
        setPosition(1);
    }
    @UiHandler("tab2")
    public void changeToTab2(ClickEvent ev) {
        setPosition(2);
    }
    
     public void setPosition(int pos) {
        unselectAllTabs();
        switch(pos) {
            case 1:
                rail.removeClassName(rail.getClassName());
                rail.setClassName(style.posLektion());
                tab1.addStyleName(style.selected());
                break;
            case 2:
                rail.removeClassName(rail.getClassName());
                rail.setClassName(style.posTest());
                tab2.addStyleName(style.selected());
                break;
            default:
                System.err.println("Wrong parameter! Only 1 to 2 allowed. Given was: " + pos);
        }
    }
     
     private void unselectAllTabs() {
        tab1.removeStyleName(style.selected());
        tab2.removeStyleName(style.selected());
    }
}