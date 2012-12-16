/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.playn;

import de.hofuniversity.iws.client.playn.entitys.Entity;
import de.hofuniversity.iws.client.playn.entitys.PhysicEntity;
import java.util.*;

import org.jbox2d.callbacks.*;
import org.jbox2d.collision.Manifold;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import org.jbox2d.dynamics.contacts.Contact;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public abstract class StandardPhysicGame implements PhysicGame, ContactListener {

    private final World phyWorld;
    private List<Entity> entities = new ArrayList<Entity>();
    private Map<Body, PhysicEntity> bodyEntityLUT = new HashMap<Body, PhysicEntity>();
    private final Stack<Contact> contacts = new Stack<Contact>();

    protected StandardPhysicGame(boolean gravity) {
        Vec2 grav = new Vec2(0.0f, gravity ? 9.81f : 0);
        phyWorld = new World(grav, true);
        phyWorld.setWarmStarting(true);
        phyWorld.setAutoClearForces(true);
        phyWorld.setContactListener(this);
    }

    @Override
    public final void paint(float alpha) {
        for (Entity entity : entities) {
            entity.update(alpha);
        }
    }

    @Override
    public final void update(float delta) {
        for (Entity entity : entities) {
            entity.update(delta);
        }
        phyWorld.step(0.033f, 10, 10);
        processContacts();
    }

    public void add(Entity entity) {
        entities.add(entity);
        if (entity instanceof PhysicEntity) {
            PhysicEntity physicsEntity = (PhysicEntity) entity;
            bodyEntityLUT.put(physicsEntity.getBody(), physicsEntity);
        }
    }

    public void processContacts() {
        while (!contacts.isEmpty()) {
            Contact contact = contacts.pop();

            // handle collision
            PhysicEntity entityA = bodyEntityLUT.get(contact.m_fixtureA.m_body);
            PhysicEntity entityB = bodyEntityLUT.get(contact.m_fixtureB.m_body);

            if (entityA != null && entityB != null) {
                if (entityA instanceof PhysicEntity.HasContactListener) {
                    ((PhysicEntity.HasContactListener) entityA).contact(entityB);
                }
                if (entityB instanceof PhysicEntity.HasContactListener) {
                    ((PhysicEntity.HasContactListener) entityB).contact(entityA);
                }
            }
        }
    }

    public World getPhysicWorld() {
        return phyWorld;
    }

    @Override
    public void beginContact(Contact contact) {
        contacts.push(contact);
    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
    }
}
