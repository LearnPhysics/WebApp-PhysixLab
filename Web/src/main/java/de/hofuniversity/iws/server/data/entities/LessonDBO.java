/*
 * Copyright (C) 2012 Oliver Schütz
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.hofuniversity.iws.server.data.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 *
 * @author Oliver Schütz
 */
@Entity
@Table
public class LessonDBO implements Serializable, GenericEntity {

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
