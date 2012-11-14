package de.hofuniversity.iws.java;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.hofuniversity.iws.core.WebApp;
import playn.core.PlayN;
import playn.java.JavaPlatform;

public class WebAppJava {

    public static void main(String[] args) {

        Injector injector = Guice.createInjector(new WebAppModul());

        JavaPlatform platform = JavaPlatform.register();
        platform.assets().setPathPrefix("de/hofuniversity/iws/resources");
        PlayN.run(injector.getInstance(WebApp.class));
    }
}
