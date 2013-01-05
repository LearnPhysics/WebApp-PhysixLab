/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.*;

/**
 *
 * @author Oliver
 */
public class Header extends Composite {
    
    private static HeaderUiBinder uiBinder = GWT.create(HeaderUiBinder.class);
    
    interface HeaderUiBinder extends UiBinder<Widget, Header> {
    }
    
    public Header() {
        initWidget(uiBinder.createAndBindUi(this));
    }
}
