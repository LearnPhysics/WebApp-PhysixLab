/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.UserHome;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 *
 * @author Oliver
 */
public class UserHome extends Composite {
    
    public final static String NAME = "userHome";
    private static UserHomeUiBinder uiBinder = GWT.create(UserHomeUiBinder.class);
    
    interface UserHomeUiBinder extends UiBinder<Widget, UserHome> {
    }
    
    public UserHome() {
        initWidget(uiBinder.createAndBindUi(this));
    }
}
