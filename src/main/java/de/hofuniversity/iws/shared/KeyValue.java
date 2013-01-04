/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.shared;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class KeyValue<E, T> {

    private final E key;
    private final T value;

    public KeyValue(E key, T value) {
        this.key = key;
        this.value = value;
    }

    public E getKey() {
        return key;
    }

    public T getValue() {
        return value;
    }
}
