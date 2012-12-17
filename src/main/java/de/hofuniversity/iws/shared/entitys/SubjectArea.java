/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.shared.entitys;

import java.io.Serializable;
import java.util.List;


/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public interface SubjectArea extends Serializable {

    public String getName();

    public void setName(String name);

    public List<? extends Lesson> getLessonList();
}
