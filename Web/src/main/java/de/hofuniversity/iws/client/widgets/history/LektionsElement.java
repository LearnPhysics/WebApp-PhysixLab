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
import de.hofuniversity.iws.client.jsonbeans.*;
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
