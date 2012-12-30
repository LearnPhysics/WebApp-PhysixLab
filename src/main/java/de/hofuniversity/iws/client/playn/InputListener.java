/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.playn;

import java.util.*;
import de.hofuniversity.iws.client.playn.entitys.PhysicEntity;
import de.hofuniversity.iws.client.playn.entitys.PhysicEntity.HasInteractionListener;
import org.jbox2d.callbacks.QueryCallback;
import org.jbox2d.collision.AABB;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import playn.core.PlayN;
import playn.core.Pointer.Event;
import playn.core.Pointer.Listener;

/**
 *
 * @author some
 */
public class InputListener implements Listener {

    private static final float c = .001f;
    private final Map<Body, PhysicEntity> bodyEntityLUT;
    private final World phyWorld;
    private final PhysicGame game;
    private HasInteractionListener down;
    private boolean dragging;

    public InputListener(Map<Body, PhysicEntity> bodyEntityLUT, World phyWorld, PhysicGame game) {
        this.bodyEntityLUT = bodyEntityLUT;
        this.phyWorld = phyWorld;
        this.game = game;
    }

    @Override
    public void onPointerStart(Event event) {
        final float x = getScaledX(event.localX()),
                y = getScaledY(event.localY());

        phyWorld.queryAABB(new QueryCallback() {
            @Override
            public boolean reportFixture(Fixture fixture) {
//                        if (fixture.testPoint(new Vec2(x, y))) {
                PhysicEntity get = bodyEntityLUT.get(fixture.m_body);
                if (get instanceof HasInteractionListener) {
                    down = (HasInteractionListener) get;
                }
//                        }
                return true;
            }
        }, new AABB(new Vec2(x - c, y - c), new Vec2(x + c, y + c)));
        dragging = false;
    }

    @Override
    public void onPointerDrag(Event event) {
        dragging = down != null;
        if (dragging) {
            float drx = getScaledX(event.localX()),
                    dry = getScaledY(event.localY());
            down.getListener().draged(drx, dry);
        }
    }

    @Override
    public void onPointerEnd(Event event) {
        if (!dragging) {
            if (down != null) {
                down.getListener().clicked(event);
            }
        } else {
            down.getListener().dragEnd();
        }
        down = null;
    }

    @Override
    public void onPointerCancel(Event event) {
        if (dragging) {
            down.getListener().dragEnd();
        }
        down = null;
    }

    private float getScaledX(float x) {
        return x / PlayN.graphics().width() * game.getWidth();
    }

    private float getScaledY(float y) {
        return -y / PlayN.graphics().height() * game.getHeight() + game.getHeight();
    }
}
