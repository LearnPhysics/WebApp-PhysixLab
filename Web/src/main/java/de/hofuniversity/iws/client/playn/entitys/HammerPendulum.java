/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.playn.entitys;

import de.hofuniversity.iws.client.playn.entitys.PhysicEntity.HasContactListener;
import de.hofuniversity.iws.client.playn.entitys.PhysicEntity.HasInteractionListener;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import org.jbox2d.dynamics.joints.*;
import playn.core.ImmediateLayer.Renderer;
import playn.core.*;

/**
 *
 * @author some
 */
public class HammerPendulum extends PhysicEntity implements HasContactListener, HasInteractionListener {

    private final Body body;
    private final Layer layer;
    private final float width;
    private final float height;
    private final InteractionListener listener;

    //<editor-fold defaultstate="collapsed" desc="Builder">
    public static class Builder extends PhysicEntity.Builder<HammerPendulum> {

        private float width = 1f;
        private float height = 0.5f;
        private float length = 2f;

        public Builder(World world) {
            super(world);
        }

        public Builder setWidth(float w) {
            width = w;
            return this;
        }

        public Builder setHeight(float height) {
            this.height = height;
            return this;
        }

        public Builder setSize(float width, float height) {
            this.height = height;
            this.width = width;
            return this;
        }

        public Builder setLength(float length) {
            this.length = length;
            return this;
        }

        @Override
        public HammerPendulum create() {

            BodyDef bdef = new BodyDef();
            bdef.type = BodyType.DYNAMIC;
            bdef.position = new Vec2(x, y - length);

            FixtureDef fdef = new FixtureDef();
            PolygonShape s = new PolygonShape();
            s.setAsBox(width / 2, height / 2);
            fdef.shape = s;
            fdef.density = 15;
            fdef.friction = 0.1f;
            fdef.restitution = 0.96f;

            Body body = world.createBody(bdef);
            body.createFixture(fdef);
            body.setLinearDamping(0.01f);

            RevoluteJointDef jdef = new RevoluteJointDef();
            BodyDef bdef2 = new BodyDef();
            bdef2.type = BodyType.STATIC;
            bdef2.position = new Vec2(x, y);
            final Body body2 = world.createBody(bdef2);
            jdef.initialize(body, body2, new Vec2(x, y));
            final Joint joint = world.createJoint(jdef);

            return new HammerPendulum(body, width, height, length){

                @Override
                public void destroy() {
                    super.destroy();
                    world.destroyBody(body2);
                    world.destroyJoint(joint);
                }
            };
        }
    }

    public static Builder build(World world, float imageScaleFactor) {
        return new Builder(world);
    }
    //</editor-fold>

    private HammerPendulum(Body body, final float width, final float height, final float len) {
        this.body = body;
        this.width = width;
        this.height = height;
        listener = new MouseJointHandler(body);

        layer = PlayN.graphics().createImmediateLayer(new Renderer() {
            @Override
            public void render(Surface surface) {
                surface.clear();
                surface.setFillColor(Color.rgb(0, 255, 0));
                surface.fillRect(-width/2, -height/2, width, height);
                surface.fillRect(-0.02f, 0, 0.02f, len);
            }
        });
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
    public void setPos(float x, float y) {
    }

    @Override
    public void setAngularVelocity(float w) {
        super.setAngularVelocity(w);
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }

    @Override
    public void contact(PhysicEntity other, float impulse) {
        System.out.println("Bam!");
    }

    @Override
    public InteractionListener getListener() {
        return listener;
    }
}
