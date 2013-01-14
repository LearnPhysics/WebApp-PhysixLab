/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.UserHome;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.ui.*;
import de.hofuniversity.iws.client.GenericFactory;
import de.hofuniversity.iws.client.widgets.CrumbPage;
import de.hofuniversity.iws.client.widgets.SubWidgets.Breadcrumb.BreadcrumbFactory;
import javax.inject.Inject;

/**
 *
 * @author Oliver
 */
public class UserHome extends CrumbPage {

    interface UserHomeUiBinder extends UiBinder<Widget, UserHome> {
    }
    private final static UserHomeUiBinder uiBinder = GWT.create(UserHomeUiBinder.class);
    public final static String NAME = "userHome";
    @UiField UserHomeStyle style;
    @UiField HTMLPanel page;
    @UiField SpanElement rail;
    @UiField ScrollPanel sWrap;
    @UiField FocusPanel tab1;
    @UiField FocusPanel tab2;
    @UiField FocusPanel tab3;
    @UiField FocusPanel tab4;

    public interface UserHomeFactory extends GenericFactory<UserHome> {
    }

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
    public UserHome(BreadcrumbFactory factory) {
        super(null, factory, 1, NAME);
        initWidget(uiBinder.createAndBindUi(this));
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