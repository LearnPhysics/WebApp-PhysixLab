/*
 * Copyright (C) 2013 Daniel Heinrich <dannynullzwo@gmail.com>
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * (version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library.  If not, see <http://www.gnu.org/licenses/> 
 * or write to the Free Software Foundation, Inc., 51 Franklin Street,
 * Fifth Floor, Boston, MA 02110-1301  USA.
 */
package de.hofuniversity.iws.gwtheadless;

import java.lang.reflect.*;
import java.util.Arrays;

import com.google.common.reflect.AbstractInvocationHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class SequentialAsyncCallback<E> implements AsyncCallback<E> {

    private E res;

    @Override
    public void onFailure(Throwable caught) {
        throw new RuntimeException(caught);
    }

    @Override
    public void onSuccess(E result) {
        res = result;
    }

    public E getResult() {
        return res;
    }

    public static <E> E makeSequential(final Object async, Class<E> service) {
        return (E) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{service}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                SequentialAsyncCallback callback = new SequentialAsyncCallback();
                for (Method method1 : async.getClass().getMethods()) {
                    if (method1.getName().equals(method.getName())) {
                        if (args == null) {
                            method1.invoke(async, callback);
                        } else {
                            Object[] copyOf = Arrays.copyOf(args, args.length + 1);
                            copyOf[args.length] = callback;
                            method1.invoke(async, copyOf);
                        }
                        return callback.getResult();
                    }
                }
                throw new RuntimeException();
            }
        });
    }
}
