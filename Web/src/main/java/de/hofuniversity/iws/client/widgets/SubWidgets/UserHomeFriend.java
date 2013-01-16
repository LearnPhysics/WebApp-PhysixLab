/*
  * Copyright (C) 2012 Oliver Schütz
  *
  * This program is free software: you can redistribute it and/or modify
  * it under the terms of the GNU General Public License as published by
  * the Free Software Foundation, either version 3 of the License, or
  * (at your option) any later version.
  *
  * This program is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU General Public License for more details.
  *
  * You should have received a copy of the GNU General Public License
  * along with this program.  If not, see <http://www.gnu.org/licenses/>.
  */
package de.hofuniversity.iws.client.widgets.SubWidgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.ui.*;
import de.hofuniversity.iws.shared.dto.User;
import de.hofuniversity.iws.shared.services.Utilities;

/**
 *
 * @author Oliver Schütz
 */
public class UserHomeFriend extends Composite {

    interface UserHomeFriendUiBinder extends UiBinder<Widget, UserHomeFriend> {
    }
    private static UserHomeFriendUiBinder uiBinder = GWT.create(UserHomeFriendUiBinder.class);
    private User friend;
    @UiField Image image;
    @UiField Label rank;
    @UiField VerticalPanel data;
    @UiField HeadingElement userName;

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
