/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.util;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public abstract class IgnoreCallback implements AsyncCallback<Void> {

    protected abstract void action();

    @Override
    public void onFailure(Throwable caught) {
        action();
    }

    @Override
    public void onSuccess(Void result) {
        action();
    }
}
