package de.hofuniversity.iws.shared.entityimpl;

import java.io.Serializable;
import java.util.List;

import de.hofuniversity.iws.shared.entitys.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
public class SubjectAreaDBO implements Serializable, GenericEntity, SubjectArea {

    @Transient
    private boolean detached = false;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @OneToMany(mappedBy = "subjectArea")
    private List<LessonDBO> lessonList;

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

    public List<LessonDBO> getLessonList() {
        return lessonList;
    }

    public void setLessonList(List<LessonDBO> lessonList) {
        this.lessonList = lessonList;
    }
}