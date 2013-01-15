/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
