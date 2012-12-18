/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.Lektion;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import de.hofuniversity.iws.client.widgets.TestEntities.TestLektion;

/**
 *
 * @author Oliver
 */
public class Lektion extends Composite {

    private static LektionUiBinder uiBinder = GWT.create(LektionUiBinder.class);
    private TestLektion lektion;

    interface LektionUiBinder extends UiBinder<Widget, Lektion> {
    }

    public Lektion() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    public Lektion(TestLektion lektion) {
        initWidget(uiBinder.createAndBindUi(this));
        this.lektion = lektion;
    }
}