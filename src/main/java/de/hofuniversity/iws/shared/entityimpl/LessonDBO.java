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

    @Override
    public boolean isDetached() {
        return detached;
    }

    @Override
    public void setDetached(boolean detached) {
        this.detached = detached;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<LessonProgressDBO> getLessonProgressList() {
        return lessonProgressList;
    }

    public void setLessonProgressList(List<LessonProgressDBO> lessonProgressList) {
        this.lessonProgressList = lessonProgressList;
    }

    @Override
    public SubjectAreaDBO getSubjectArea() {
        return subjectArea;
    }

    public void setSubjectArea(SubjectAreaDBO subjectArea) {
        this.subjectArea = subjectArea;
    }

    @Override
    public List<GameDBO> getGameList() {
        return gameList;
    }

    public void setGameList(List<GameDBO> gameList) {
        this.gameList = gameList;
    }

    @Override
    public LessonDBO getParentLesson() {
        return parentLesson;
    }

    public void setParentLesson(LessonDBO parentLesson) {
        this.parentLesson = parentLesson;
    }
}
