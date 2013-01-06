/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.SubWidgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import de.hofuniversity.iws.shared.entitys.User;
import de.hofuniversity.iws.shared.services.Utilities;

/**
 *
 * @author Oliver
 */
public class UserHomeFriend extends Composite {
    
    private static UserHomeFriendUiBinder uiBinder = GWT.create(UserHomeFriendUiBinder.class);
    private User friend;
    
    @UiField Image image;
    @UiField Label rank;
    @UiField VerticalPanel data;
    @UiField HeadingElement userName;
    
    interface UserHomeFriendUiBinder extends UiBinder<Widget, UserHomeFriend> {
    }
    
    public UserHomeFriend(User friend) {
        initWidget(uiBinder.createAndBindUi(this));
        this.friend = friend;
        setup();
        System.out.println("setup: " + friend.getUserName());
    }
    
    private void setup() {
        image.setUrl(friend.getUserPic());
        image.setVisibleRect((image.getWidth() / 2) - 60, (image.getHeight() / 2) - 60, 120, 120);
        userName.setInnerText(friend.getUserName());
        rank.setText("10");
        
        data.add(new Label("Alter: " + Utilities.getAge(friend.getBirthDate())));
        data.add(new Label("Wohnort: " + friend.getCity()));
        data.add(new Label("Rang: " + "Experte"));
    }
}
