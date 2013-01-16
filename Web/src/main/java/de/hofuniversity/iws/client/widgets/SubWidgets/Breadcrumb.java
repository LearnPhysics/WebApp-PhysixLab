/*
  * Copyright (C) 2012 Oliver Schütz
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
 * @author Oliver Schütz
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
