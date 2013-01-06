/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.Lektion;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.*;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import de.hofuniversity.iws.client.jsonbeans.LessonJson;
import de.hofuniversity.iws.client.jsonbeans.TestJson;
import de.hofuniversity.iws.shared.services.UserService;
import de.hofuniversity.iws.shared.services.UserServiceAsync;
import de.hofuniversity.iws.shared.services.Utilities;

/**
 *
 * @author Oliver
 */
public class Test extends Composite {

    private static TestUiBinder uiBinder = GWT.create(TestUiBinder.class);
    private TestJson test;
    private String lessonName;
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

    public Test(TestJson test, String lessonName) {
        initWidget(uiBinder.createAndBindUi(this));

        this.test = test;
        this.lessonName = lessonName;
        title.setInnerText(test.getTitle());
        text.setInnerText(test.getProblem());
        illustration.add(new Image(test.getImage()));
    }

    @UiHandler("submit")
    public void checkSolution(ClickEvent ev) {
        try {
            double sol = Double.parseDouble(solution.getText());
            if(Utilities.isSimilar(test.getSolution(), sol, 10)) {
                userService.addTestResult(lessonName, 0,new TestAsyncCallback());
//                test.setPassed(true);
                // Spiele Lösungsanimation
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
            System.out.println("");
        }

    }
}