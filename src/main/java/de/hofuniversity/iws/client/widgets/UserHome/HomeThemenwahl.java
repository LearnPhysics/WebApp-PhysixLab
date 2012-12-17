/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.UserHome;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Widget;
import de.hofuniversity.iws.client.PhysixLab;
import de.hofuniversity.iws.client.widgets.Thema.Thema;

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
    
    @UiHandler("img1")
    public void changeToKinetik(ClickEvent ev) {
        System.out.println("img1 fired");
        PhysixLab.PAGE_CONTROLLER.changePage(Thema.NAME);
    }
    @UiHandler("img2")
    public void changeToElekt(ClickEvent ev) {
        System.out.println("img2 fired");
        PhysixLab.PAGE_CONTROLLER.changePage(Thema.NAME);
    }
    @UiHandler("img3")
    public void changeToThermo(ClickEvent ev) {
        System.out.println("img3 fired");
        PhysixLab.PAGE_CONTROLLER.changePage(Thema.NAME);
    }
}
