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

import de.hofuniversity.iws.client.playn.entitys.PhysicEntity.InteractionListener;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.joints.*;
import playn.core.Pointer;

/**
 *
 * @author Daniel Heinrich <DannyNullZwo@gmail.com>
 */
public class MouseJointHandler implements InteractionListener {

    private final Body body;
    private MouseJoint mouseJoint;

    public MouseJointHandler(Body body) {
        this.body = body;
    }

    @Override
    public void clicked(Pointer.Event event) {
    }

    @Override
    public void draged(float x, float y) {
        if (mouseJoint == null) {
            MouseJointDef mdef = new MouseJointDef();
            mdef.bodyA = getFirstWorldBody();
            mdef.bodyB = body;
            mdef.collideConnected = true;
            mdef.maxForce = 300f * body.getMass();
            mdef.target.set(new Vec2(x, y));
            Joint j = body.getWorld().createJoint(mdef);
            mouseJoint = (MouseJoint) j;
            body.setAwake(true);
        }

        mouseJoint.setTarget(new Vec2(x, y));
    }

    public Body getFirstWorldBody() {
        Body last = body.getWorld().getBodyList();
        while (last.getNext() != null) {
            last = last.getNext();
        }
        return last;
    }

    @Override
    public void dragEnd() {
        if (mouseJoint != null) {
            body.getWorld().destroyJoint(mouseJoint);
            mouseJoint = null;
        }
    }
}
