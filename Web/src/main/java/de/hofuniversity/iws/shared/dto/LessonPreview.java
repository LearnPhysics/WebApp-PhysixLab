/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.shared.dto;

import java.io.Serializable;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class LessonPreview implements Serializable {

    private String name;
    private String parent;
    private String imageUrl;

    private LessonPreview() {
    }

    public LessonPreview(String name, String parent, String imageUrl) {
        this.name = name;
        this.parent = parent;
        this.imageUrl = imageUrl;
    }

    public String getParent() {
        return parent;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
    }
}
