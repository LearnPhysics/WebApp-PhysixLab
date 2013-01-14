/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.SubWidgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.ui.*;
import com.google.inject.assistedinject.*;
import de.hofuniversity.iws.client.HistoryPageController;
import de.hofuniversity.iws.client.util.CrumbTuple;

/**
 *
 * @author Oliver
 */
public class Crumb extends Composite {

    interface CrumbUiBinder extends UiBinder<Widget, Crumb> {
    }
    private static CrumbUiBinder uiBinder = GWT.create(CrumbUiBinder.class);
    private CrumbTuple lp;
    private final HistoryPageController pageController;
    @UiField FocusPanel action;
    @UiField ParagraphElement crumb;

    public interface CrumbFactory {

        public Crumb create(CrumbTuple lp);
    }

    @AssistedInject
    public Crumb(HistoryPageController pageController, @Assisted CrumbTuple lp) {
        initWidget(uiBinder.createAndBindUi(this));
        this.pageController = pageController;
        this.lp = lp;
        setup();
    }

    private void setup() {
        crumb.setInnerText(" " + lp.getLabel() + " ");
    }

    @UiHandler("action")
    public void openGame(ClickEvent ev) {
        pageController.changePage(lp.getPage());
    }
}