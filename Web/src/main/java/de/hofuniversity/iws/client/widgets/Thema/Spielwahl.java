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

import com.google.gwt.core.client.*;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.ui.*;
import com.google.inject.assistedinject.Assisted;
import de.hofuniversity.iws.client.jsonbeans.GameJson;
import de.hofuniversity.iws.client.widgets.SubWidgets.GameSelector.GameSelectorFactory;
import javax.inject.Inject;

/**
 *
 * @author Oliver Schütz
 */
public class Spielwahl extends Composite {

    interface SpielwahlUiBinder extends UiBinder<Widget, Spielwahl> {
    }
    private static SpielwahlUiBinder uiBinder = GWT.create(SpielwahlUiBinder.class);
    @UiField VerticalPanel gamesPanel;

    public interface SpielwahlFactory {

        public Spielwahl create(JsArray<GameJson> games);
    }

    @Inject
    public Spielwahl(GameSelectorFactory factory, @Assisted JsArray<GameJson> games) {
        initWidget(uiBinder.createAndBindUi(this));
        for (int i = 0; i < games.length(); i++) {
            gamesPanel.add(factory.create(games.get(i)));
        }
    }
}
