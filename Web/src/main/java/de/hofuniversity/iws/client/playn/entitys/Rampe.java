/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.playn.entitys;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import playn.core.ImmediateLayer.Renderer;
import playn.core.*;

/**
 *
 * @author Daniel Heinrich <DannyNullZwo@gmail.com>
 */
public class Rampe extends PhysicEntity {

    private final Body body;
    private final float width, height;
    private final Layer layer;

    public static class Builder extends PhysicEntity.Builder<Rampe> {

        private float width = 1f;
        private float angle = 0f;

        public Builder(World world) {
            super(world);
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

            return new Rampe(body, width, height);
        }
    }

    public static Builder build(World world) {
        return new Builder(world);
    }

    private Rampe(Body body, final float width, final float height) {
        this.width = width;
        this.height = height;
        this.body = body;

        layer = PlayN.graphics().createImmediateLayer(new Renderer() {
            float[] v = new float[]{
                0, 0,
                width, 0,
                width, height
            };
            int[] i = new int[]{0, 1, 2};

            @Override
            public void render(Surface surface) {
                surface.clear();
                surface.setFillColor(Color.rgb(220, 125, 0));
                surface.fillTriangles(v,i);
            }
        });
        layer.setOrigin(width/2, height/2);
        update(0);
    }

    @Override
    public Layer getLayer() {
        return layer;
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
