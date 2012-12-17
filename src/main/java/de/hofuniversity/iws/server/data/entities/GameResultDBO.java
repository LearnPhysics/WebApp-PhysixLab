package de.hofuniversity.iws.server.data.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import de.hofuniversity.iws.shared.entitys.*;
import de.hofuniversity.iws.shared.entitys.GameResult;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
public class GameResultDBO implements Serializable, GenericEntity, GameResult {

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
    private GameDBO game;

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

    public User getUser() {
        return user;
    }

    public void setUser(UserDBO user) {
        this.user = user;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(GameDBO game) {
        this.game = game;
    }
}
