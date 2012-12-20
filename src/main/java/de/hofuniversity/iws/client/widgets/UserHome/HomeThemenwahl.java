/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.UserHome;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.ui.*;
import de.hofuniversity.iws.client.widgets.SubWidgets.ThemaSelector;
import de.hofuniversity.iws.client.widgets.TestEntities.*;
import de.hofuniversity.iws.client.widgets.Thema.Thema;

/**
 *
 * @author Oliver
 */
public class HomeThemenwahl extends Composite {

    private static HomeThemenwahlUiBinder uiBinder = GWT.create(HomeThemenwahlUiBinder.class);
    @UiField
    VerticalPanel themaPanel;

    interface HomeThemenwahlUiBinder extends UiBinder<Widget, HomeThemenwahl> {
    }

    public HomeThemenwahl() {
        initWidget(uiBinder.createAndBindUi(this));
        List<TestThema> themen = FakeDatabase.getInstance().getAllThemes();
        if (themen != null) {
            for (TestThema thema : themen) {
                themaPanel.add(new ThemaSelector(thema));
            }
        }
    }
}
