/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.shared.dto;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class User implements Serializable {
    private String name, last,first,city,pic;
    private Timestamp birt;

    public User() {
    }

    public User(String name, String last, String first, String city, String pic, Timestamp birt) {
        this.name = name;
        this.last = last;
        this.first = first;
        this.city = city;
        this.pic = pic;
        this.birt = birt;
    }
    
    public String getUserName()
    {
        return name;
    }

    public String getLastName()
    {
        return last;
    }

    public String getFirstName()
    {
        return first;
    }

    public Timestamp getBirthDate()
    {
        return birt;
    }

    public String getCity()
    {
        return city;
    }

    public String getUserPic()
    {
        return pic;
    }
}
