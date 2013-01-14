/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.SubWidgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.assistedinject.*;
import de.hofuniversity.iws.client.HistoryPageController;
import de.hofuniversity.iws.client.PhysixLab;
import de.hofuniversity.iws.client.util.AddressStack;
import de.hofuniversity.iws.client.util.CrumbTuple;

/**
 *
 * @author Oliver
 */
public class BackButton extends Composite {

    interface BackButtonUiBinder extends UiBinder<Widget, BackButton> {
    }
    private static BackButtonUiBinder uiBinder = GWT.create(BackButtonUiBinder.class);
    private final AddressStack stack;
    private final HistoryPageController pageController;
    private final PhysixLab lab;
    private int layer;

    public interface BackButtonFactory {

        public BackButton create(int layer);
    }

    @AssistedInject
    public BackButton(AddressStack stack, HistoryPageController pageController,
                      PhysixLab lab, @Assisted int layer) {
        initWidget(uiBinder.createAndBindUi(this));
        this.pageController = pageController;
        this.stack = stack;
        this.lab = lab;
        this.layer = layer;
    }

    @UiHandler("back")
    public void goBack(ClickEvent ev) {
        CrumbTuple ct = stack.getPrevious(layer);
        pageController.changePage(ct.getPage());
    }

    @UiHandler("logout")
    public void logout(ClickEvent ev) {
        lab.logout();
    }
}