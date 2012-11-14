package de.hofuniversity.iws.html;

import com.google.gwt.core.client.GWT;
import playn.core.PlayN;
import playn.html.HtmlGame;
import playn.html.HtmlPlatform;

public class WebAppHtml extends HtmlGame {

    private final WebAppInjector injector = GWT.create(WebAppInjector.class);

    @Override
    public void start() {
        HtmlPlatform platform = HtmlPlatform.register();
        platform.assets().setPathPrefix("WebSA_WS12/");
        PlayN.run(injector.createApp());
    }
}
