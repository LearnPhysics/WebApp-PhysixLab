/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.Lektion;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.dom.client.Style;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import de.hofuniversity.iws.client.widgets.Lektion.Lektion.TestLektion;

/**
 *
 * @author Oliver
 */
public class Lesson extends Composite {
    
    private static LessonUiBinder uiBinder = GWT.create(LessonUiBinder.class);
    
    @UiField HeadingElement theme;
    @UiField HeadingElement title;
    @UiField ParagraphElement text;
    @UiField VerticalPanel experiment;
    @UiField VerticalPanel formular;
    
    private TestLektion lesson;
    
    interface LessonUiBinder extends UiBinder<Widget, Lesson> {
    }
    
    public Lesson() {
        initWidget(uiBinder.createAndBindUi(this));
    }
    
    public Lesson(TestLektion lesson) {
        initWidget(uiBinder.createAndBindUi(this));
        this.lesson = lesson;
        setup();
    }
    
    private void setup() {
        theme.setInnerText(lesson.getThema());
        title.setInnerText(lesson.getTitle());
        text.setInnerText(lesson.getLessonText());
        experiment.add(lesson.getExperiment());
        formular.add(lesson.getFormular());
    }
}
