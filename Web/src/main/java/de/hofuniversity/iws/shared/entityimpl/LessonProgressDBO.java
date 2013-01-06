package de.hofuniversity.iws.shared.entityimpl;

import java.io.Serializable;
import java.sql.Timestamp;

import de.hofuniversity.iws.shared.entitys.LessonProgress;
import javax.persistence.*;

@Entity
@Table
public class LessonProgressDBO implements Serializable, GenericEntity, LessonProgress {

    @Transient
    private boolean detached = false;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private Timestamp date;
    @Column
    private Integer points;
    @ManyToOne(cascade = CascadeType.ALL)
    private UserDBO user;
    @ManyToOne(cascade = CascadeType.ALL)
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

    @Override
    public Timestamp getDate() {
        return date;
    }

    @Override
    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public Integer getPoints() {
        return points;
    }

    @Override
    public void setPoints(Integer points) {
        this.points = points;
    }

    @Override
    public UserDBO getUser() {
        return user;
    }

    public void setUser(UserDBO user) {
        this.user = user;
    }

    @Override
    public LessonDBO getLesson() {
        return lesson;
    }

    public void setLesson(LessonDBO lesson) {
        this.lesson = lesson;
    }
}
