/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.SubWidgets;

import de.hofuniversity.iws.client.PhysixLab;
import de.hofuniversity.iws.client.widgets.Lektion.Lektion;
import de.hofuniversity.iws.shared.dto.LektionDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.ui.*;

/**
 *
 * @author Oliver
 */
public class LektionSelector extends Composite {
    
    private static LektionSelectorUiBinder uiBinder = GWT.create(LektionSelectorUiBinder.class);
    private LektionDTO lektion;
    private int x, y;
    
    @UiField HTMLPanel wrap;
    @UiField FocusPanel oImg;
    @UiField Image iImg;
    
    interface LektionSelectorUiBinder extends UiBinder<Widget, LektionSelector> {
    }
    
    public LektionSelector(LektionDTO lektion, int x, int y) {
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
        PhysixLab.PAGE_CONTROLLER.changePage(new Lektion(lektion));
    }
}
