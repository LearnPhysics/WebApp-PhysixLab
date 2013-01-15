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
package de.hofuniversity.iws.client.widgets.Lektion;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.*;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import de.hofuniversity.iws.client.jsonbeans.*;
import de.hofuniversity.iws.shared.services.*;

/**
 *
 * @author Oliver Schütz
 */
public class Test extends Composite {

    private static TestUiBinder uiBinder = GWT.create(TestUiBinder.class);
    private TestJson test;
    private LessonJson lesson;
    private SubjectJson subject;
    private UserServiceAsync userService = (UserServiceAsync) GWT.create(UserService.class);
    @UiField HeadingElement title;
    @UiField ParagraphElement text;
    @UiField VerticalPanel illustration;
    @UiField TextBox solution;
    @UiField Button submit;

    interface TestUiBinder extends UiBinder<Widget, Test> {
    }

    public Test(TestJson test, LessonJson lesson, SubjectJson subject) {
        initWidget(uiBinder.createAndBindUi(this));

        this.test = test;
        this.lesson = lesson;
        this.subject = subject;
        title.setInnerText(test.getTitle());
        text.setInnerText(test.getProblem());
        illustration.add(new Image(test.getImage()));
    }

    @UiHandler("submit")
    public void checkSolution(ClickEvent ev) {
        try {
            double sol = Double.parseDouble(solution.getText());
            if (Utilities.isSimilar(test.getSolution(), sol, 10)) {
                userService.addTestResult(lesson.getName(), subject.getName(), 0, new TestAsyncCallback());
            }
        } catch (Exception e) {
            System.err.println("Input not parsable!");
        }
    }

    private class TestAsyncCallback implements AsyncCallback<Void> {

        @Override
        public void onFailure(Throwable caught) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void onSuccess(Void result) {
            Window.alert("Congratulation, you passed the test.");
            // Spiele Lösungsanimation
        }
    }
}
