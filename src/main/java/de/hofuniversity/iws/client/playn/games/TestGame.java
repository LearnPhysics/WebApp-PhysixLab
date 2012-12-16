/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.playn.games;

import de.hofuniversity.iws.client.playn.StandardPhysicGame;
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

    private static final float width = 100;
    private static final float height = 50;
    public GroupLayer staticLayerBack;
    public GroupLayer dynamicLayer;
    public GroupLayer staticLayerFront;

    public TestGame() {
        super(true);
    }

    @Override
    public void init(GroupLayer scaledLayer) {
        staticLayerBack = graphics().createGroupLayer();
        scaledLayer.add(staticLayerBack);
        dynamicLayer = graphics().createGroupLayer();
        scaledLayer.add(dynamicLayer);
        staticLayerFront = graphics().createGroupLayer();
        scaledLayer.add(staticLayerFront);

        // create the ground
        Body ground = getPhysicWorld().createBody(new BodyDef());
        PolygonShape groundShape = new PolygonShape();
        groundShape.setAsEdge(new Vec2(0, height), new Vec2(width, height));
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
        
        Ball b = new Ball(getPhysicWorld(), 50, 25);
        b.setLinearVelocity(10, 0);
        add(b);
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
