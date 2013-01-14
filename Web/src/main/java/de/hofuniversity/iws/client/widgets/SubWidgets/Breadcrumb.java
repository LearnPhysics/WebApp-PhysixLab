/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.SubWidgets;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.ui.*;
import com.google.inject.assistedinject.*;
import de.hofuniversity.iws.client.util.*;
import de.hofuniversity.iws.client.widgets.SubWidgets.Crumb.CrumbFactory;

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

        public Breadcrumb create(int layer);
    }

    @AssistedInject
    public Breadcrumb(AddressStack stack, CrumbFactory factory, @Assisted int layer) {
        initWidget(uiBinder.createAndBindUi(this));

        try {
            List<CrumbTuple> ctList = stack.getListTillLayer(layer);
            System.err.println("Breadcrumb Length: " + ctList.size());
            for (int i = 0; i < ctList.size(); i++) {
                if (ctList.get(i).getLayer() > 0) {
                    crumbs.add(factory.create(ctList.get(i)));
                    System.err.println(ctList.get(i).getLabel());
                    if (i < ctList.size() - 1) {
                        crumbs.add(new SplitterParagraph());
                    }
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Couldn't build breadcrumbs after reload!");
        }
    }
}
