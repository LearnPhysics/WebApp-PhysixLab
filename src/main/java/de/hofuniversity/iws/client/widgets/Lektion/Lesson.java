/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.Lektion;

import de.hofuniversity.iws.client.jsonbeans.*;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.*;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.ui.*;

/**
 *
 * @author Oliver
 */
public class Lesson extends Composite {

    private static LessonUiBinder uiBinder = GWT.create(LessonUiBinder.class);
    @UiField
    HeadingElement theme;
    @UiField
    HeadingElement title;
    @UiField
    ParagraphElement text;
    @UiField
    VerticalPanel experiment;
    @UiField
    VerticalPanel formular;

    interface LessonUiBinder extends UiBinder<Widget, Lesson> {
    }

    public Lesson(LessonJson lesson, SubjectJson subject) {
        initWidget(uiBinder.createAndBindUi(this));

        title.setInnerText(lesson.getTitle());
        text.setInnerText(lesson.getText());        
        
        theme.setInnerText(subject.getTitle());

//        if (lesson.getExperiment() != null) {
//            experiment.add(lesson.getExperiment());
//        }
//        if (lesson.getFormular() != null) {
//            formular.add(lesson.getFormular());
//        }
    }
}
