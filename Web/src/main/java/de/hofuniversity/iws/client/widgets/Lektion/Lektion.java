/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.Lektion;

import com.chrisgammage.ginjitsu.client.AfterInject;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.inject.assistedinject.*;
import de.hofuniversity.iws.client.jsonbeans.*;
import de.hofuniversity.iws.client.widgets.Lektion.Lektion.LektionUiBinder;
import de.hofuniversity.iws.client.widgets.base.CrumbPage;
import de.hofuniversity.iws.client.widgets.history.LektionsElement.LektionsElementFactory;
import de.hofuniversity.iws.shared.services.LessonServiceAsync;
import javax.inject.*;

/**
 *
 * @author Oliver
 */
public class Lektion extends CrumbPage<LektionUiBinder> {

    //<editor-fold defaultstate="collapsed" desc="ui-stuff">
    public interface LektionUiBinder extends UiBinder<Widget, Lektion> {
    }
    @UiField Lektion.LektionStyle style;
    @UiField SpanElement rail;
    @UiField HorizontalPanel railContent;
    @UiField FocusPanel tab1;
    @UiField FocusPanel tab2;
    @UiField HTMLPanel page;

    interface LektionStyle extends CssResource {

        String posLektion();

        String posTest();

        String selected();

        String tab();

        String tab1();

        String tab2();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Builder">
    public static class Builder {

        private final LessonServiceAsync lessonService;
        private final LektionFactory factory;
        private LessonJson lessonBean;
        private SubjectJson subjectBean;
        private String lessonName, subjectName;

        @Inject
        public Builder(LessonServiceAsync lessonService, LektionFactory factory) {
            this.lessonService = lessonService;
            this.factory = factory;
        }

        public Builder withLesson(String name) {
            lessonName = name;
            if (lessonBean != null) {
                if (!lessonBean.getName().equals(name)) {
                    lessonBean = null;
                }
            }
            return this;
        }

        public Builder withLesson(LessonJson bean) {
            lessonBean = bean;
            return this;
        }

        public Builder withSubject(String name) {
            subjectName = name;
            if (subjectBean != null) {
                if (!subjectBean.getName().equals(name)) {
                    subjectBean = null;
                }
            }
            return this;
        }

        public Builder withSubject(SubjectJson bean) {
            subjectBean = bean;
            return this;
        }

        private class SubAsync implements AsyncCallback<String> {

            private final AsyncCallback<Lektion> callback;
            private final boolean wasSubject;

            public SubAsync(AsyncCallback<Lektion> callback, boolean wasSubject) {
                this.callback = callback;
                this.wasSubject = wasSubject;
            }

            @Override
            public void onFailure(Throwable caught) {
                callback.onFailure(caught);
            }

            @Override
            public void onSuccess(String result) {
                if (wasSubject) {
                    subjectBean = SubjectJson.create(result);
                } else {
                    lessonBean = LessonJson.create(result);
                }
                nextRound();
            }

            public void nextRound() {
                if (subjectBean != null && lessonBean != null) {
                    callback.onSuccess(factory.create(lessonBean, subjectBean));
                } else if (lessonBean == null) {
                    lessonService.getLesson(lessonName, new SubAsync(callback, false));
                } else if (subjectBean == null) {
                    if (subjectName == null) {
                        lessonService.getSubject(lessonBean.getThema(), new SubAsync(callback, true));
                    } else {
                        lessonService.getSubject(subjectName, new SubAsync(callback, true));
                    }
                }
            }
        }

        public void create(AsyncCallback<Lektion> callback) {
            new SubAsync(callback, true).nextRound();
        }
    }
    //</editor-fold>
    public final static String NAME = "lektion";
    private final LessonJson lesson;
    private final SubjectJson subject;

    public interface LektionFactory {

        public Lektion create(LessonJson lektion, SubjectJson subject);
    }

    @AssistedInject
    public Lektion(LektionsElementFactory element, @Assisted LessonJson lesson, @Assisted SubjectJson subject) {
        super(element.create(subject, lesson), NAME + "?" + lesson.getName());
        this.lesson = lesson;
        this.subject = subject;
    }

    @Override
    public void initWidget() {
        railContent.add(new Lesson(lesson, subject));
        railContent.add(new Test(lesson.getTest(), lesson, subject));
    }

    public String getLessonTitle() {
        return lesson.getTitle();
    }

    @UiHandler("tab1")
    public void changeToTab1(ClickEvent ev) {
        setPosition(1);
    }

    @UiHandler("tab2")
    public void changeToTab2(ClickEvent ev) {
        setPosition(2);
    }

    public void setPosition(int pos) {
        rail.removeClassName(rail.getClassName());
        tab1.removeStyleName(style.selected());
        tab2.removeStyleName(style.selected());

        switch (pos) {
            case 1:
                rail.setClassName(style.posLektion());
                tab1.addStyleName(style.selected());
                break;
            case 2:
                rail.setClassName(style.posTest());
                tab2.addStyleName(style.selected());
                break;
            default:
                System.err.println("Wrong parameter! Only 1 to 2 allowed. Given was: " + pos);
        }
    }
}
