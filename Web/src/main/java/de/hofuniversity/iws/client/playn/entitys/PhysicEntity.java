/**
 * Copyright 2011 The PlayN Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package de.hofuniversity.iws.client.playn.entitys;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import playn.core.*;

public abstract class PhysicEntity implements Entity {

    public abstract Body getBody();

    //<editor-fold defaultstate="collapsed" desc="Builder">
    public static abstract class Builder<T extends PhysicEntity> {

        protected final World world;
        protected float x, y;

        public Builder(World world) {
            this.world = world;
        }

        public Builder<T> onPosition(float x, float y) {
            this.x = x;
            this.y = y;
            return this;
        }

        public abstract T create();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="listener">
    public interface HasContactListener {

        public void contact(PhysicEntity other, float impulse);
    }

    public interface HasInteractionListener {

        public InteractionListener getListener();
    }

    public interface InteractionListener {

        public void clicked(Pointer.Event event);

        public void draged(float x, float y);

        public void dragEnd();
    }
    //</editor-fold>
    
    private float prevX, prevY, prevA;

    @Override
    public void destroy() {
        Body b = getBody();
        if (b != null) {
            b.getWorld().destroyBody(getBody());
        }
        if (getLayer() != null) {
            GroupLayer parent = getLayer().parent();
            if (parent != null) {
                parent.remove(getLayer());
            }
        }
    }

    @Override
    public boolean isMarkedForRemoval() {
        return false;
    }

    @Override
    public final void paint(float alpha) {
        float x = (getBody().getPosition().x * alpha) + (prevX * (1f - alpha));
        float y = (getBody().getPosition().y * alpha) + (prevY * (1f - alpha));
        float a = (getBody().getAngle() * alpha) + (prevA * (1f - alpha));
        getLayer().setTranslation(x, y);
        getLayer().setRotation(a);
    }

    @Override
    public final void update(float delta) {
        prevX = getBody().getPosition().x;
        prevY = getBody().getPosition().y;
        prevA = getBody().getAngle();
    }

    public void setLinearVelocity(float x, float y) {
        getBody().setLinearVelocity(new Vec2(x, y));
    }

    public void setAngularVelocity(float w) {
        getBody().setAngularVelocity(w);
    }

    public void setPos(float x, float y) {
        getBody().setTransform(new Vec2(x, y), getBody().getAngle());
        prevX = x;
        prevY = y;
    }

    public void setAngle(float a) {
        getBody().setTransform(getBody().getPosition(), a);
        prevA = a;
    }
}