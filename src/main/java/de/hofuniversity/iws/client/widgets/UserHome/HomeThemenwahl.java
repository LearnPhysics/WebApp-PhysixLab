/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.UserHome;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 *
 * @author Oliver
 */
public class HomeThemenwahl extends Composite {
    
    private static HomeThemenwahlUiBinder uiBinder = GWT.create(HomeThemenwahlUiBinder.class);
    
    interface HomeThemenwahlUiBinder extends UiBinder<Widget, HomeThemenwahl> {
    }
    
    public HomeThemenwahl() {
        initWidget(uiBinder.createAndBindUi(this));
    }
}
