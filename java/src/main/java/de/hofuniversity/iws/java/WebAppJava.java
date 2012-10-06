package de.hofuniversity.iws.java;

import playn.core.PlayN;
import playn.java.JavaPlatform;

import de.hofuniversity.iws.core.WebApp;

public class WebAppJava {

  public static void main(String[] args) {
    JavaPlatform platform = JavaPlatform.register();
    platform.assets().setPathPrefix("de/hofuniversity/iws/resources");
    PlayN.run(new WebApp());
  }
}
