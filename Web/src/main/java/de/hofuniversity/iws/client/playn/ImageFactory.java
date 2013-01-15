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
package de.hofuniversity.iws.client.playn;

import playn.core.*;

/**
 *
 * @author Daniel Heinrich <DannyNullZwo@gmail.com>
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
