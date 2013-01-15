/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.SubWidgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.ui.*;
import com.google.inject.assistedinject.Assisted;
import de.hofuniversity.iws.client.widgets.SubWidgets.Crumb.CrumbFactory;
import de.hofuniversity.iws.client.widgets.history.HistoryElement;
import javax.inject.Inject;

/**
 *
 * @author Oliver
 */
public class Breadcrumb extends Composite {

    interface BreadcrumbUiBinder extends UiBinder<Widget, Breadcrumb> {
    }
    private static BreadcrumbUiBinder uiBinder = GWT.create(BreadcrumbUiBinder.class);
    @UiField HorizontalPanel crumbs;

    public interface BreadcrumbFactory {

        public Breadcrumb create(HistoryElement element);
    }

    @Inject
    public Breadcrumb(CrumbFactory factory, @Assisted HistoryElement element) {
        initWidget(uiBinder.createAndBindUi(this));

        try {
            HistoryElement acc = element;
            while (acc.hasParent()) {
                crumbs.insert(acc.createWidget(), 0);
                crumbs.insert(new SplitterParagraph(), 0);
                acc = acc.getParent();
            }
            crumbs.insert(acc.createWidget(), 0);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Couldn't build breadcrumbs after reload!");
        }
    }
}
