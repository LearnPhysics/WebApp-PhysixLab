/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.playn;

import playn.core.*;

/**
 *
 * @author some
 */
public class ImageFactory {

    private final float imageScaleFactor;

    public ImageFactory(float imageScaleFactor) {
        this.imageScaleFactor = imageScaleFactor;
    }

    public ImageLayer createImageLayer(float width, float height) {
        ImageLayer layer = PlayN.graphics().createImageLayer(createImage(width, height));
        layer.setOrigin(width * imageScaleFactor / 2, height * imageScaleFactor / 2);
        layer.setScale(1 / imageScaleFactor);
        return layer;
    }

    public Image createImage(float width, float height) {
        return PlayN.graphics().createImage(width * imageScaleFactor, height * imageScaleFactor);
    }
}
