/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.UserHome;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import de.hofuniversity.iws.client.PhysixLab;
import de.hofuniversity.iws.client.widgets.SubWidgets.UserHomeFriend;
import de.hofuniversity.iws.shared.entityimpl.UserDBO;
import de.hofuniversity.iws.shared.entitys.User;
import de.hofuniversity.iws.shared.services.Utilities;
import java.sql.Timestamp;

/**
 *
 * @author Oliver
 */
public class HomeFreunde extends Composite {

    private static HomeFreundeUiBinder uiBinder = GWT.create(HomeFreundeUiBinder.class);
    @UiField
    VerticalPanel friends;

    interface HomeFreundeUiBinder extends UiBinder<Widget, HomeFreunde> {
    }

    public HomeFreunde() {
        initWidget(uiBinder.createAndBindUi(this));
        // setup();
        setTestFriends();
    }

    private void setup() {
        User user = PhysixLab.getSessionUser();
        for(User friend : user.getBilateralFriends()) {
            addFriend(friend);
        }
    }
    
    private void setTestFriends() {

        User user0 = new UserDBO();
        User user1 = new UserDBO();
        User user2 = new UserDBO();

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
        user1.setUserPic("https://si0.twimg.com/profile_images/1765911582/image1326962316_reasonably_small.png");
        user2.setUserPic("http://www.msc.org/publikationen/images-DE/logonutzung/missuse.jpg/image_preview");

        addFriend(user0);
        addFriend(user1);
        addFriend(user2);
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