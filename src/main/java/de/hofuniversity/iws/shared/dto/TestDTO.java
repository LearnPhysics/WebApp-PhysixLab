/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.shared.dto;

import java.io.Serializable;

/**
 *
 * @author Andreas
 */
public class TestDTO implements Serializable {
    String title;
    String description;
    String solution;
    String illustration;
    boolean testpassed;

    public boolean isTestpassed() {
        return testpassed;
    }

    public void setTestpassed(boolean testpassed) {
        this.testpassed = testpassed;
    }

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

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getIllustration() {
        return illustration;
    }

    public void setIllustration(String illustration) {
        this.illustration = illustration;
    }
    
}
