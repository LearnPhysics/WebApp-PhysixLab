/*
  * Copyright (C) 2012 Daniel Heinrich
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

    public void setUserName(String name) {
        this.name = name;
    }

    public void setLastName(String last) {
        this.last = last;
    }

    public void setFirstName(String first) {
        this.first = first;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setUserPic(String pic) {
        this.pic = pic;
    }

    public void setBirthDate(Timestamp birt) {
        this.birt = birt;
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
