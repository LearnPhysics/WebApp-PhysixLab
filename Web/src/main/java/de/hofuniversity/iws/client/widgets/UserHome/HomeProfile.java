/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.UserHome;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.ui.*;
import de.hofuniversity.iws.client.PhysixLab;
import de.hofuniversity.iws.client.widgets.SubWidgets.ProfileBig;
import javax.inject.Inject;

/**
 *
 * @author Oliver
 */
public class HomeProfile extends Composite {

    interface HomeProfileUiBinder extends UiBinder<Widget, HomeProfile> {
    }
    private static HomeProfileUiBinder uiBinder = GWT.create(HomeProfileUiBinder.class);
    @UiField VerticalPanel uData;

    @Inject
    public HomeProfile(PhysixLab lab) {
        initWidget(uiBinder.createAndBindUi(this));
        uData.add(new ProfileBig(lab.getSessionUser()));
        Image img = new Image("images/ReplacementImages/EditProfile.png");
        img.getElement().getStyle().setMarginLeft(195, Style.Unit.PX);
        uData.add(img);
    }
}
