package de.hofuniversity.iws.dto;

import java.io.Serializable;
import java.util.List;

public class LessonDTO implements Serializable {

    private boolean detached = false;

    private Long id;

    private String name;

    private List<LessonProgressDTO> lessonProgressList;

    private SubjectAreaDTO subjectArea;

    private List<GameDTO> gameList;
    
    private LessonDTO parentLesson;

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

    public List<LessonProgressDTO> getLessonProgressList() {
        return lessonProgressList;
    }

    public void setLessonProgressList(List<LessonProgressDTO> lessonProgressList) {
        this.lessonProgressList = lessonProgressList;
    }

    public SubjectAreaDTO getSubjectArea() {
        return subjectArea;
    }

    public void setSubjectArea(SubjectAreaDTO subjectArea) {
        this.subjectArea = subjectArea;
    }

    public List<GameDTO> getGameList() {
        return gameList;
    }

    public void setGameList(List<GameDTO> gameList) {
        this.gameList = gameList;
    }

    public LessonDTO getParentLesson() {
        return parentLesson;
    }

    public void setParentLesson(LessonDTO parentLesson) {
        this.parentLesson = parentLesson;
    }
}
