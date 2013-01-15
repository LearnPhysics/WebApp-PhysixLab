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
import playn.core.GroupLayer;

import static playn.core.PlayN.graphics;

/**
 *
 * @author Daniel Heinrich <DannyNullZwo@gmail.com>
 */
public class KineticWars extends StandardPhysicGame {

    public GroupLayer staticLayerBack, dynamicLayer, staticLayerFront;
    private HammerPendulum pendel;
    private Rampe rampe;
    private Ball ball;
    private Box[] boxes;
    private int points;
    private boolean finished;

    public KineticWars() {
        super(true);
    }

    @Override
    protected void initGame(GroupLayer scaledLayer) {
        staticLayerBack = graphics().createGroupLayer();
        scaledLayer.add(staticLayerBack);
        dynamicLayer = graphics().createGroupLayer();
        scaledLayer.add(dynamicLayer);
        staticLayerFront = graphics().createGroupLayer();
        scaledLayer.add(staticLayerFront);

        // create the ground
        Body ground = getPhysicWorld().createBody(new BodyDef());
        PolygonShape groundShape = new PolygonShape();
        groundShape.setAsEdge(new Vec2(-1000, 0), new Vec2(1000, 0));
        Fixture createFixture = ground.createFixture(groundShape, 0.0f);
        createFixture.setFriction(1);

        reset();
    }

    private void reset() {
        points = 1000;
        finished = false;

        setBall();
        setPendel(2 + (float) Math.random() * 3, 7);
        setBurg();
        setRampe(15 + (float) Math.random() * 10);
    }
    private boolean wasActive;
    private final static float SLEEP_VEL = 0.1f;

    @Override
    public void update(float delta) {
        if (!finished) {
            super.update(delta);

            wasActive |= ball.getBody().getLinearVelocity().lengthSquared() > SLEEP_VEL;

            float x = ball.getBody().m_xf.position.x;
            float hw = ball.getWidth() / 2;
            if (x - hw < 0 || x + hw > getWidth() || (wasActive && ball.getBody().getLinearVelocity().lengthSquared() < SLEEP_VEL)) {
                setPendel(2 + (float) Math.random() * 3, 7);
                setBall();
                wasActive = false;
            }

            for (Box box : boxes) {
                if (!box.isMarkedForRemoval()) {
                    return;
                }
            }
            finished = true;
        }
    }

    public void setRampe(float angle) {
        if (rampe != null) {
            remove(rampe);
        }
        rampe = Rampe.build(getPhysicWorld())
                .setWidth(1.5f)
                .setAngle((float) Math.toRadians(angle))
                .create();
        rampe.setPos(5.5f, rampe.getHeight() / 2);
        add(rampe);
    }

    public void setBall() {
        changePointsBy(-100);
        if (ball != null) {
            remove(ball);
        }
        ball = Ball.build(getPhysicWorld(), getScaleFactor())
                .onPosition(2.8f + 0.25f, 0.26f)
                .create();
        add(ball);
    }

    public void setPendel(float length, float mass) {
        if (pendel != null) {
            remove(pendel);
        }
        pendel = HammerPendulum.build(getPhysicWorld(), getScaleFactor())
                .setLength(length)
                .onPosition(2.25f, length + 0.55f)
                .create();
        add(pendel);
    }

    public void setBurg() {
        if (boxes != null) {
            for (Box box : boxes) {
                remove(box);
            }
        }

        float baseOffset = 10;
        boxes = new Box[4];
        boxes[0] = Box.build(getPhysicWorld())
                .setSize(0.2f, 1.6f)
                .onPosition(baseOffset, 0.8f)
                .create();
        boxes[1] = Box.build(getPhysicWorld())
                .setSize(0.2f, 1.6f)
                .onPosition(baseOffset + 1.2f, 0.8f)
                .create();
        boxes[2] = Box.build(getPhysicWorld())
                .setSize(1.6f, 0.2f)
                .onPosition(baseOffset + 0.6f, boxes[0].getHeight() + 0.1f)
                .create();
        boxes[3] = Box.build(getPhysicWorld())
                .setSize(0.2f, 1.6f)
                .onPosition(baseOffset + 0.6f, boxes[0].getHeight() + boxes[2].getHeight() + 0.8f)
                .create();


        for (Box box : boxes) {
            add(box);
        }
    }

    @Override
    public void add(Entity entity) {
        super.add(entity);
        dynamicLayer.add(entity.getLayer());
    }

    @Override
    public float getWidth() {
        return 12;
    }

    @Override
    public float getHeight() {
        return 5;
    }

    private void changePointsBy(float delta) {
        points += delta;
        System.out.println("--" + points);
    }

    @Override
    public int getPlayerScore() {
        return points;
    }

    @Override
    public boolean isFinished() {
        return finished;
    }

    @Override
    public void restart() {
        reset();
    }
}
