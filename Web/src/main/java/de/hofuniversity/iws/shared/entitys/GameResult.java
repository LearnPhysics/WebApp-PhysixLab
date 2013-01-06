/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.shared.entitys;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public interface GameResult extends Serializable {

    public Timestamp getDate();

    public void setDate(Timestamp date);

    public Integer getPoints();

    public void setPoints(Integer points);

    public User getUser();

    public Game getGame();
}
