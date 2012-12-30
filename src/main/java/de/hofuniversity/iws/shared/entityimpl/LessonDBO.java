package de.hofuniversity.iws.shared.entityimpl;

import java.io.Serializable;
import java.util.List;

import de.hofuniversity.iws.shared.entitys.Lesson;

import javax.persistence.*;

@Entity
@Table
public class LessonDBO implements Serializable, GenericEntity, Lesson {

    @Transient
    private boolean detached = false;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @OneToMany(mappedBy = "lesson")
    private List<LessonProgressDBO> lessonProgressList;
    @ManyToOne
    private SubjectAreaDBO subjectArea;
    @ManyToMany(mappedBy = "lessonList")
    private List<GameDBO> gameList;
    @ManyToOne
    private LessonDBO parentLesson;

    public boolean isDetached() {
        return detached;
    }

    public void setDetached(boolean detached) {
        this.detached = detached;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LessonProgressDBO> getLessonProgressList() {
        return lessonProgressList;
    }

    public void setLessonProgressList(List<LessonProgressDBO> lessonProgressList) {
        this.lessonProgressList = lessonProgressList;
    }

    public SubjectAreaDBO getSubjectArea() {
        return subjectArea;
    }

    public void setSubjectArea(SubjectAreaDBO subjectArea) {
        this.subjectArea = subjectArea;
    }

    public List<GameDBO> getGameList() {
        return gameList;
    }

    public void setGameList(List<GameDBO> gameList) {
        this.gameList = gameList;
    }

    public LessonDBO getParentLesson() {
        return parentLesson;
    }

    public void setParentLesson(LessonDBO parentLesson) {
        this.parentLesson = parentLesson;
    }
}
