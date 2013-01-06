/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.Lektion;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.*;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.ui.*;
import de.hofuniversity.iws.client.jsonbeans.*;
import de.hofuniversity.iws.client.widgets.MathWidget;

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
    MathWidget formular;

    interface LessonUiBinder extends UiBinder<Widget, Lesson> {
    }

    public Lesson(LessonJson lesson, SubjectJson subject) {
        initWidget(uiBinder.createAndBindUi(this));

        title.setInnerText(lesson.getTitle());
        text.setInnerText(lesson.getText());        
        
        theme.setInnerText(subject.getTitle());

        formular.setMathText(lesson.getMath());
//        if (lesson.getExperiment() != null) {
//            experiment.add(lesson.getExperiment());
//        }
    }
}
