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

import com.chrisgammage.ginjitsu.client.AfterInject;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.*;
import javax.inject.Inject;

/**
 * Composit class which is helpfull when some ui:fields must be injected,
 * because the ui binding after the injections happens.
 *
 * @author Daniel Heinrich <DannyNullZwo@gmail.com>
 */
public class InjectedBinderWidget<E extends UiBinder> extends Composite {

    @Inject
    private E uiBinder;

    @AfterInject
    public void bind() {
        Widget w = (Widget) uiBinder.createAndBindUi(this);
        initWidget(w);
        initWidget();
    }

    /**
     * Method which can be overriden for initialization, which must happen after
     * the ui binding
     */
    public void initWidget() {
    }
}
