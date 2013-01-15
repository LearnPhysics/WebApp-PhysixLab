/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.history;

import com.google.gwt.user.client.ui.*;
import de.hofuniversity.iws.client.widgets.UserHome.UserHome;
import javax.inject.*;

/**
 *
 * @author Daniel Heinrich <DannyNullZwo@gmail.com>
 */
public class UserHomeElement implements HistoryElement {

    @Inject private Provider<UserHome> provider;

    @Override
    public HistoryElement getParent() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean hasParent() {
        return false;
    }

    @Override
    public Composite createWidget() {
        return provider.get();
    }

    @Override
    public String getTtile() {
        return "Home";
    }
}
