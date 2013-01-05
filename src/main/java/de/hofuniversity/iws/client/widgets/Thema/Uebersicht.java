/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.Thema;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.*;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.ui.*;
import de.hofuniversity.iws.client.jsonbeans.SubjectJson;

/**
 *
 * @author Oliver
 */
public class Uebersicht extends Composite {

    private static UebersichtUiBinder uiBinder = GWT.create(UebersichtUiBinder.class);
    @UiField
    HeadingElement title;
    @UiField
    DivElement einfuehrungstext;

    interface UebersichtUiBinder extends UiBinder<Widget, Uebersicht> {
    }

    public Uebersicht(SubjectJson thema) {
        initWidget(uiBinder.createAndBindUi(this));

        title.setInnerText(thema.getTitle());
        einfuehrungstext.setInnerText(thema.getText());
    }
}