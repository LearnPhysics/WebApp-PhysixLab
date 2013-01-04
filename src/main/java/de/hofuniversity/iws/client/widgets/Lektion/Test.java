/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.Lektion;

import de.hofuniversity.iws.client.jsonbeans.TestJson;
import de.hofuniversity.iws.shared.services.Utilities;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.*;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.ui.*;

/**
 *
 * @author Oliver
 */
public class Test extends Composite {

    private static TestUiBinder uiBinder = GWT.create(TestUiBinder.class);
    private TestJson test;
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

    public Test(TestJson test) {
        initWidget(uiBinder.createAndBindUi(this));

        this.test = test;
        title.setInnerText(test.getTitle());
        text.setInnerText(test.getProblem());
        illustration.add(new Image(test.getImage()));
    }

    @UiHandler("submit")
    public void checkSolution(ClickEvent ev) {
        try {
            double sol = Double.parseDouble(solution.getText());
            if(Utilities.isSimilar(test.getSolution(), sol, 10)) {
//                test.setPassed(true);
                // Spiele Lösungsanimation
            }
        }
        catch(Exception e) {
            System.err.println("Input not parsable!");
        }
    }
}