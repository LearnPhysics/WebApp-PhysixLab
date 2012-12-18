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
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import de.hofuniversity.iws.client.PhysixLab;
import de.hofuniversity.iws.client.widgets.SubWidgets.GameSelector;
import de.hofuniversity.iws.client.widgets.SubWidgets.ThemaSelector;
import de.hofuniversity.iws.client.widgets.TestEntities.FakeDatabase;
import de.hofuniversity.iws.client.widgets.TestEntities.TestGame;
import de.hofuniversity.iws.client.widgets.TestEntities.TestThema;
import de.hofuniversity.iws.client.widgets.Thema.Thema;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Oliver
 */
public class HomeThemenwahl extends Composite {
    
    private static HomeThemenwahlUiBinder uiBinder = GWT.create(HomeThemenwahlUiBinder.class);
    
    private static List<TestThema> themen;
    @UiField VerticalPanel themaPanel;
    
    interface HomeThemenwahlUiBinder extends UiBinder<Widget, HomeThemenwahl> {
    }
    
    public HomeThemenwahl() {
        initWidget(uiBinder.createAndBindUi(this));
        this.themen = FakeDatabase.getInstance().getAllThemes();
        if(themen != null) {
            setup();
        }
    }
    
    public void setup() {
        for (int i = 0; i < themen.size(); i++) {
            ThemaSelector ts = new ThemaSelector(themen.get(i), i);
            themaPanel.add(ts);
        }
    }
}