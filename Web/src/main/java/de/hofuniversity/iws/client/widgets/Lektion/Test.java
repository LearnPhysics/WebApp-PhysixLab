/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
 * @author Oliver
 */
public class Test extends Composite {

    private static TestUiBinder uiBinder = GWT.create(TestUiBinder.class);
    private TestJson test;
    private LessonJson lesson;
    private SubjectJson subject;
    private UserServiceAsync userService = (UserServiceAsync)GWT.create(UserService.class);
    @UiField
    HeadingElement title;
    @UiField
    ParagraphElement text;
    @UiField
    VerticalPanel illustration;
    @UiField
    TextBox solution;
    @UiField
    Button submit;

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
            if(Utilities.isSimilar(test.getSolution(), sol, 10)) {
                userService.addTestResult(lesson.getName(),subject.getName(), 0,new TestAsyncCallback());
            }
        }
        catch(Exception e) {
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