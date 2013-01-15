/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.base;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.History;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class HistoryPage<E extends UiBinder> extends InjectedBinderWidget<E> {

    private String name;

    public HistoryPage(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected void onLoad() {
        History.newItem(name, false);
    }
}
