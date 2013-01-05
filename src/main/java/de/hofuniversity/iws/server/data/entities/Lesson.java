package de.hofuniversity.iws.server.data.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table
public class Lesson implements Serializable, GenericEntity {

    private boolean detached = false;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @OneToMany(mappedBy = "lesson")
    private List<LessonProgress> lessonProgressList;
    @ManyToOne
    private SubjectArea subjectArea;
    @ManyToMany(mappedBy = "lessonList")
    private List<Game> gameList;
    @ManyToOne
    private Lesson parentLesson;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LessonProgress> getLessonProgressList() {
        return lessonProgressList;
    }

    public void setLessonProgressList(List<LessonProgress> lessonProgressList) {
        this.lessonProgressList = lessonProgressList;
    }

    public SubjectArea getSubjectArea() {
        return subjectArea;
    }

    public void setSubjectArea(SubjectArea subjectArea) {
        this.subjectArea = subjectArea;
    }

    public List<Game> getGameList() {
        return gameList;
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }

    public Lesson getParentLesson() {
        return parentLesson;
    }

    public void setParentLesson(Lesson parentLesson) {
        this.parentLesson = parentLesson;
    }
}
