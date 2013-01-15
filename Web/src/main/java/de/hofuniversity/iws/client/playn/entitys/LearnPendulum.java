/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.playn.entitys;

import de.hofuniversity.iws.client.playn.ImageFactory;
import de.hofuniversity.iws.client.playn.entitys.PhysicEntity.HasInteractionListener;
import org.jbox2d.collision.shapes.*;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import org.jbox2d.dynamics.joints.*;
import playn.core.*;
import playn.core.ImmediateLayer.Renderer;

/**
 *
 * @author Daniel Heinrich <DannyNullZwo@gmail.com>
 */
public class LearnPendulum extends PhysicEntity implements HasInteractionListener {

    private final Body koerper, aufhaengung;
    private final float radius;
    private final Layer layer;
//    private final ImageLayer faden;
    private MouseJointHandler listener;

    public static class Builder extends PhysicEntity.Builder<LearnPendulum> {

        private float radius = 0.25f;
        private float length = 2f;
        private final float imageScaleFactor;

        public Builder(float imageScaleFactor, World world) {
            super(world);
            this.imageScaleFactor = imageScaleFactor;
        }

        public Builder setRadius(float r) {
            radius = r;
            return this;
        }

        public Builder setLength(float length) {
            this.length = length;
            return this;
        }

        @Override
        public LearnPendulum create() {
            BodyDef bdef = new BodyDef();
            bdef.type = BodyType.DYNAMIC;
            bdef.position = new Vec2(x, y - 1);

            FixtureDef fdef = new FixtureDef();
            CircleShape s = new CircleShape();
            s.m_radius = radius;
            fdef.shape = s;
            fdef.density = 15;

            Body koerper = world.createBody(bdef);
            koerper.createFixture(fdef);

            BodyDef bdef2 = new BodyDef();
            bdef2.type = BodyType.STATIC;
            bdef2.position = new Vec2(x, y);
            Body aufhaengung = world.createBody(bdef2);

            LearnPendulum p = new LearnPendulum(imageScaleFactor, koerper, aufhaengung, radius);
            p.setLength(length);
            return p;
        }
    }

    public static Builder build(World world, float imageScaleFactor) {
        return new Builder(imageScaleFactor, world);
    }

    private LearnPendulum(final float scale, final Body koerper, final Body aufhaengung, float radius) {
        this.aufhaengung = aufhaengung;
        this.koerper = koerper;
        this.radius = radius;
        listener = new MouseJointHandler(koerper);

        ImageFactory fac = new ImageFactory(scale);

        final CanvasImage img = (CanvasImage) fac.createImage(radius * 2, radius * 2);
        img.canvas().clear();
        img.canvas().setFillColor(Color.rgb(0, 255, 0));
        img.canvas().fillCircle(img.width() / 2, img.height() / 2, img.width() / 2);

        layer = PlayN.graphics().createImmediateLayer(new Renderer() {
            @Override
            public void render(Surface surface) {
                Vec2 sub = koerper.getPosition().sub(aufhaengung.getPosition());
                surface.setFillColor(Color.rgb(255, 0, 0));
                float len = sub.length();
                surface.fillRect(-0.05f, 0, 0.1f, len);
                float w = img.width() / scale;
                float h = img.height() / scale;
                surface.drawImage(img, -w / 2, -h / 2, w, h);
            }
        });
        update(0);
    }

    @Override
    public Body getBody() {
        return koerper;
    }

    @Override
    public Layer getLayer() {
        return layer;
    }

    @Override
    public void setPos(float x, float y) {
    }
    private Joint joint;

    public void setLength(float len) {
        if (joint != null) {
            koerper.getWorld().destroyJoint(joint);
        }

        Vec2 dir = koerper.getPosition().sub(aufhaengung.getPosition());
        dir.normalize();
        Vec2 newPos = aufhaengung.getPosition().add(dir.mul(len));
        koerper.setTransform(newPos, koerper.getAngle());

        RevoluteJointDef jdef = new RevoluteJointDef();
        jdef.initialize(koerper, aufhaengung, aufhaengung.getPosition().clone());

        joint = koerper.getWorld().createJoint(jdef);
    }

    public void setDensity(float d) {
        koerper.getFixtureList().setDensity(d);
        koerper.resetMassData();
    }

    @Override
    public float getHeight() {
        return radius * 2;
    }

    @Override
    public float getWidth() {
        return radius * 2;
    }

    @Override
    public InteractionListener getListener() {
        return listener;
    }

    @Override
    public void destroy() {
        super.destroy();
        if (joint != null) {
            aufhaengung.getWorld().destroyJoint(joint);
        }
        aufhaengung.getWorld().destroyBody(aufhaengung);
    }
}
