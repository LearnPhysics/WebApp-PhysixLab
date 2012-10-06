package de.hofuniversity.iws.android;

import playn.android.GameActivity;
import playn.core.PlayN;

import de.hofuniversity.iws.core.WebApp;

public class WebAppActivity extends GameActivity {

  @Override
  public void main(){
    platform().assets().setPathPrefix("de/hofuniversity/iws/resources");
    PlayN.run(new WebApp());
  }
}
