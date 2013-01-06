/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.SubWidgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import de.hofuniversity.iws.client.PhysixLab;
import de.hofuniversity.iws.client.util.AddressStack;
import de.hofuniversity.iws.client.util.CrumbTuple;
import de.hofuniversity.iws.client.widgets.LoginPage;

/**
 *
 * @author Oliver
 */
public class BackButton extends Composite {
    
    private static BackButtonUiBinder uiBinder = GWT.create(BackButtonUiBinder.class);
    private int layer;
    
    interface BackButtonUiBinder extends UiBinder<Widget, BackButton> {
    }
    
    public BackButton(int layer) {
        initWidget(uiBinder.createAndBindUi(this));
        this.layer = layer;
    }
    
    @UiHandler("back")
    public void goBack(ClickEvent ev) {
        try {
            CrumbTuple ct = AddressStack.getInstance().getPrevious(layer);
            PhysixLab.PAGE_CONTROLLER.changePage(ct.getPage());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    @UiHandler("logout")
    public void logout(ClickEvent ev) {
        try {
            PhysixLab.logout();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}