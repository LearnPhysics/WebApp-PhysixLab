/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.SubWidgets;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.ui.*;
import de.hofuniversity.iws.client.util.*;

/**
 *
 * @author Oliver
 */
public class Breadcrumb extends Composite {
    
    private static BreadcrumbUiBinder uiBinder = GWT.create(BreadcrumbUiBinder.class);
    private int layer;
    
    @UiField HorizontalPanel crumbs;
    
    interface BreadcrumbUiBinder extends UiBinder<Widget, Breadcrumb> {
    }
    
    public Breadcrumb(int layer) {
        initWidget(uiBinder.createAndBindUi(this));
        this.layer = layer;
        setup();
    }
    
    private void setup() {
        try {
            List<CrumbTuple> ctList = AddressStack.getInstance().getListTillLayer(layer);
            System.err.println("Breadcrumb Length: " + ctList.size());
            for(int i = 0; i < ctList.size(); i++) {
                if(ctList.get(i).getLayer() > 0) {
                    crumbs.add(new Crumb(ctList.get(i)));
                    System.err.println(ctList.get(i).getLabel());
                    if(i < ctList.size() - 1) {
                        crumbs.add(new SplitterParagraph());
                    }
                }
            }
        }
        catch(IndexOutOfBoundsException e) {
            System.err.println("Couldn't build breadcrumbs after reload!");
        }
    }
}
