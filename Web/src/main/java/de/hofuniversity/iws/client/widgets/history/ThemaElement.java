/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.history;

import com.google.gwt.user.client.ui.*;
import com.google.inject.assistedinject.Assisted;
import de.hofuniversity.iws.client.jsonbeans.SubjectJson;
import de.hofuniversity.iws.client.widgets.Thema.Thema.ThemaFactory;
import javax.inject.Inject;

/**
 *
 * @author Daniel Heinrich <DannyNullZwo@gmail.com>
 */
public class ThemaElement implements HistoryElement {

    private final UserHomeElement parent;
    private final ThemaFactory factory;
    private final SubjectJson subject;

    public interface ThemaElementFactory {

        public ThemaElement create(SubjectJson subject);
    }

    @Inject
    public ThemaElement(UserHomeElement parent, ThemaFactory factory, @Assisted SubjectJson subject) {
        this.parent = parent;
        this.factory = factory;
        this.subject = subject;
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
        return factory.create(subject);
    }

    @Override
    public String getTtile() {
        return subject.getTitle();
    }
}
