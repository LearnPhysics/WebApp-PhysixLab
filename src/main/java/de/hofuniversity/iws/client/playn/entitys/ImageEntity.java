/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.playn.entitys;

import org.jbox2d.common.Vec2;
import playn.core.*;

import static playn.core.PlayN.graphics;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public abstract class ImageEntity implements PhysicEntity {

    private float prevX, prevY, prevA;
    private ImageLayer layer;

    protected final void init() {
        layer = graphics().createImageLayer(getImage());
        getImage().addCallback(new ResourceCallback<Image>() {
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

    public abstract Image getImage();
}
