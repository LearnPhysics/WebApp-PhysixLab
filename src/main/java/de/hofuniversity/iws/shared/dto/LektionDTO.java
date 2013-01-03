/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.shared.dto;

import java.io.Serializable;

/**
 *
 * @author Andreas
 */
public class LektionDTO implements Serializable {
        String id;
        String title;
        String previewURL;
        String lessonText;
        String lesson_name;
        String parent;
        String widget;
        String formular;

    public String getWidget() {
        return widget;
    }

    public void setWidget(String widget_id) {
        this.widget = widget_id;
    }

    public String getFormular() {
        return formular;
    }

    public void setFormular(String formular_id) {
        this.formular = formular_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }       

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
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

    public String getPreviewURL() {
        return previewURL;
    }

    public void setPreviewURL(String previewURL) {
        this.previewURL = previewURL;
    }

    public String getLessonText() {
        return lessonText;
    }

    public void setLessonText(String description) {
        this.lessonText = description;
    }
        
}
