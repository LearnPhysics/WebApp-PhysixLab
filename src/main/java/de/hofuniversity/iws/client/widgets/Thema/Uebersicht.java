/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.Thema;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 *
 * @author Oliver
 */
public class Uebersicht extends Composite {
    
    private static UebersichtUiBinder uiBinder = GWT.create(UebersichtUiBinder.class);
    
    interface UebersichtUiBinder extends UiBinder<Widget, Uebersicht> {
    }
    
    public Uebersicht() {
        initWidget(uiBinder.createAndBindUi(this));
    }
}
