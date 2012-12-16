/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.playn.entitys;

import playn.core.Layer;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public interface Entity {
    public void paint(float alpha);
    public void update(float delta);
    public Layer getLayer();
    public float getWidth();
    public float getHeight();
}
