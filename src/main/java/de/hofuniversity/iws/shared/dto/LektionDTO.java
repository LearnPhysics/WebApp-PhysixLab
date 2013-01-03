/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.shared.dto;

import java.io.Serializable;

import de.hofuniversity.iws.shared.GameFactory;

/**
 *
 * @author Andreas
 */
public class LektionDTO implements Serializable {

    private String id;
    private String title;
    private String previewURL;
    private String lessonText;
    private String lesson_name;
    private String parent;
    private String formularID;
    private GameFactory widgetFactory;

    public String getFormularID() {
        return formularID;
    }

    public void setFormularID(String formularID) {
        this.formularID = formularID;
    }

    public GameFactory getWidgetFactory() {
        return widgetFactory;
    }

    public void setWidgetFactory(GameFactory widgetFactory) {
        this.widgetFactory = widgetFactory;
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
