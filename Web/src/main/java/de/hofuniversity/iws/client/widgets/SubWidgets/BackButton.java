/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.SubWidgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.assistedinject.*;
import de.hofuniversity.iws.client.HistoryPageController;
import de.hofuniversity.iws.client.PhysixLab;
import de.hofuniversity.iws.client.widgets.history.HistoryElement;
import javax.inject.Inject;

/**
 *
 * @author Oliver
 */
public class BackButton extends Composite {

    interface BackButtonUiBinder extends UiBinder<Widget, BackButton> {
    }
    private static BackButtonUiBinder uiBinder = GWT.create(BackButtonUiBinder.class);
    private final HistoryPageController pageController;
    private final PhysixLab lab;
    private final HistoryElement element;
    @UiField FocusPanel back;

    public interface BackButtonFactory {

        public BackButton create(HistoryElement element);
    }

    @Inject
    public BackButton(HistoryPageController pageController, PhysixLab lab,
                      @Assisted HistoryElement element) {
        initWidget(uiBinder.createAndBindUi(this));
        this.pageController = pageController;
        this.lab = lab;
        this.element = element;
        if (!element.hasParent()) {
            back.setVisible(false);
        }
    }

    @UiHandler("back")
    public void goBack(ClickEvent ev) {
        if (element.hasParent()) {
            pageController.changePage(element.getParent().createWidget());
        }
    }

    @UiHandler("logout")
    public void logout(ClickEvent ev) {
        lab.logout();
    }
}