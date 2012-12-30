/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.TestEntities;

import com.google.gwt.user.client.ui.Widget;

/**
 *
 * @author Oliver
 */
public class TestTest {

    String title;
    String description;
    Widget illustration;
    double solution;
    boolean passed;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Widget getIllustration() {
        return illustration;
    }

    public void setIllustration(Widget illustration) {
        this.illustration = illustration;
    }

    public double getSolution() {
        return solution;
    }

    public void setSolution(double solution) {
        this.solution = solution;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }
}
