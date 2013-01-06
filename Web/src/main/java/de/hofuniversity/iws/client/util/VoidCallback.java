/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.util;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author some
 */
public class VoidCallback implements AsyncCallback<Void> {

    @Override
    public void onFailure(Throwable caught) {
    }

    @Override
    public void onSuccess(Void result) {
    }
}