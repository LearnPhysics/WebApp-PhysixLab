/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.gwttests;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import de.hofuniversity.iws.client.PhysixLabModul;
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
import de.hofuniversity.iws.client.widgets.history.GameElement.GameElementFactory;
import de.hofuniversity.iws.client.widgets.history.LektionsElement.LektionsElementFactory;
import de.hofuniversity.iws.client.widgets.history.ThemaElement.ThemaElementFactory;

/**
 *
 * @author Daniel Heinrich <DannyNullZwo@gmail.com>
 */
public class TestGinModule extends AbstractGinModule {

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
            GameElementFactory.class,
        };

        for (Class factory : factoryClasses) {
            install(new GinFactoryModuleBuilder().build(factory));
        }
    }
}
