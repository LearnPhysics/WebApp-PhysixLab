/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.UserHome;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ClientBundle.Source;
import com.google.gwt.resources.client.DataResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
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
        setTestFriends();
    }

    private void setTestFriends() {

        TestUser user0 = new TestUser();
        TestUser user1 = new TestUser();
        TestUser user2 = new TestUser();

        user0.setUserName("Test00");
        user1.setUserName("Test01");
        user2.setUserName("Test02");

        user0.setCity("TestTown00");
        user1.setCity("TestTown01");
        user2.setCity("TestTown02");

        user0.setBirthDate(new Timestamp(100));
        user0.setBirthDate(new Timestamp(133876800));
        user0.setBirthDate(new Timestamp(1038768000));

        String url2 = "https://si0.twimg.com/profile_images/2641434457/369709d0d9861e4a7298c8606023e42b.png";
        String url1 = "https://si0.twimg.com/profile_images/1765911582/image1326962316_reasonably_small.png";
        String url0 = "http://www.msc.org/publikationen/images-DE/logonutzung/missuse.jpg/image_preview";

        addFriend(user0, url0);
        addFriend(user1, url1);
        addFriend(user2, url2);
    }

    public void addFriend(TestUser friend, String imageURL) {
        try {
            HorizontalPanel friendPanel = new HorizontalPanel();          
            Image image = new Image();
            image.setUrl(imageURL);
            
            int oWidth = image.getWidth();
            int oHeight = image.getHeight();
            
            /* Is supossed to scale image. Doesn't do jack
             * gwt-image-loader also didn't work (FitImage)
            float ratio = oHeight / (float) 120;
            int nWidth = Math.round(oWidth / ratio);
            image.setPixelSize(nWidth, 120);
            image.setSize(nWidth + "px", 120 + "px");
            */
            
            // Take the middle portion of the image as compensatory solution
            image.setVisibleRect((oWidth / 2) - 60, (oHeight / 2) - 60, 120, 120);

            // DockLayoutPanel headCombo = new DockLayoutPanel(Unit.PX);

            VerticalPanel dataPanel = new VerticalPanel();
            Label userName = new Label(friend.getUserName());
            Label age = new Label("Alter: " + Utilities.getAge(friend.getBirthDate()));
            Label city = new Label("Wohnort: " + friend.getCity());
            Label rank = new Label("Rang: " + "Testuser");

            HorizontalPanel stars = new HorizontalPanel();
            Label starCount = new Label("10");
            Image star = new Image("images/UserHome/LittleStar.png");

            stars.add(starCount);
            stars.add(star);

            dataPanel.add(userName);
            dataPanel.add(age);
            dataPanel.add(city);
            dataPanel.add(rank);

            friendPanel.add(image);
            friendPanel.add(dataPanel);
            friendPanel.add(stars);

            try {
                friends.add(friendPanel);
            } catch (Exception e) {
                System.err.println("InnerCatch: " + e);
            }

        } catch (Exception e) {
            System.err.println("OuterCatch: " + e);
        }
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