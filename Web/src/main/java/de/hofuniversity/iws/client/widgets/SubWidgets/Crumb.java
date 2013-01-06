/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.SubWidgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import de.hofuniversity.iws.client.PhysixLab;
import de.hofuniversity.iws.client.util.CrumbTuple;
import de.hofuniversity.iws.client.widgets.Thema.Thema;
import de.hofuniversity.iws.shared.services.Utilities;

/**
 *
 * @author Oliver
 */
public class Crumb extends Composite {
    
    private static CrumbUiBinder uiBinder = GWT.create(CrumbUiBinder.class);
    
    @UiField FocusPanel action;
    @UiField ParagraphElement crumb;
    
    private CrumbTuple lp;
    
    interface CrumbUiBinder extends UiBinder<Widget, Crumb> {
    }
    
    public Crumb() {
        initWidget(uiBinder.createAndBindUi(this));
    }
    
    public Crumb(CrumbTuple lp) {
        initWidget(uiBinder.createAndBindUi(this));
        this.lp = lp;
        setup();
    }
    
    private void setup() {
        crumb.setInnerText(" " + lp.getLabel() + " ");
    }
    
    @UiHandler("action")
    public void openGame(ClickEvent ev) {
        PhysixLab.PAGE_CONTROLLER.changePage(lp.getPage());
    }
}