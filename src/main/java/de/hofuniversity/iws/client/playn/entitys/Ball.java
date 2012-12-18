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

    public Ball(World world) {
        FixtureDef fixtureDef = new FixtureDef();
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DYNAMIC;
        bodyDef.position = new Vec2(0, 0);
        body = world.createBody(bodyDef);

        CircleShape circleShape = new CircleShape();
        circleShape.m_radius = getWidth()/2;
        fixtureDef.shape = circleShape;
        fixtureDef.density = 2.4f;
        fixtureDef.friction = 0.1f;
        fixtureDef.restitution = 0.35f;
        circleShape.m_p.set(0, 0);
        body.createFixture(fixtureDef);
        body.setLinearDamping(0.2f);
        
        init();
    }

    @Override
    public Body getBody() {
        return body;
    }

    @Override
    public Image getImage() {
        CanvasImage img = PlayN.graphics().createImage(100, 100);
        img.canvas().clear();
        img.canvas().setFillColor(Color.rgb(255, 0, 0));
        img.canvas().fillCircle(50, 50, 50);
        return img;
    }

    @Override
    public float getWidth() {
        return 0.5f;
    }

    @Override
    public float getHeight() {
        return 0.5f;
    }
}
