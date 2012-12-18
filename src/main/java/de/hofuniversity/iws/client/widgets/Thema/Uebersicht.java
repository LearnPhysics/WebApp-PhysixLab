/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.Thema;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.Style;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import de.hofuniversity.iws.client.widgets.TestEntities.EntityHolder;
import de.hofuniversity.iws.client.widgets.TestEntities.TestThema;

/**
 *
 * @author Oliver
 */
public class Uebersicht extends Composite {

    private static UebersichtUiBinder uiBinder = GWT.create(UebersichtUiBinder.class);
    private TestThema thema;
    @UiField
    HeadingElement title;
    @UiField
    DivElement einfuehrungstext;

    interface UebersichtUiBinder extends UiBinder<Widget, Uebersicht> {
    }

    public Uebersicht() {
        initWidget(uiBinder.createAndBindUi(this));
        this.thema = EntityHolder.getInstance().getThema();
        if (thema != null) {
            setup();
        }
    }

    private void setup() {
        title.setInnerText(thema.getTitle());
        einfuehrungstext.setInnerText(thema.getDescription());
    }
}