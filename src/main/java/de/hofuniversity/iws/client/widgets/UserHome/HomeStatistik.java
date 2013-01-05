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
public class HomeStatistik extends Composite {
    
    private static HomeStatistikUiBinder uiBinder = GWT.create(HomeStatistikUiBinder.class);
    
    interface HomeStatistikUiBinder extends UiBinder<Widget, HomeStatistik> {
    }
    
    public HomeStatistik() {
        initWidget(uiBinder.createAndBindUi(this));
    }
}
