/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.playn;

import java.util.*;

import de.hofuniversity.iws.client.playn.entitys.*;
import org.jbox2d.callbacks.*;
import org.jbox2d.collision.*;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import org.jbox2d.dynamics.contacts.Contact;
import playn.core.*;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public abstract class StandardPhysicGame implements PhysicGame, ContactListener {

    private final static boolean DEBUG_DRAW = false;
    private final World phyWorld;
    private List<Entity> entities = new ArrayList<Entity>();
    private Map<Body, PhysicEntity> bodyEntityLUT = new HashMap<Body, PhysicEntity>();
    private final Stack<Contact> contacts = new Stack<Contact>();
    private CanvasImage img;

    /**
     * @param gravity if the physic simulation should use gravity
     */
    protected StandardPhysicGame(boolean gravity) {
        Vec2 grav = new Vec2(0.0f, gravity ? -9.81f : 0);
        phyWorld = new World(grav, true);
        phyWorld.setWarmStarting(true);
        phyWorld.setAutoClearForces(true);
        phyWorld.setContactListener(this);
    }

    @Override
    public final void init(GroupLayer scaledLayer) {
        PlayN.pointer().setListener(new InputListener(bodyEntityLUT, phyWorld, this));

        //Debug stuff
        if (DEBUG_DRAW) {
            img = PlayN.graphics().createImage(800, 400);
            ImageLayer layer = PlayN.graphics().createImageLayer(img);
            layer.setScale(getWidth() / 800);
            scaledLayer.add(layer);

            DebugDrawBox2D dd = new DebugDrawBox2D();
            dd.setCamera(0, 0, 800 / getWidth());
            dd.setCanvas(img);
            dd.setFlipY(false);
            dd.setStrokeAlpha(150);
            dd.setFillAlpha(75);
            dd.setStrokeWidth(2);
            dd.setFlags(DebugDraw.e_shapeBit | DebugDraw.e_jointBit | DebugDraw.e_aabbBit);
            phyWorld.setDebugDraw(dd);
        }

        initGame(scaledLayer);
    }

    protected abstract void initGame(GroupLayer scaledLayer);

    @Override
    public final void paint(float alpha) {
        for (Entity entity : entities) {
            entity.paint(alpha);
        }
        if (DEBUG_DRAW) {
            img.canvas().clear();
            phyWorld.drawDebugData();
        }
    }
    private final float TIME_STEP = 0.020f;
    private float time = 0;

    @Override
    public void update(float delta) {
        List<Entity> toRemove = new ArrayList<Entity>();
        for (Entity entity : entities) {
            entity.update(delta);
            if (entity.isMarkedForRemoval()) {
                toRemove.add(entity);
            }
        }

        for (Entity entity : toRemove) {
            remove(entity);
        }
        time += delta;
        while (time / 1000 > TIME_STEP) {
            phyWorld.step(TIME_STEP, 10, 10);
            phyWorld.clearForces();
            time -= TIME_STEP * 1000;
        }
        processContacts();
    }

    public void add(Entity entity) {
        entities.add(entity);
        if (entity instanceof PhysicEntity) {
            PhysicEntity physicsEntity = (PhysicEntity) entity;
            bodyEntityLUT.put(physicsEntity.getBody(), physicsEntity);
        }
    }

    public void remove(Entity entity) {
        entities.remove(entity);
        bodyEntityLUT.remove(entity);
        entity.destroy();
    }

    public World getPhysicWorld() {
        return phyWorld;
    }

    public float getScaleFactor() {
        return PlayN.graphics().width() / getWidth();
    }

    public void processContacts() {
        while (!contacts.isEmpty()) {
            Contact contact = contacts.pop();

            // handle collision
            PhysicEntity entityA = bodyEntityLUT.get(contact.m_fixtureA.m_body);
            PhysicEntity entityB = bodyEntityLUT.get(contact.m_fixtureB.m_body);


            WorldManifold worldMani = new WorldManifold();
            contact.getWorldManifold(worldMani);

            float impulse = 0;
            if (entityA != null) {
                Vec2 va = entityA.getBody().getLinearVelocity();
                impulse += Math.abs(Vec2.dot(va, worldMani.normal) * entityA.getBody().getMass());
            }
            if (entityB != null) {
                Vec2 vb = entityB.getBody().getLinearVelocity();
                impulse += Math.abs(Vec2.dot(vb, worldMani.normal) * entityB.getBody().getMass());
            }
            if (impulse > 0.1) {
                System.out.println(impulse);
            }
//            float approachVelocity = Vec2.dot(vb.sub(va), worldMani.normal);

            if (entityA instanceof PhysicEntity.HasContactListener) {
                ((PhysicEntity.HasContactListener) entityA).contact(entityB, impulse);
            }
            if (entityB instanceof PhysicEntity.HasContactListener) {
                ((PhysicEntity.HasContactListener) entityB).contact(entityA, impulse);
            }
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Contact Solver">
    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
    }

    @Override
    public void beginContact(Contact contact) {
    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        contacts.push(contact);
    }
    //</editor-fold>
}
