/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.core.services;

import com.google.gwt.user.client.rpc.IsSerializable;
import pythagoras.f.Vector;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class RPCVector implements IsSerializable {

    private float x, y;

    public RPCVector() {
    }

    public RPCVector(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public RPCVector(Vector v) {
        x = v.x;
        y = v.y;
    }

    public Vector getVector() {
        return new Vector(x, y);
    }
}
