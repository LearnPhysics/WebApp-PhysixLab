/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.core.game.entities;

import de.hofuniversity.iws.core.TestWorld;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import playn.core.*;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class Ball extends DynamicPhysicsEntity {
    private final int color;

    public Ball(TestWorld peaWorld, World world, float x, float y, int color) {
        super(peaWorld, world, x, y, 0);
        this.color = color;
    }

    @Override
    Body initPhysicsBody(World world, float x, float y, float angle) {
        FixtureDef fixtureDef = new FixtureDef();
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DYNAMIC;
        bodyDef.position = new Vec2(0, 0);
        Body body = world.createBody(bodyDef);

        CircleShape circleShape = new CircleShape();
        circleShape.m_radius = getRadius();
        fixtureDef.shape = circleShape;
        fixtureDef.density = 0.4f;
        fixtureDef.friction = 0.1f;
        fixtureDef.restitution = 0.35f;
        circleShape.m_p.set(0, 0);
        body.createFixture(fixtureDef);
        body.setLinearDamping(0.8f);
        body.setTransform(new Vec2(x, y), angle);
        return body;
    }

    @Override
    float getWidth() {
        return 2 * getRadius();
    }

    @Override
    float getHeight() {
        return 2 * getRadius();
    }

    float getRadius() {
        //return 1.50f;
        return 0.5f;
    }

    @Override
    public Image getImage() {
        CanvasImage img = PlayN.graphics().createImage(20, 20);
        img.canvas().setFillColor(color);
        img.canvas().fillCircle(10, 10, 10);
        return img;
    }
}