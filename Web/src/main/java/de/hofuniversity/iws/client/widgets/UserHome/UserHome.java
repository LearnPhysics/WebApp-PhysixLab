/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.UserHome;

import com.chrisgammage.ginjitsu.client.AfterInject;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.ui.*;
import de.hofuniversity.iws.client.widgets.SubWidgets.OnlyLogout;
import de.hofuniversity.iws.client.widgets.UserHome.UserHome.UserHomeUiBinder;
import de.hofuniversity.iws.client.widgets.base.CrumbPage;
import de.hofuniversity.iws.client.widgets.history.HistoryElement;
import de.hofuniversity.iws.client.widgets.history.UserHomeElement;
import javax.inject.Inject;

/**
 *
 * @author Oliver
 */
public class UserHome extends CrumbPage<UserHomeUiBinder> {

    public interface UserHomeUiBinder extends UiBinder<Widget, UserHome> {
    }
    public final static String NAME = "userHome";
    @UiField UserHomeStyle style;
    @UiField HTMLPanel page;
    @UiField SpanElement rail;
    @UiField FocusPanel tab1;
    @UiField FocusPanel tab2;
    @UiField FocusPanel tab3;
    @UiField FocusPanel tab4;
    @Inject @UiField(provided = true) HomeProfile profile;
    @Inject @UiField(provided = true) HomeThemenwahl themenwahl;
    @Inject @UiField(provided = true) HomeFreunde freunde;
    @Inject @UiField(provided = true) OnlyLogout logout;

    interface UserHomeStyle extends CssResource {

        String posStatistik();

        String posThemenwahl();

        String posFreunde();

        String posProfile();

        String selected();

        String tab();

        String tab1();

        String tab2();

        String tab3();

        String tab4();
    }

    @Inject
    public UserHome(UserHomeElement element) {
        super(element, NAME);
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

    @UiHandler("tab4")
    public void changeToTab4(ClickEvent ev) {
        setPosition(4);
    }

    public void setPosition(int pos) {
        unselectAllTabs();
        rail.removeClassName(rail.getClassName());
        switch (pos) {
            case 1:
                rail.setClassName(style.posStatistik());
                tab1.addStyleName(style.selected());
                break;
            case 2:
                rail.setClassName(style.posThemenwahl());
                tab2.addStyleName(style.selected());
                break;
            case 3:
                rail.setClassName(style.posFreunde());
                tab3.addStyleName(style.selected());
                break;
            case 4:
                rail.setClassName(style.posProfile());
                tab4.addStyleName(style.selected());
                break;
            default:
                System.err.println("Wrong parameter! Only 1 to 4 allowed. Given was: " + pos);
        }
    }

    private void unselectAllTabs() {
        tab1.removeStyleName(style.selected());
        tab2.removeStyleName(style.selected());
        tab3.removeStyleName(style.selected());
        tab4.removeStyleName(style.selected());
    }
}