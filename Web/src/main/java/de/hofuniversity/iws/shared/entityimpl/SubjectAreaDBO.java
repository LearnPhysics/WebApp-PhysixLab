package de.hofuniversity.iws.shared.entityimpl;

import java.io.Serializable;
import java.util.List;

import de.hofuniversity.iws.shared.entitys.SubjectArea;
import javax.persistence.*;

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
    public List<LessonDBO> getLessonList() {
        return lessonList;
    }

    public void setLessonList(List<LessonDBO> lessonList) {
        this.lessonList = lessonList;
    }
}
