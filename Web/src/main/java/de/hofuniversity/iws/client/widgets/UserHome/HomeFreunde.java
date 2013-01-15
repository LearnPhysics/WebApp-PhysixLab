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
package de.hofuniversity.iws.client.widgets.UserHome;

import java.sql.Timestamp;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.ui.*;
import de.hofuniversity.iws.client.PhysixLab;
import de.hofuniversity.iws.client.widgets.SubWidgets.UserHomeFriend;
import de.hofuniversity.iws.shared.dto.User;
import de.hofuniversity.iws.shared.services.UserServiceAsync;
import javax.inject.Inject;

/**
 *
 * @author Oliver Schütz
 */
public class HomeFreunde extends Composite {

    interface HomeFreundeUiBinder extends UiBinder<Widget, HomeFreunde> {
    }
    private static HomeFreundeUiBinder uiBinder = GWT.create(HomeFreundeUiBinder.class);
    private final UserServiceAsync userService;
    private final PhysixLab lab;
    @UiField VerticalPanel friends;

    @Inject
    public HomeFreunde(UserServiceAsync userService, PhysixLab lab) {
        this.userService = userService;
        this.lab = lab;
        initWidget(uiBinder.createAndBindUi(this));
        // setup();
        setTestFriends();
    }

    private void setup() {
        User user = lab.getSessionUser();
//        for(User friend : user.getBilateralFriends()) {
//            addFriend(friend);
//        }
    }

    private void setTestFriends() {

        User user0 = new User();
        User user1 = new User();
        User user2 = new User();

        user0.setUserName("Test00");
        user1.setUserName("Test01");
        user2.setUserName("Test02");

        user0.setCity("TestTown00");
        user1.setCity("TestTown01");
        user2.setCity("TestTown02");

        user0.setBirthDate(new Timestamp(100));
        user1.setBirthDate(new Timestamp(133876800));
        user2.setBirthDate(new Timestamp(1038768000));



        user0.setUserPic("https://si0.twimg.com/profile_images/2641434457/369709d0d9861e4a7298c8606023e42b.png");
        user1.setUserPic("images/ReplacementImages/Profile01.png");
        user2.setUserPic("images/ReplacementImages/Profile02.png");

        addFriend(user1);
        addFriend(user2);
        addFriend(user0);
    }

    public void addFriend(User friend) {
        friends.add(new UserHomeFriend(friend));
        //friends.add(new Label("friend"));
    }

    private class TestUser {

        String userName;
        String city;
        Timestamp birthDate;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public Timestamp getBirthDate() {
            return birthDate;
        }

        public void setBirthDate(Timestamp birthDate) {
            this.birthDate = birthDate;
        }
    }
}
