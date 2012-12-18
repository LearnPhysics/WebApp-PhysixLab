/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.TestEntities;

import com.google.gwt.user.client.ui.Widget;

/**
 *
 * @author Oliver
 */
public class TestLektion {

    String title;
    String lessonText;
    Widget experiment;
    Widget formular;
    TestTest test;

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

    public TestTest getTest() {
        return test;
    }

    public void setTest(TestTest test) {
        this.test = test;
    }
}
