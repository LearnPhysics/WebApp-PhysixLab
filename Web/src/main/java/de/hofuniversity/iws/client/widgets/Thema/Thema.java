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
package de.hofuniversity.iws.client.widgets.Thema;

import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.ui.*;
import com.google.inject.assistedinject.Assisted;
import de.hofuniversity.iws.client.jsonbeans.SubjectJson;
import de.hofuniversity.iws.client.widgets.Thema.Lektionswahl.LektionswahlFactory;
import de.hofuniversity.iws.client.widgets.Thema.Spielwahl.SpielwahlFactory;
import de.hofuniversity.iws.client.widgets.Thema.Thema.ThemaUiBinder;
import de.hofuniversity.iws.client.widgets.base.CrumbPage;
import de.hofuniversity.iws.client.widgets.history.ThemaElement.ThemaElementFactory;
import javax.inject.Inject;

/**
 *
 * @author Oliver Schütz
 */
public class Thema extends CrumbPage<ThemaUiBinder> {

    public interface ThemaUiBinder extends UiBinder<Widget, Thema> {
    }
    public final static String NAME = "thema";
    private final LektionswahlFactory lektionFactory;
    private final SpielwahlFactory spielFactory;
    private final SubjectJson bean;
    @UiField Thema.ThemaStyle style;
    @UiField SpanElement rail;
    @UiField HorizontalPanel railContent;
    @UiField FocusPanel tab1;
    @UiField FocusPanel tab2;
    @UiField FocusPanel tab3;
    @UiField HTMLPanel page;

    interface ThemaStyle extends CssResource {

        String posLektionswahl();

        String posUebersicht();

        String posSpielwahl();

        String selected();

        String tab();

        String tab1();

        String tab2();

        String tab3();
    }

    public interface ThemaFactory {

        public Thema create(SubjectJson bean);
    }

    @Inject
    public Thema(LektionswahlFactory lektionFactory, SpielwahlFactory spielFactory,
                 ThemaElementFactory element, @Assisted SubjectJson bean) {
        super(element.create(bean), NAME + "?" + bean.getName());
        this.lektionFactory = lektionFactory;
        this.spielFactory = spielFactory;
        this.bean = bean;
    }

    @UiHandler("tab1")
    public void changeToTab1(ClickEvent ev) {
        setPosition(1);
    }

    @UiHandler("tab2")
    public void changeToTab2(ClickEvent ev) {
        setPosition(2);
    }

    @UiHandler("tab3")
    public void changeToTab3(ClickEvent ev) {
        setPosition(3);
    }

    public void setPosition(int pos) {
        unselectAllTabs();
        switch (pos) {
            case 1:
                rail.removeClassName(rail.getClassName());
                rail.setClassName(style.posLektionswahl());
                tab1.addStyleName(style.selected());
                break;
            case 2:
                rail.removeClassName(rail.getClassName());
                rail.setClassName(style.posUebersicht());
                tab2.addStyleName(style.selected());
                break;
            case 3:
                rail.removeClassName(rail.getClassName());
                rail.setClassName(style.posSpielwahl());
                tab3.addStyleName(style.selected());
                break;
            default:
                System.err.println("Wrong parameter! Only 1 to 3 allowed. Given was: " + pos);
        }
    }

    private void unselectAllTabs() {
        tab1.removeStyleName(style.selected());
        tab2.removeStyleName(style.selected());
        tab3.removeStyleName(style.selected());
    }

    @UiFactory
    public Lektionswahl buildLektion() {
        return lektionFactory.create(bean);
    }

    @UiFactory
    public Uebersicht buildUebrsicht() {
        return new Uebersicht(bean);
    }

    @UiFactory
    public Spielwahl buildSpiel() {
        return spielFactory.create(bean.getGames());
    }
}
