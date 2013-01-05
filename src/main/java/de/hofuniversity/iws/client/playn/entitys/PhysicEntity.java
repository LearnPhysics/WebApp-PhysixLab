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

import org.jbox2d.dynamics.*;
import playn.core.Pointer;

public abstract class PhysicEntity implements Entity {

    public abstract Body getBody();

    @Override
    public void destroy() {
        Body b = getBody();
        if (b != null) {
            b.getWorld().destroyBody(getBody());
        }
    }

    @Override
    public boolean isMarkedForRemoval() {
        return false;
    }

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
}