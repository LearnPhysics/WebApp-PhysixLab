/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets;

import de.hofuniversity.iws.client.widgets.PhotoBooth.Listener;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.*;
import de.hofuniversity.iws.shared.services.LoginServiceAsync;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class UserInfoWidget extends Composite {

    public final static String NAME = "user";
    private UserInfoWidgetUiBinder uiBinder = GWT.create(UserInfoWidgetUiBinder.class);

    interface UserInfoWidgetUiBinder extends UiBinder<Widget, UserInfoWidget> {
    }
    @UiField
    Image user_pic;
    @UiField
    PhotoBoothWidget photobooth;

    public UserInfoWidget() {
        initWidget(uiBinder.createAndBindUi(this));
        History.newItem(NAME);
        photobooth.addListener(new Listener() {
            @Override
            public void onImage(String url) {
                user_pic.setUrl(url);
            }
        });
    }

    @UiHandler("boothButton")
    public void createBooth(ClickEvent e) {
    }

    public void setImageUrl(String url) {
        user_pic.setUrl(url);
    }
    @UiField
    Label user_name;

    public void setUserName(String name) {
        user_name.setText(name);
        initWidget(uiBinder.createAndBindUi(this));
    }
}
