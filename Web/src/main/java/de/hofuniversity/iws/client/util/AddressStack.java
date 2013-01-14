/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.util;

import java.util.*;

import javax.inject.Singleton;

/**
 *
 * @author Oliver
 */
@Singleton
public class AddressStack {
    
    private LinkedList<CrumbTuple> list;
    int pointer;

    public AddressStack() {
        list = new LinkedList<CrumbTuple>();
        pointer = 0;
    }
    
    public void addAddress(CrumbTuple page) {
        System.out.println("--------------------------------------------------- Adding: " + page.getLabel());
        list.add(page);
        int layer = page.getLayer();
        for(int i = list.size() - 1; i >= 0; i--) {
            if(list.get(i).getLayer() > layer) {
                list.remove(i);
            }
        }
        LinkedList<CrumbTuple> copy = new LinkedList<CrumbTuple>();
        for(int i = list.size() - 1; i >= 0; i--) {
            if(list.get(i).getLayer() == layer) {
                copy.add(0, list.get(i));
                layer--;
            }
        }
        list = copy;
        printAddressStack();
    }
    
    public List<CrumbTuple> getListTillLayer(int layer) {
        return list.subList(0, layer+1);
    }
    
    public CrumbTuple getPrevious(int layer) {
        return list.get(layer - 1);
    }
    
    public void printAddressStack() {
        System.out.println("<<<<<-----### Start of Address Stack ###----->>>>>");
        for(CrumbTuple ct : list) {
            System.out.println("Label: " + ct.getLabel());
            System.out.println("---");
        }
        System.out.println("### End of Address Stack ###");
    }
}