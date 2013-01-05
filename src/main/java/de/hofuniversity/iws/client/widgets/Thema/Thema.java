/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.Thema;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.*;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.*;
import de.hofuniversity.iws.client.jsonbeans.SubjectJson;

/**
 *
 * @author Oliver
 */
public class Thema extends Composite {

    public final static String NAME = "thema";
    private static ThemaUiBinder uiBinder = GWT.create(ThemaUiBinder.class);
    
    @UiField
    Thema.ThemaStyle style;
    @UiField
    SpanElement rail;
    @UiField
    HorizontalPanel railContent;
    @UiField
    ScrollPanel sWrap;
    @UiField
    FocusPanel tab1;
    @UiField
    FocusPanel tab2;
    @UiField
    FocusPanel tab3;

    interface ThemaUiBinder extends UiBinder<Widget, Thema> {
    }

    interface ThemaStyle extends CssResource {

        String posLektionswahl();

        String posUebersicht();

        String posSpielwahl();

        String selected();

        String tab();

        String tab1();

        String tab2();

        String tab3();
    }

    public Thema(SubjectJson bean) {
        initWidget(uiBinder.createAndBindUi(this));
        History.newItem(NAME + "?" + bean.getName(), false);
        sWrap.getElement().getStyle().setOverflow(Style.Overflow.HIDDEN);
        sWrap.setVerticalScrollPosition(0);
        
        railContent.add(new Lektionswahl(bean));
        railContent.add(new Uebersicht(bean));
        railContent.add(new Spielwahl(bean.getName()));
    }

    @UiHandler("tab1")
    public void changeToTab1(ClickEvent ev) {
        setPosition(1);
    }

    @UiHandler("tab2")
    public void changeToTab2(ClickEvent ev) {
        setPosition(2);
    }

    @UiHandler("tab3")
    public void changeToTab3(ClickEvent ev) {
        setPosition(3);
    }

    public void setPosition(int pos) {
        unselectAllTabs();
        switch (pos) {
            case 1:
                rail.removeClassName(rail.getClassName());
                rail.setClassName(style.posLektionswahl());
                tab1.addStyleName(style.selected());
                break;
            case 2:
                rail.removeClassName(rail.getClassName());
                rail.setClassName(style.posUebersicht());
                tab2.addStyleName(style.selected());
                break;
            case 3:
                rail.removeClassName(rail.getClassName());
                rail.setClassName(style.posSpielwahl());
                tab3.addStyleName(style.selected());
                break;
            default:
                System.err.println("Wrong parameter! Only 1 to 3 allowed. Given was: " + pos);
        }
    }

    private void unselectAllTabs() {
        tab1.removeStyleName(style.selected());
        tab2.removeStyleName(style.selected());
        tab3.removeStyleName(style.selected());
    }
}
