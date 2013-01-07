/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.Lektion;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.*;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import de.hofuniversity.iws.client.jsonbeans.*;
import de.hofuniversity.iws.client.util.AddressStack;
import de.hofuniversity.iws.client.util.CrumbTuple;
import de.hofuniversity.iws.client.widgets.SubWidgets.BackButton;
import de.hofuniversity.iws.client.widgets.SubWidgets.Breadcrumb;
import de.hofuniversity.iws.shared.services.*;

/**
 *
 * @author Oliver
 */
public class Lektion extends Composite {

    //<editor-fold defaultstate="collapsed" desc="ui-stuff">
    private static LektionUiBinder uiBinder = GWT.create(LektionUiBinder.class);
    @UiField
    Lektion.LektionStyle style;
    @UiField
    SpanElement rail;
    @UiField
    HorizontalPanel railContent;
    @UiField
    ScrollPanel sWrap;
    @UiField
    FocusPanel tab1;
    @UiField
    FocusPanel tab2;
    @UiField HTMLPanel page;

    interface LektionUiBinder extends UiBinder<Widget, Lektion> {
    }
    
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
        
        private final LessonServiceAsync lessonService = (LessonServiceAsync) GWT.create(LessonService.class);
        private LessonJson lesson;
        private SubjectJson subject;
        private String lessonName, subjectName;
        
        public Builder withLesson(String name) {
            lessonName = name;
            return this;
        }
        
        public Builder withLesson(LessonJson bean) {
            lesson = bean;
            return this;
        }
        
        public Builder withSubject(String name) {
            subjectName = name;
            return this;
        }
        
        public Builder withSubject(SubjectJson bean) {
            subject = bean;
            return this;
        }
        
        private static class SubAsync implements AsyncCallback<String> {
            
            private final Lektion l;
            
            public SubAsync(Lektion l) {
                this.l = l;
            }
            
            @Override
            public void onFailure(Throwable caught) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
            
            @Override
            public void onSuccess(String result) {
                l.ini(SubjectJson.create(result));
            }
        }
        
        public Lektion create() {
            final Lektion l = new Lektion();
            if (subject != null) {
                l.ini(subject);
            } else if (subjectName != null) {
                lessonService.getSubject(subjectName, new SubAsync(l));
            }
            if (lesson != null) {
                l.ini(lesson);
            } else if (lessonName != null) {
                lessonService.getLesson(lessonName, new AsyncCallback<String>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }
                    
                    @Override
                    public void onSuccess(String result) {
                        LessonJson les = LessonJson.create(result);
                        l.ini(les);
                        if (subjectName == null) {
                            lessonService.getSubject(les.getThema(), new SubAsync(l));
                        }
                    }
                });
            }
            return l;
        }
    }
    
    public static Builder build() {
        return new Builder();
    }
    //</editor-fold>
    
    public final static String NAME = "lektion";
    private LessonJson lesson;
    private SubjectJson subject;

    private Lektion() {
        initWidget(uiBinder.createAndBindUi(this));
        sWrap.getElement().getStyle().setOverflow(Style.Overflow.HIDDEN);
        sWrap.setVerticalScrollPosition(0);
    }

    public Lektion(LessonJson lektion, SubjectJson subject) {
        this();

        History.newItem(NAME + "?" + lektion.getName(), false);
        railContent.add(new Lesson(lektion, subject));
        railContent.add(new Test(lektion.getTest(),lesson,subject));
        AddressStack.getInstance().addAddress(new CrumbTuple(this, lektion.getTitle(), 3));
        page.add(new Breadcrumb(3));
        page.add(new BackButton(3));
    }

    private void ini(LessonJson l) {
        lesson = l;
        History.newItem(NAME + "?" + l.getName(), false);
        ini();
    }

    private void ini(SubjectJson s) {
        subject = s;
        ini();
    }

    private void ini() {
        if (lesson != null && subject != null) {
            railContent.add(new Lesson(lesson, subject));
            railContent.add(new Test(lesson.getTest(),lesson,subject));
            AddressStack.getInstance().addAddress(new CrumbTuple(this, lesson.getTitle(), 3));
            page.add(new Breadcrumb(3));
            page.add(new BackButton(3));
            lesson = null;
            subject = null;
        }
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
