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
import de.hofuniversity.iws.shared.services.Utilities;
import java.sql.Timestamp;

import com.google.gwt.user.client.rpc.AsyncCallback;
import de.hofuniversity.iws.server.data.entities.UserDBO;
import de.hofuniversity.iws.shared.dto.User;
import de.hofuniversity.iws.shared.services.UserService;
import de.hofuniversity.iws.shared.services.UserServiceAsync;

/**
 *
 * @author Oliver
 */
public class HomeFreunde extends Composite {

    private static HomeFreundeUiBinder uiBinder = GWT.create(HomeFreundeUiBinder.class);
    private final static UserServiceAsync userService = GWT.create(UserService.class);
    @UiField
    VerticalPanel friends;

    interface HomeFreundeUiBinder extends UiBinder<Widget, HomeFreunde> {
    }

    public HomeFreunde() {
        initWidget(uiBinder.createAndBindUi(this));
        userService.getFriends(new AsyncCallback<Iterable<? extends User>>() {

            @Override
            public void onFailure(Throwable caught) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void onSuccess(Iterable<? extends User> result) {
                for (User user : result) {
                    addFriend(user);
                }
            }
        });
    }

    public void addFriend(User friend) {
        try {
            HorizontalPanel friendPanel = new HorizontalPanel();          
            Image image = new Image();
            image.setUrl(friend.getUserPic());
            
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
}