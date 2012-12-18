/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.playn.games;

import de.hofuniversity.iws.client.playn.*;
import de.hofuniversity.iws.client.playn.entitys.*;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import playn.core.*;

import static playn.core.PlayN.*;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class TestGame extends StandardPhysicGame {

    private static final float width = 10;
    private static final float height = 5;
    public GroupLayer staticLayerBack;
    public GroupLayer dynamicLayer;
    public GroupLayer staticLayerFront;

    public TestGame() {
        super(true);
    }

    @Override
    public void init(GroupLayer scaledLayer) {
        super.init(scaledLayer);
        staticLayerBack = graphics().createGroupLayer();
        scaledLayer.add(staticLayerBack);
        dynamicLayer = graphics().createGroupLayer();
        scaledLayer.add(dynamicLayer);
        staticLayerFront = graphics().createGroupLayer();
        scaledLayer.add(staticLayerFront);

        // create the ground
        Body ground = getPhysicWorld().createBody(new BodyDef());
        PolygonShape groundShape = new PolygonShape();
        groundShape.setAsEdge(new Vec2(0, 0), new Vec2(width, 0));
        ground.createFixture(groundShape, 0.0f);

        // create the walls
        Body wallLeft = getPhysicWorld().createBody(new BodyDef());
        PolygonShape wallLeftShape = new PolygonShape();
        wallLeftShape.setAsEdge(new Vec2(0, 0), new Vec2(0, height));
        wallLeft.createFixture(wallLeftShape, 0.0f);
        Body wallRight = getPhysicWorld().createBody(new BodyDef());
        PolygonShape wallRightShape = new PolygonShape();
        wallRightShape.setAsEdge(new Vec2(width, 0), new Vec2(width, height));
        wallRight.createFixture(wallRightShape, 0.0f);

        Ball b = new Ball(getPhysicWorld());
        b.setPos(2, 5);
        b.setLinearVelocity(3, 0);
        add(b);

        Box b1 = new Box(getPhysicWorld(), 0.3f, 2);
        b1.setPos(5.5f, 1f);
        Box b2 = new Box(getPhysicWorld(), 0.3f, 2);
        b2.setPos(7f, 1f);
        Box b3 = new Box(getPhysicWorld(), 2f, 0.3f);
        b3.setPos(6.25f, 2.15f);

        add(b1);
        add(b2);
        add(b3);

        Pendulum p = new Pendulum(getPhysicWorld(), 3, 4, 5);
        p.setLinearVelocity(-5, 0);
    }

    @Override
    public void add(Entity entity) {
        super.add(entity);
        dynamicLayer.add(entity.getLayer());
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }
}
