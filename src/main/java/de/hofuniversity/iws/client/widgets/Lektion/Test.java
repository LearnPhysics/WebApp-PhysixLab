/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.Lektion;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import de.hofuniversity.iws.client.widgets.Lektion.Lektion.TestTest;
import de.hofuniversity.iws.client.widgets.LoginPage;
import de.hofuniversity.iws.shared.services.Utilities;

/**
 *
 * @author Oliver
 */
public class Test extends Composite {
    
    private static TestUiBinder uiBinder = GWT.create(TestUiBinder.class);
    private TestTest test;
    
    @UiField HeadingElement title;
    @UiField ParagraphElement text;
    @UiField VerticalPanel illustration;
    @UiField TextBox solution;
    @UiField Button submit;
    
    interface TestUiBinder extends UiBinder<Widget, Test> {
    }
    
    public Test() {
        initWidget(uiBinder.createAndBindUi(this));
    }
    
    public Test(TestTest test) {
        initWidget(uiBinder.createAndBindUi(this));
        this.test = test;
        setup();
    }
    
    private void setup() {
        title.setInnerText(test.getTitle());
        text.setInnerText(test.getDescription());
        if(test.getIllustration() != null) {
            illustration.add(test.getIllustration());
        }
    }
    
    @UiHandler("submit")
    public void checkSolution(ClickEvent ev) {
        try {
            double sol = Double.parseDouble(solution.getText());
            if(Utilities.isSimilar(test.getSolution(), sol, 10)) {
                test.setPassed(true);
                // Spiele Lösungsanimation
            }
        }
        catch(Exception e) {
            System.err.println("Input not parsable!");
        }
    }
}