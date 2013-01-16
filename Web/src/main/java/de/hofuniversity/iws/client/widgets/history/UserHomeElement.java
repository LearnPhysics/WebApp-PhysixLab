/*
  * Copyright (C) 2012 Daniel Heinrich
  *
  * This program is free software: you can redistribute it and/or modify
  * it under the terms of the GNU General Public License as published by
  * the Free Software Foundation, either version 3 of the License, or
  * (at your option) any later version.
  *
  * This program is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU General Public License for more details.
  *
  * You should have received a copy of the GNU General Public License
  * along with this program.  If not, see <http://www.gnu.org/licenses/>.
  */
package de.hofuniversity.iws.client.widgets.history;

import com.google.gwt.user.client.ui.Composite;
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
