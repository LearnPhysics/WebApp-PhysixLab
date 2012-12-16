/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.playn;

import playn.core.GroupLayer;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public interface PhysicGame {

    public void init(GroupLayer scaledLayer);

    public float getWidth();

    public float getHeight();

    public void update(float delta);

    public void paint(float alpha);
}
