/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.SubWidgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import de.hofuniversity.iws.client.PhysixLab;
import de.hofuniversity.iws.client.widgets.Game.Game;
import de.hofuniversity.iws.client.widgets.Lektion.Lektion;
import de.hofuniversity.iws.client.widgets.TestEntities.EntityHolder;
import de.hofuniversity.iws.client.widgets.TestEntities.TestGame;
import de.hofuniversity.iws.client.widgets.TestEntities.TestLektion;

/**
 *
 * @author Oliver
 */
public class LektionSelector extends Composite {
    
    private static LektionSelectorUiBinder uiBinder = GWT.create(LektionSelectorUiBinder.class);
    private TestLektion lektion;
    private int x, y;
    
    @UiField HTMLPanel wrap;
    @UiField FocusPanel oImg;
    @UiField Image iImg;
    
    interface LektionSelectorUiBinder extends UiBinder<Widget, LektionSelector> {
    }
    
    public LektionSelector() {
        initWidget(uiBinder.createAndBindUi(this));
    }
    
    public LektionSelector(TestLektion lektion, int x, int y) {
        initWidget(uiBinder.createAndBindUi(this));
        this.lektion = lektion;
        this.x = x;
        this.y = y;
        setup();
    }
    
    private void setup() {
        iImg.setUrl(lektion.getPreviewURL());
        wrap.getElement().getStyle().setLeft(x, Style.Unit.PX);
        wrap.getElement().getStyle().setTop(195+y, Style.Unit.PX);
    }
    
    @UiHandler("oImg")
    public void openLektion(ClickEvent ev) {
        EntityHolder.getInstance().setLektion(lektion);
        PhysixLab.PAGE_CONTROLLER.changePage(Lektion.NAME);
    }
}
