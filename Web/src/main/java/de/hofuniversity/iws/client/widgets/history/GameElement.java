/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.history;

import com.google.gwt.user.client.ui.Composite;
import com.google.inject.assistedinject.Assisted;
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

    @Inject
    public GameElement(Provider<UserHomeElement> home, ThemaElementFactory parentFactory, GameFactory factory,
                       @Assisted SubjectJson subject, @Assisted GameJson game) {
        this.parent = subject == null ? home.get() : parentFactory.create(subject);
        this.factory = factory;
        this.game = game;
        this.subject = subject;
    }
    
    @Inject
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
