/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.playn.entitys;

import de.hofuniversity.iws.client.playn.entitys.PhysicEntity.HasContactListener;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.*;
import org.jbox2d.dynamics.*;
import org.jbox2d.dynamics.joints.*;
import playn.core.*;

/**
 *
 * @author some
 */
public class Pendulum extends ImageEntity implements HasContactListener {

    private final Body body;
    private final RevoluteJoint joint;
    private final float length;
    private final float width = 0.5f;
    private final float height = 0.25f;

    public Pendulum(World world, float length, float x, float y) {
        this.length = length;
        BodyDef bdef = new BodyDef();
        bdef.type = BodyType.DYNAMIC;
        bdef.position = new Vec2(x, y - length);

        FixtureDef fdef = new FixtureDef();
        PolygonShape s = new PolygonShape();
        s.setAsBox(width, height);
        fdef.shape = s;
        fdef.density = 1;
        fdef.friction = 1;
        fdef.restitution = 0.7f;

        body = world.createBody(bdef);
        body.createFixture(fdef);
        body.setLinearDamping(0.1f);

        RevoluteJointDef jdef = new RevoluteJointDef();
        BodyDef bdef2 = new BodyDef();
        bdef.type = BodyType.STATIC;
        bdef.position = new Vec2(x, y);
        Body body2 = world.createBody(bdef2);
        jdef.initialize(body, body2, new Vec2(x, y));

        joint = (RevoluteJoint) world.createJoint(jdef);

        init();
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
    public Image getImage() {
        float w = 2.5f * 100;
        float h = 1.5f * 100;
        CanvasImage img = PlayN.graphics().createImage(w, h);
        img.canvas().clear();
        img.canvas().setFillColor(Color.rgb(0, 255, 0));
        img.canvas().fillRect(0, 0, w, h);
        img.canvas().setStrokeColor(Color.rgb(0, 120, 0));
        img.canvas().setStrokeWidth(2);
        img.canvas().strokeRect(0, 0, w, h);
        return img;
    }

    @Override
    public float getWidth() {
        return 2.5f;
    }

    @Override
    public float getHeight() {
        return 1.5f;
    }

    @Override
    public void contact(PhysicEntity other, float impulse) {
        System.out.println("Bam!");
    }
}
