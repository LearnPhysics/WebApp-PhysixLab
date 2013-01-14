/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Composite;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public abstract class HistoryPage extends Composite {

    private String name;

    public HistoryPage(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected void onAttach() {
        super.onAttach();
        History.newItem(name, false);
    }
}
