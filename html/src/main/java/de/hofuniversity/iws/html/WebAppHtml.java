package de.hofuniversity.iws.html;

import playn.core.PlayN;
import playn.html.HtmlGame;
import playn.html.HtmlPlatform;

import de.hofuniversity.iws.core.WebApp;

public class WebAppHtml extends HtmlGame {

  @Override
  public void start() {
    HtmlPlatform platform = HtmlPlatform.register();
    platform.assets().setPathPrefix("WebSA_WS12/");
    PlayN.run(new WebApp());
  }
}
