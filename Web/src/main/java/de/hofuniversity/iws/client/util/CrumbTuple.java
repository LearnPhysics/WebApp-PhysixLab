/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.util;

import com.google.gwt.user.client.ui.Composite;

/**
 *
 * @author Oliver
 */
public class CrumbTuple {

    private String label;
    private Composite page;
    private int layer;

    public CrumbTuple(Composite page, String label, int layer) {
        this.label = label;
        this.page = page;
        this.layer = layer;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Composite getPage() {
        return page;
    }

    public void setPage(Composite page) {
        this.page = page;
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }
}