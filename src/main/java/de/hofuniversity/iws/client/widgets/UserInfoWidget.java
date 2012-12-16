/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets;

import de.hofuniversity.iws.client.widgets.PhotoBooth.Listener;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.ui.*;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class UserInfoWidget extends HistoryPage {

    private UserInfoWidgetUiBinder uiBinder = GWT.create(UserInfoWidgetUiBinder.class);

    interface UserInfoWidgetUiBinder extends UiBinder<Widget, UserInfoWidget> {
    }
    @UiField
    Label user_name;
    @UiField
    Image user_pic;
    @UiField
    PhotoBoothWidget photobooth;
    public static final String NAME = "user";

    public UserInfoWidget() {
        initWidget(uiBinder.createAndBindUi(this));
        photobooth.addListener(new Listener() {
            @Override
            public void onImage(String url) {
                user_pic.setUrl(url);
            }
        });
    }

    @Override
    public String getName() {
        return NAME;
    }

    public void setImageUrl(String url) {
        user_pic.setUrl(url);
    }

    public void setUserName(String name) {
        user_name.setText(name);
    }
}
