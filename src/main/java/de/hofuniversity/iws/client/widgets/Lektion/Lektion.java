/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.Lektion;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 *
 * @author Oliver
 */
public class Lektion extends Composite {

    private static LektionUiBinder uiBinder = GWT.create(LektionUiBinder.class);
    private TestLektion lektion;

    interface LektionUiBinder extends UiBinder<Widget, Lektion> {
    }

    public Lektion() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    public Lektion(TestLektion lektion) {
        initWidget(uiBinder.createAndBindUi(this));
        this.lektion = lektion;
    }

    public class TestLektion {

        String thema;
        String title;
        String lessonText;
        Widget experiment;
        Widget formular;

        public String getThema() {
            return thema;
        }

        public void setThema(String thema) {
            this.thema = thema;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLessonText() {
            return lessonText;
        }

        public void setLessonText(String lessonText) {
            this.lessonText = lessonText;
        }

        public Widget getExperiment() {
            return experiment;
        }

        public void setExperiment(Widget experiment) {
            this.experiment = experiment;
        }

        public Widget getFormular() {
            return formular;
        }

        public void setFormular(Widget formular) {
            this.formular = formular;
        }
    }

    public class TestTest {

        String title;
        String description;
        Widget illustration;
        double solution;
        boolean passed;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Widget getIllustration() {
            return illustration;
        }

        public void setIllustration(Widget illustration) {
            this.illustration = illustration;
        }

        public double getSolution() {
            return solution;
        }

        public void setSolution(double solution) {
            this.solution = solution;
        }

        public boolean isPassed() {
            return passed;
        }

        public void setPassed(boolean passed) {
            this.passed = passed;
        }
    }
}
