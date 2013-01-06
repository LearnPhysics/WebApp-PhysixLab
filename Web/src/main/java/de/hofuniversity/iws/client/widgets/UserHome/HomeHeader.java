/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.UserHome;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.*;

/**
 *
 * @author Oliver
 */
public class HomeHeader extends Composite {
    
    private static HomeHeaderUiBinder uiBinder = GWT.create(HomeHeaderUiBinder.class);
    
    interface HomeHeaderUiBinder extends UiBinder<Widget, HomeHeader> {
    }
    
    public HomeHeader() {
        initWidget(uiBinder.createAndBindUi(this));
    }
}
