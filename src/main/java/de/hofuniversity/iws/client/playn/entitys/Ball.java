/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.playn.entitys;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import playn.core.*;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class Ball extends ImageEntity {

    private final Body body;

    public static class Builder extends ImageEntity.Builder<Ball> {

        private float size = 0.5f;

        public Builder(float imageScaleFactor, World world) {
            super(imageScaleFactor, world);
        }

        public Builder setSize(float s) {
            size = s;
            return this;
        }

        @Override
        public Ball create() {
            FixtureDef fixtureDef = new FixtureDef();
            BodyDef bodyDef = new BodyDef();
            bodyDef.type = BodyType.DYNAMIC;
            bodyDef.position = new Vec2(x, y);
            Body body = world.createBody(bodyDef);

            CircleShape circleShape = new CircleShape();
            circleShape.m_radius = size / 2;
            fixtureDef.shape = circleShape;
            fixtureDef.density = 3f;
            fixtureDef.friction = 0.4f;
            fixtureDef.restitution = 0.4f;
            circleShape.m_p.set(0, 0);
            body.createFixture(fixtureDef);
            body.setLinearDamping(0.1f);
            body.setAngularDamping(0.1f);

            return new Ball(body, imageScaleFactor, size);
        }
    }

    public static Builder build(World world, float imageScaleFactor) {
        return new Builder(imageScaleFactor, world);
    }

    private Ball(Body body, float scale, float size) {
        super(scale);
        this.body = body;
        this.size = size;

        listener = new MouseJointHandler(body);

        init();

        CanvasImage img = getImage();
        img.canvas().clear();
        img.canvas().setFillColor(Color.rgb(255, 0, 0));
        img.canvas().fillCircle(img.width() / 2, img.height() / 2, img.width() / 2);
    }

    @Override
    public Body getBody() {
        return body;
    }

    @Override
    public Image getImage() {
        CanvasImage img = PlayN.graphics().createImage(getWidth(), getHeight());
        img.canvas().clear();
        img.canvas().setFillColor(Color.rgb(255, 0, 0));
        img.canvas().fillCircle(5, 5, 5);
        return img;
    }

    @Override
    public float getWidth() {
        return 10;
    }

    @Override
    public float getHeight() {
        return 10;
    }
}
