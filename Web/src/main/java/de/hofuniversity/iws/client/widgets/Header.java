/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.ui.*;
import de.hofuniversity.iws.client.HistoryPageController;
import de.hofuniversity.iws.client.widgets.UserHome.UserHome;
import javax.inject.Inject;

/**
 *
 * @author Oliver
 */
public class Header extends Composite {

    private static HeaderUiBinder uiBinder = GWT.create(HeaderUiBinder.class);

    interface HeaderUiBinder extends UiBinder<Widget, Header> {
    }
    private final HistoryPageController pageController;
    @UiField//so gwt-test-util is happy
    private FocusPanel logo;

    @Inject
    public Header(HistoryPageController pageController) {
        initWidget(uiBinder.createAndBindUi(this));
        this.pageController = pageController;
    }

    @UiHandler("logo")
    public void goHome(ClickEvent ev) {
        pageController.changePage(UserHome.NAME);
    }
}
