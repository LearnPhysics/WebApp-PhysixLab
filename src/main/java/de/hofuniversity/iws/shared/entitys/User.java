/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.shared.entitys;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public interface User extends Serializable {

    public String getUserName();

    public void setUserName(String userName);

    public String getLastName();

    public void setLastName(String lastName);

    public String getFirstName();

    public void setFirstName(String firstName);

    public Timestamp getBirthDate();

    public void setBirthDate(Timestamp birthDate);

    public String getCity();

    public void setCity(String city);

    public String getUserPic();

    public void setUserPic(String userPic);

    public List<? extends NetworkAccount> getNetworkAccountList();

    public List<? extends GameResult> getGameResultList();

    public List<? extends LessonProgress> getLessonProgressList();

    public List<? extends User> getFriends();

    public List<? extends User> getDevotees();

    public List<? extends User> getBilateralFriends();
}
