/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.Lektion;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.*;

/**
 *
 * @author Oliver
 */
public class Loesung extends Composite {
    
    private static LoesungUiBinder uiBinder = GWT.create(LoesungUiBinder.class);
    
    interface LoesungUiBinder extends UiBinder<Widget, Loesung> {
    }
    
    public Loesung() {
        initWidget(uiBinder.createAndBindUi(this));
    }
}
