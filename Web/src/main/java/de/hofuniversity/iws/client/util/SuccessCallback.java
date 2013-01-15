/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.util;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author Daniel Heinrich <DannyNullZwo@gmail.com>
 */
public abstract class SuccessCallback<T> implements AsyncCallback<T> {

    @Override
    public void onFailure(Throwable caught) {
    }
}
