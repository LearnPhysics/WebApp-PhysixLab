/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.UserHome;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;

/**
 *
 * @author Oliver
 */
public class HomeStatistik extends Composite {
    
    private static HomeStatistikUiBinder uiBinder = GWT.create(HomeStatistikUiBinder.class);
    
    @UiField ParagraphElement text;
    
    interface HomeStatistikUiBinder extends UiBinder<Widget, HomeStatistik> {
    }
    
    public HomeStatistik() {
        initWidget(uiBinder.createAndBindUi(this));
        
        text.setInnerText("Duis autem vel eum iriure dolor in hendrerit in vulputate "
                + "velit esse molestie consequat, vel illum dolore eu feugiat nulla "
                + "facilisis at vero eros et accumsan et iusto odio dignissim qui blandit "
                + "praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. "
                + "Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy "
                + "nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.");
    }
}
