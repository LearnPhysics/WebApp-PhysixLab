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
package de.hofuniversity.iws.client.widgets.base;

import com.google.gwt.uibinder.client.*;
import de.hofuniversity.iws.client.widgets.Header;
import de.hofuniversity.iws.client.widgets.SubWidgets.BackButton.BackButtonFactory;
import de.hofuniversity.iws.client.widgets.SubWidgets.*;
import de.hofuniversity.iws.client.widgets.SubWidgets.Breadcrumb.BreadcrumbFactory;
import de.hofuniversity.iws.client.widgets.history.HistoryElement;
import javax.inject.*;

/**
 * provides uifactories for header, breadcrumb and backbutton
 * @author Daniel Heinrich <DannyNullZwo@gmail.com>
 */
public abstract class CrumbPage<E extends UiBinder> extends HistoryPage<E> {

    @Inject private BackButtonFactory backFactory;
    @Inject private BreadcrumbFactory breadFactory;
    @Inject private Provider<Header> headerProvider;
    private final HistoryElement element;

    public CrumbPage(HistoryElement element, String name) {
        super(name);
        this.element = element;
    }

    @UiFactory
    public Breadcrumb buildCrumb() {
        return breadFactory.create(element);
    }

    @UiFactory
    public BackButton buildBack() {
        return backFactory.create(element);
    }

    @UiFactory
    public Header getHeader() {
        return headerProvider.get();
    }
}
