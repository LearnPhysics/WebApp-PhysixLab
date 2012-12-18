/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.playn.entitys;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import playn.core.*;

import static playn.core.PlayN.graphics;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public abstract class ImageEntity extends PhysicEntity {

    public static abstract class Builder<T extends ImageEntity> extends PhysicEntity.Builder<T> {

        protected final float imageScaleFactor;

        public Builder(float imageScaleFactor, World world) {
            super(world);
            this.imageScaleFactor = imageScaleFactor;
        }
    }
    protected final float imageScaleFactor;
    private float prevX, prevY, prevA;
    private CanvasImage image;
    private ImageLayer layer;

    public ImageEntity(float imageScaleFactor) {
        this.imageScaleFactor = imageScaleFactor;
    }

    protected void init() {
        image = createImage();
        layer = graphics().createImageLayer(image);
        image.addCallback(new ResourceCallback<Image>() {
            @Override
            public void done(Image image) {
                layer.setOrigin(image.width() / 2f, image.height() / 2f);
                layer.setScale(getWidth() / image.width(), getHeight() / image.height());
            }

            @Override
            public void error(Throwable err) {
                PlayN.log().error("Error loading image: " + err.getMessage());
            }
        });
    }

    @Override
    public Layer getLayer() {
        return layer;
    }

    public CanvasImage getImage() {
        return image;
    }

    private CanvasImage createImage() {
        return PlayN.graphics().createImage(getWidth() * imageScaleFactor, getHeight() * imageScaleFactor);
    }

    @Override
    public final void paint(float alpha) {
        float x = (getBody().getPosition().x * alpha) + (prevX * (1f - alpha));
        float y = (getBody().getPosition().y * alpha) + (prevY * (1f - alpha));
        float a = (getBody().getAngle() * alpha) + (prevA * (1f - alpha));
        layer.setTranslation(x, y);
        layer.setRotation(a);
    }

    @Override
    public final void update(float delta) {
        prevX = getBody().getPosition().x;
        prevY = getBody().getPosition().y;
        prevA = getBody().getAngle();
    }

    public void setLinearVelocity(float x, float y) {
        getBody().setLinearVelocity(new Vec2(x, y));
    }

    public void setAngularVelocity(float w) {
        getBody().setAngularVelocity(w);
    }

    public void setPos(float x, float y) {
        getBody().setTransform(new Vec2(x, y), getBody().getAngle());
        prevX = x;
        prevY = y;
    }

    public void setAngle(float a) {
        getBody().setTransform(getBody().getPosition(), a);
        prevA = a;
    }

    @Override
    public void destroy() {
        super.destroy();
        if (layer != null) {
            GroupLayer parent = layer.parent();
            if (parent != null) {
                parent.remove(layer);
            }
            layer = null;
        }
    }
}
