/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import de.hofuniversity.iws.shared.services.LoginServiceAsync;
import javax.inject.Inject;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class UserInfoWidget extends Composite {

    @Inject
    private LoginServiceAsync loginService;
    private UserInfoWidgetUiBinder uiBinder = GWT.create(UserInfoWidgetUiBinder.class);

    interface UserInfoWidgetUiBinder extends UiBinder<Widget, UserInfoWidget> {
    }

    public UserInfoWidget() {
        initWidget(uiBinder.createAndBindUi(this));
    }
    
      
    @UiField Image user_pic;
    public void setImageUrl(String url) {
        user_pic.setUrl(url);
    }
    @UiField Label user_name;
    public void setUserName(String name) {
        user_name.setText(name);
    }


}
