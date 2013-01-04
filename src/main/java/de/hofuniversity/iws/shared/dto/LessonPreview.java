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

    private final String name;
    private final String parent;
    private final String imageUrl;

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
