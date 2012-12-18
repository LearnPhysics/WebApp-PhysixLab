/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.playn.entitys;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import playn.core.*;

/**
 *
 * @author some
 */
public class Rampe extends ImageEntity {

    private final Body body;
    private final float width, height;

    public static class Builder extends ImageEntity.Builder<Rampe> {

        private float width = 1f;
        private float angle = 0f;

        public Builder(float imageScaleFactor, World world) {
            super(imageScaleFactor, world);
        }

        public Builder setWidth(float w) {
            width = w;
            return this;
        }

        public Builder setAngle(float angle) {
            this.angle = angle;
            return this;
        }

        @Override
        public Rampe create() {
            float height = (float) (width * Math.tan(angle));

            BodyDef bodyDef = new BodyDef();
            bodyDef.type = BodyType.STATIC;
            bodyDef.position = new Vec2(x, y);

            PolygonShape shape = new PolygonShape();
            Vec2[] v = new Vec2[]{new Vec2(-width / 2, -height / 2),
                                  new Vec2(width / 2, -height / 2),
                                  new Vec2(width / 2, height / 2)};
            shape.set(v, v.length);

            FixtureDef fixtureDef = new FixtureDef();
            fixtureDef.shape = shape;
            fixtureDef.density = 0.1f;
            fixtureDef.friction = 0.1f;
            fixtureDef.restitution = 0.35f;

            Body body = world.createBody(bodyDef);
            body.createFixture(fixtureDef);
            body.setLinearDamping(0.2f);

            return new Rampe(body, imageScaleFactor, width, height);
        }
    }

    public static Builder build(World world, float imageScaleFactor) {
        return new Builder(imageScaleFactor, world);
    }

    private Rampe(Body body, float scale, float width, float height) {
        super(scale);
        this.width = width;
        this.height = height;
        this.body = body;

        init();

        CanvasImage img = getImage();

        Path path = img.canvas().createPath();
        path.moveTo(0, 0);
        path.lineTo(img.width(), 0);
        path.lineTo(img.width(), img.height());
        path.close();

        img.canvas().setFillColor(Color.rgb(220, 125, 0));
        img.canvas().fillPath(path);
    }

    @Override
    public Body getBody() {
        return body;
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }
}
