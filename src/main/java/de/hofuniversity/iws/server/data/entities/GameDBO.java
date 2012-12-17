package de.hofuniversity.iws.server.data.entities;

import java.io.Serializable;
import java.util.List;

import de.hofuniversity.iws.shared.entitys.*;

import javax.persistence.*;

@Entity
@Table
public class GameDBO implements Serializable, GenericEntity, Game {

    @Transient
    private boolean detached = false;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @OneToMany(mappedBy = "game")
    private List<GameResultDBO> gameResultList;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<? extends GameResult> getGameResultList() {
        return gameResultList;
    }

    public void setGameResultList(List<GameResultDBO> gameResultList) {
        this.gameResultList = gameResultList;
    }

    public List<? extends Lesson> getLessonList() {
        return lessonList;
    }

    public void setLessonList(List<LessonDBO> lessonList) {
        this.lessonList = lessonList;
    }
}
