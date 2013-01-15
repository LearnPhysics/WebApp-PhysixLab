/*
  * Copyright (C) 2012 Daniel Heinrich
  *
  * This program is free software: you can redistribute it and/or modify
  * it under the terms of the GNU General Public License as published by
  * the Free Software Foundation, either version 3 of the License, or
  * (at your option) any later version.
  *
  * This program is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU General Public License for more details.
  *
  * You should have received a copy of the GNU General Public License
  * along with this program.  If not, see <http://www.gnu.org/licenses/>.
  */
package de.hofuniversity.iws.client.playn.entitys;

import de.hofuniversity.iws.client.playn.ImageFactory;
import de.hofuniversity.iws.client.playn.entitys.PhysicEntity.HasInteractionListener;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import playn.core.*;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class Ball extends PhysicEntity implements HasInteractionListener {

    private final Body body;
    private final float size;
    private final ImageLayer layer;
    private final InteractionListener listener;

    //<editor-fold defaultstate="collapsed" desc="Builder">
    public static class Builder extends PhysicEntity.Builder<Ball> {
        
        private float size = 0.5f;
        private final float imageScale;
        
        public Builder(float imageScaleFactor, World world) {
            super(world);
            imageScale = imageScaleFactor;
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
            
            return new Ball(body, imageScale, size);
        }
    }
    
    public static Builder build(World world, float imageScaleFactor) {
        return new Builder(imageScaleFactor, world);
    }
    //</editor-fold>

    private Ball(Body body, float scale, float size) {
        this.body = body;
        this.size = size;

        listener = new MouseJointHandler(body);

        ImageFactory fac = new ImageFactory(scale);
        layer = fac.createImageLayer(size, size);

        CanvasImage img = (CanvasImage) layer.image();
        img.canvas().clear();
        img.canvas().setFillColor(Color.rgb(255, 0, 0));
        img.canvas().fillCircle(img.width() / 2, img.height() / 2, img.width() / 2);
        
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
        return size;
    }

    @Override
    public float getHeight() {
        return size;
    }

    @Override
    public InteractionListener getListener() {
        return listener;
    }
}
