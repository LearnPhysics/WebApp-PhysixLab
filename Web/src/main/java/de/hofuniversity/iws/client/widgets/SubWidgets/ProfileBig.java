/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.SubWidgets;

import java.sql.Timestamp;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.*;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.ui.*;
import de.hofuniversity.iws.shared.dto.User;
import de.hofuniversity.iws.shared.services.Utilities;

/**
 *
 * @author Oliver
 */
public class ProfileBig extends Composite {

    private static ProfileBigUiBinder uiBinder = GWT.create(ProfileBigUiBinder.class);
    private User user;
    @UiField Image image;
    @UiField Label rank;
    @UiField VerticalPanel data;
    @UiField HeadingElement userName;
    @UiField ParagraphElement text;

    interface ProfileBigUiBinder extends UiBinder<Widget, ProfileBig> {
    }

    public ProfileBig() {
        initWidget(uiBinder.createAndBindUi(this));
        setTestUser();
        setup();
    }

    public ProfileBig(User user) {
        initWidget(uiBinder.createAndBindUi(this));
        this.user = user;
        setTestUser();
        setup();
    }

    private void setTestUser() {
        user = new User();
        user.setFirstName("George");
        user.setLastName("Washington");
        user.setUserName("El presidente");
        user.setCity("Washington D.C.");
        user.setBirthDate(new Timestamp(100));
        user.setUserPic("https://si0.twimg.com/profile_images/2641434457/369709d0d9861e4a7298c8606023e42b.png");
    }

    private void setup() {
        image.setUrl(user.getUserPic());
        image.setVisibleRect((image.getWidth() / 2) - 60, (image.getHeight() / 2) - 60, 120, 120);
        userName.setInnerText(user.getUserName());
        rank.setText("10");

        data.add(new Label("Name: " + user.getFirstName() + " " + user.getLastName()));
        data.add(new Label("Alter: " + Utilities.getAge(user.getBirthDate())));
        data.add(new Label("Wohnort: " + user.getCity()));
        data.add(new Label("Rang: " + "Experte"));
        text.setInnerText("Über mich: Lorem ipsum dolor sit amet, consetetur sadipscing elitr, "
                          + "sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, "
                          + "sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. "
                          + "Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. "
                          + "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod "
                          + "tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. "
                          + "At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, "
                          + "no sea takimata sanctus est Lorem ipsum dolor sit amet.");
    }
}
