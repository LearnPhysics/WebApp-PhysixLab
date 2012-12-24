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
    String LessonId;
    String lesson_name;
    String title;
    String lessonText;
    String previewURL;
    String parent_id;
    Widget experiment;
    String experiment_id;
    Widget formular;
    String formular_id;
    TestTest test;
    TestLektion parent;

    public String getExperiment_id() {
        return experiment_id;
    }

    public void setExperiment_id(String experiment_id) {
        this.experiment_id = experiment_id;
    }

    public String getFormular_id() {
        return formular_id;
    }

    public void setFormular_id(String formular_id) {
        this.formular_id = formular_id;
    } 

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }
    

    public String getLessonId() {
        return LessonId;
    }

    public void setLessonId(String LessonId) {
        this.LessonId = LessonId;
    }
    
    public String getLesson_name() {
        return lesson_name;
    }

    public void setLesson_name(String lesson_name) {
        this.lesson_name = lesson_name;
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

    public String getPreviewURL() {
        return previewURL;
    }

    public void setPreviewURL(String previewURL) {
        this.previewURL = previewURL;
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

    public TestLektion getParent() {
        return parent;
    }

    public void setParent(TestLektion parent) {
        this.parent = parent;
    }
}