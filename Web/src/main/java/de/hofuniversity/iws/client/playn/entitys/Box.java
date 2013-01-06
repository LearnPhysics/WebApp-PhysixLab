/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.playn.entitys;

import de.hofuniversity.iws.client.playn.entitys.PhysicEntity.HasContactListener;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import playn.core.ImmediateLayer.Renderer;
import playn.core.*;

/**
 *
 * @author some
 */
public class Box extends PhysicEntity implements HasContactListener {

    private final Body body;
    private final float width, height;
    private boolean remove = false;
    private final static float MAX_LIFE = 3;
    private float life = MAX_LIFE;
    private final Layer layer;

    //<editor-fold defaultstate="collapsed" desc="Builder">
    public static class Builder extends PhysicEntity.Builder<Box> {
        
        private float width = 1f;
        private float height = 1f;
        
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
        
        @Override
        public Box create() {
            BodyDef bodyDef = new BodyDef();
            bodyDef.type = BodyType.DYNAMIC;
            bodyDef.position = new Vec2(x, y);
            
            PolygonShape shape = new PolygonShape();
            shape.setAsBox(width / 2, height / 2);
            
            FixtureDef fixtureDef = new FixtureDef();
            fixtureDef.shape = shape;
            fixtureDef.density = 5f;
            fixtureDef.friction = 0.9f;
            fixtureDef.restitution = 0.35f;
            
            Body body = world.createBody(bodyDef);
            body.createFixture(fixtureDef);
            body.setLinearDamping(0.2f);
            
            return new Box(body, width, height);
        }
    }
    
    public static Builder build(World world) {
        return new Builder(world);
    }
    //</editor-fold>

    private Box(Body body, final float width, final float height) {
        this.width = width;
        this.height = height;
        this.body = body;

        layer = PlayN.graphics().createImmediateLayer(new Renderer() {
            @Override
            public void render(Surface surface) {
                int color = Color.rgb(0, 255, (int) (255 * life / MAX_LIFE));
                surface.clear();
                surface.setFillColor(color);
                surface.fillRect(0, 0, width, height);
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

    @Override
    public void contact(PhysicEntity other, float impulse) {
        if (!(other instanceof Box)) {
            life -= impulse;
            if (life < 0) {
                remove = true;
            }
        }
    }

    @Override
    public boolean isMarkedForRemoval() {
        return remove;
    }
}
