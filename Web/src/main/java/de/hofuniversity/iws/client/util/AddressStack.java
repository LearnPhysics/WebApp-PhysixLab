/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.util;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Oliver
 */
public class AddressStack {
    private static AddressStack as = null;
    
    private LinkedList<CrumbTuple> list;
    int pointer;

    private AddressStack() {
        list = new LinkedList<CrumbTuple>();
        pointer = 0;
    }

    public static AddressStack getInstance() {
        if (as == null) {
            as = new AddressStack();
        }
        return as;
    }
    
    public void addAddress(CrumbTuple page) {
        System.out.println("--------------------------------------------------- Adding: " + page.getLabel());
        list.add(page);
        int layer = page.getLayer();
        boolean found = false;
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