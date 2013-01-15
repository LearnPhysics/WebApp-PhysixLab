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
import java.sql.Timestamp;

import javax.persistence.*;

/**
 *
 * @author Oliver Schütz
 */
@Entity
@Table
public class LessonProgressDBO implements Serializable, GenericEntity {

    @Transient
    private boolean detached = false;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private Timestamp date;
    @Column
    private Integer points;
    @ManyToOne
    private UserDBO user;
    @ManyToOne
    private LessonDBO lesson;

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

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public UserDBO getUser() {
        return user;
    }

    public void setUser(UserDBO user) {
        this.user = user;
    }

    public LessonDBO getLesson() {
        return lesson;
    }

    public void setLesson(LessonDBO lesson) {
        this.lesson = lesson;
    }
}
