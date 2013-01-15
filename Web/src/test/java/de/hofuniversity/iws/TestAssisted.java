/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws;

import com.google.inject.assistedinject.Assisted;
import javax.inject.*;

/**
 *
 * @author Daniel Heinrich <DannyNullZwo@gmail.com>
 */
public class TestAssisted {

    public interface TestAssistedFactory {

        public TestAssisted create(String name);
    }

    @Inject
    public TestAssisted(@Named("Test") String other, @Assisted String name) {
        System.out.println(other + name);
    }
}
