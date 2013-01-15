/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.history;

import com.google.gwt.user.client.ui.*;
import com.google.inject.assistedinject.Assisted;
import de.hofuniversity.iws.client.jsonbeans.LessonJson;
import de.hofuniversity.iws.client.jsonbeans.SubjectJson;
import de.hofuniversity.iws.client.widgets.Lektion.Lektion.LektionFactory;
import de.hofuniversity.iws.client.widgets.history.ThemaElement.ThemaElementFactory;
import javax.inject.Inject;

/**
 *
 * @author Daniel Heinrich <DannyNullZwo@gmail.com>
 */
public class LektionsElement implements HistoryElement {

    private final ThemaElement parent;
    private final LektionFactory factory;
    private final LessonJson lesson;
    private final SubjectJson subject;

    public interface LektionsElementFactory {

        public LektionsElement create(SubjectJson subject, LessonJson lesson);
    }

    @Inject
    public LektionsElement(ThemaElementFactory parentFactory, LektionFactory factory,
                           @Assisted SubjectJson subject, @Assisted LessonJson lesson) {
        this.parent = parentFactory.create(subject);
        this.factory = factory;
        this.lesson = lesson;
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
        return factory.create(lesson, subject);
    }

    @Override
    public String getTtile() {
        return lesson.getTitle();
    }
}
