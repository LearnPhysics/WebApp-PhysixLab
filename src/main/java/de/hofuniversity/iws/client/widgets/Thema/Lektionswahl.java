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
public class Lektionswahl extends Composite {
    
    private static LektionswahlUiBinder uiBinder = GWT.create(LektionswahlUiBinder.class);
    
    interface LektionswahlUiBinder extends UiBinder<Widget, Lektionswahl> {
    }
    
    public Lektionswahl() {
        initWidget(uiBinder.createAndBindUi(this));
    }
}
