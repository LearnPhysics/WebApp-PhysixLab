/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.history;

import com.google.gwt.user.client.ui.Composite;

/**
 *
 * @author Daniel Heinrich <DannyNullZwo@gmail.com>
 */
public interface HistoryElement {

    public HistoryElement getParent();

    public boolean hasParent();

    public Composite createWidget();
    
    public String getTtile();
}
