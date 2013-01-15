/*
  * Copyright (C) 2012 Daniel Heinrich
  *
  * This program is free software: you can redistribute it and/or modify
  * it under the terms of the GNU General Public License as published by
  * the Free Software Foundation, either version 3 of the License, or
  * (at your option) any later version.
  *
  * This program is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU General Public License for more details.
  *
  * You should have received a copy of the GNU General Public License
  * along with this program.  If not, see <http://www.gnu.org/licenses/>.
  */
package de.hofuniversity.iws.shared;

import java.util.*;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class CollectionUtils {

    public interface Selector<E, A> {

        public A select(E e) throws Exception;
    }

    public static <K, S> Map<K, List<S>> groupBy(Iterable<S> c, Selector<S, K> s) {
        Map<K, List<S>> m = new HashMap<K, List<S>>();
        for (S s1 : c) {
            K key;
            try {
                key = s.select(s1);
            } catch (Exception ex) {
                continue;
            }
            List<S> get = m.get(key);
            if (get == null) {
                get = new ArrayList<S>();
                m.put(key, get);
            }
            get.add(s1);
        }

        return m;
    }

    public static <K, S> Map<K, List<S>> groupBy(S[] c, Selector<S, K> s) {
        return groupBy(asIterable(c), s);
    }

    public static <E, A> List<A> select(Iterable<E> c, Selector<E, A> s) {
        List<A> a = new ArrayList<A>();
        for (E e : c) {
            try {
                a.add(s.select(e));
            } catch (Exception ex) {
                continue;
            }
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
                        if (!hasNext()) {
                            throw new NoSuchElementException();
                        }
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
