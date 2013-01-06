/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.SubWidgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.*;

/**
 *
 * @author Oliver
 */
public class UserHomeFriend extends Composite {
    
    private static UserHomeFriendUiBinder uiBinder = GWT.create(UserHomeFriendUiBinder.class);
    
    interface UserHomeFriendUiBinder extends UiBinder<Widget, UserHomeFriend> {
    }
    
    public UserHomeFriend() {
        initWidget(uiBinder.createAndBindUi(this));
    }
}
