/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.SubWidgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.ui.*;
import de.hofuniversity.iws.client.PhysixLab;
import javax.inject.Inject;

/**
 *
 * @author Oliver
 */
public class OnlyLogout extends Composite {

    interface OnlyLogoutUiBinder extends UiBinder<Widget, OnlyLogout> {
    }
    private static OnlyLogoutUiBinder uiBinder = GWT.create(OnlyLogoutUiBinder.class);
    private final PhysixLab lab;

    @Inject
    public OnlyLogout(PhysixLab lab) {
        initWidget(uiBinder.createAndBindUi(this));
        this.lab = lab;
    }

    @UiHandler("logout")
    public void logout(ClickEvent ev) {
        try {
            lab.logout();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}