/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.Lektion;

import de.hofuniversity.iws.shared.dto.LektionDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.*;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.*;

/**
 *
 * @author Oliver
 */
public class Lektion extends Composite {

    public final static String NAME = "lektion";
    private static LektionUiBinder uiBinder = GWT.create(LektionUiBinder.class);
    @UiField
    Lektion.LektionStyle style;
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

    public Lektion(LektionDTO lektion) {
        initWidget(uiBinder.createAndBindUi(this));
        History.newItem(NAME, false);
        sWrap.getElement().getStyle().setOverflow(Style.Overflow.HIDDEN);
        sWrap.setVerticalScrollPosition(0);

        railContent.add(new Lesson(lektion));
        railContent.add(new Test(lektion.getTest()));
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
        switch (pos) {
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