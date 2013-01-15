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
package de.hofuniversity.iws.client;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import de.hofuniversity.iws.client.widgets.Game.Game.GameFactory;
import de.hofuniversity.iws.client.widgets.Lektion.Lektion.LektionFactory;
import de.hofuniversity.iws.client.widgets.SubWidgets.BackButton.BackButtonFactory;
import de.hofuniversity.iws.client.widgets.SubWidgets.Breadcrumb.BreadcrumbFactory;
import de.hofuniversity.iws.client.widgets.SubWidgets.Crumb.CrumbFactory;
import de.hofuniversity.iws.client.widgets.SubWidgets.GameSelector.GameSelectorFactory;
import de.hofuniversity.iws.client.widgets.SubWidgets.LektionSelector.LektionSelectorFactory;
import de.hofuniversity.iws.client.widgets.SubWidgets.ThemaSelector.ThemaSelectorFactory;
import de.hofuniversity.iws.client.widgets.Thema.Lektionswahl.LektionswahlFactory;
import de.hofuniversity.iws.client.widgets.Thema.Spielwahl.SpielwahlFactory;
import de.hofuniversity.iws.client.widgets.Thema.Thema.ThemaFactory;
import de.hofuniversity.iws.client.widgets.history.LektionsElement.LektionsElementFactory;
import de.hofuniversity.iws.client.widgets.history.ThemaElement.ThemaElementFactory;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class PhysixLabModul extends AbstractGinModule {

    @Override
    protected void configure() {
        Class[] factoryClasses = new Class[]
        {
            ThemaSelectorFactory.class,
            LektionswahlFactory.class,
            ThemaFactory.class,
            LektionSelectorFactory.class,
            GameSelectorFactory.class,
            SpielwahlFactory.class,
            BreadcrumbFactory.class,
            BackButtonFactory.class,
            CrumbFactory.class,
            LektionFactory.class,
            GameFactory.class,
            LektionsElementFactory.class,
            ThemaElementFactory.class,
        };

        for (Class factory : factoryClasses) {
            install(new GinFactoryModuleBuilder().build(factory));
        }
    }
}
