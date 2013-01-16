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
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import de.hofuniversity.iws.client.jsonbeans.*;
import de.hofuniversity.iws.client.widgets.Game.Game.GameFactory;
import de.hofuniversity.iws.client.widgets.history.ThemaElement.ThemaElementFactory;
import javax.inject.*;

/**
 *
 * @author Daniel Heinrich <DannyNullZwo@gmail.com>
 */
//TODO wie hier mit dem subject umgegangen wird ist ein wenig naja
public class GameElement implements HistoryElement {

    private final HistoryElement parent;
    private final GameFactory factory;
    private final GameJson game;
    private final SubjectJson subject;

    public interface GameElementFactory {

        public GameElement create(GameJson game);
        public GameElement create(SubjectJson subject, GameJson game);
    }

    @AssistedInject
    public GameElement(Provider<UserHomeElement> home, ThemaElementFactory parentFactory, GameFactory factory,
                       @Assisted SubjectJson subject, @Assisted GameJson game) {
        this.parent = subject == null ? home.get() : parentFactory.create(subject);
        this.factory = factory;
        this.game = game;
        this.subject = subject;
    }
    
    @AssistedInject
    public GameElement(Provider<UserHomeElement> home, ThemaElementFactory parentFactory, GameFactory factory,
                       @Assisted GameJson game) {
        this.parent = home.get();
        this.factory = factory;
        this.game = game;
        this.subject = null;
    }

    @Override
    public HistoryElement getParent() {
        return parent;
    }

    @Override
    public boolean hasParent() {
        return true;
    }

    @Override
    public Composite createWidget() {
        return factory.create(subject, game);
    }

    @Override
    public String getTtile() {
        return game.getTitle();
    }
}
