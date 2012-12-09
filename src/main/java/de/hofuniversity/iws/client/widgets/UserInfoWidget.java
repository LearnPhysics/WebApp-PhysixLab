/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.*;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class UserInfoWidget extends Composite {
    public final static String NAME = "user";
    private UserInfoWidgetUiBinder uiBinder = GWT.create(UserInfoWidgetUiBinder.class);

    interface UserInfoWidgetUiBinder extends UiBinder<Widget, UserInfoWidget> {
    }

    public UserInfoWidget() {
        initWidget(uiBinder.createAndBindUi(this));
        History.newItem(NAME);
    }
    @UiField
    Image user_pic;

    public void setImageUrl(String url) {
        user_pic.setUrl(url);
    }
    @UiField
    Label user_name;

    public void setUserName(String name) {
        user_name.setText(name);
    }
}
