/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.UserHome;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import de.hofuniversity.iws.client.PhysixLab;
import de.hofuniversity.iws.client.widgets.SubWidgets.ProfileBig;
import de.hofuniversity.iws.shared.entitys.User;

/**
 *
 * @author Oliver
 */
public class HomeProfile extends Composite {
    
    private static HomeProfileUiBinder uiBinder = GWT.create(HomeProfileUiBinder.class);
    
    @UiField VerticalPanel uData;
    
    interface HomeProfileUiBinder extends UiBinder<Widget, HomeProfile> {
    }
    
    public HomeProfile() {
        initWidget(uiBinder.createAndBindUi(this));
        User user = PhysixLab.getSessionUser();  
        uData.add(new ProfileBig(user));
    }
}
