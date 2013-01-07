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
import de.hofuniversity.iws.client.PhysixLab;

/**
 *
 * @author Oliver
 */
public class OnlyLogout extends Composite {
    
    private static OnlyLogoutUiBinder uiBinder = GWT.create(OnlyLogoutUiBinder.class);
    
    interface OnlyLogoutUiBinder extends UiBinder<Widget, OnlyLogout> {
    }
    
    public OnlyLogout() {
        initWidget(uiBinder.createAndBindUi(this));
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