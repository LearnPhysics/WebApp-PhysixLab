/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.shared;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

import com.google.common.io.Files;
import org.json.*;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class CollectionUtils {

    public interface Selector<E, A> {

        public A select(E e);
    }

    public static <K, S> Map<K, List<S>> groupBy(Iterable<S> c, Selector<S, K> s) {
        Map<K, List<S>> m = new HashMap<K, List<S>>();
        for (S s1 : c) {
            K key = s.select(s1);
            List<S> get = m.get(key);
            if (get == null) {
                get = new ArrayList<S>();
                m.put(key, get);
            }
            get.add(s1);
        }

        return m;
    }

    public static <E, A> List<A> select(Iterable<E> c, Selector<E, A> s) {
        List<A> a = new ArrayList<A>();
        for (E e : c) {
            a.add(s.select(e));
        }
        return a;
    }

    public static int max(Iterable<Integer> c) {
        int a = Integer.MIN_VALUE;
        for (Integer integer : c) {
            a = Math.max(a, integer);
        }
        return a;
    }

    public static <T> Iterable<T> asIterable(final T[] a) {
        return new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                return new Iterator<T>() {
                    int index = 0;

                    @Override
                    public boolean hasNext() {
                        return index < a.length;
                    }

                    @Override
                    public T next() {
                        return a[index++];
                    }

                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }
                };
            }
        };
    }
}
