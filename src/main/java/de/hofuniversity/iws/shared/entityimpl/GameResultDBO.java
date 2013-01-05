package de.hofuniversity.iws.shared.entityimpl;

import java.io.Serializable;
import java.sql.Timestamp;

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
    public GameDBO getGame() {
        return game;
    }

    public void setGame(GameDBO game) {
        this.game = game;
    }
}
