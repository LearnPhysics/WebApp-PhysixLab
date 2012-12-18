/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.playn.entitys;

import de.hofuniversity.iws.client.playn.entitys.PhysicEntity.HasContactListener;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import playn.core.*;

/**
 *
 * @author some
 */
public class Box extends ImageEntity implements HasContactListener{

    private final Body body;
    private final float width, height;

    public Box(World world, float width, float height) {
        this.width = width;
        this.height = height;
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DYNAMIC;
        bodyDef.position = new Vec2(0, 0);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2, height / 2);
        
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.1f;
        fixtureDef.friction = 0.1f;
        fixtureDef.restitution = 0.35f;
        
        body = world.createBody(bodyDef);
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
        float w= width*100;
        float h= height*100;
        CanvasImage img = PlayN.graphics().createImage(w, h);
        img.canvas().clear();
        img.canvas().setFillColor(Color.rgb(0, 0, 255));
        img.canvas().fillRect(0, 0, w, h);
        img.canvas().setStrokeColor(Color.rgb(0, 120, 0));
        img.canvas().setStrokeWidth(2);
        img.canvas().strokeRect(0, 0, w, h);
        return img;
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
        System.out.println(impulse);
    }
}
