/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package playn.html;

import com.google.gwt.core.client.JavaScriptObject;
import playn.core.*;

/**
 *
 * @author Daniel Heinrich <DannyNullZwo@gmail.com>
 */
public class StopableHtmlPlatform extends HtmlPlatform {

    private static final float MAX_DELTA = 100;

    /**
     * Prepares the HTML platform for operation.
     */
    public static StopableHtmlPlatform register() {
        return register(new Configuration());
    }

    /**
     * Prepares the HTML platform for operation.
     *
     * @deprecated use register(Configuration) instead.
     *
     * @param mode indicates whether to force the use of WebGL, force the use of
     * Canvas, or to autodetect whether the browser supports WebGL and use it if
     * possible.
     */
    @Deprecated
    public static StopableHtmlPlatform register(Mode mode) {
        Configuration configuration = new Configuration();
        configuration.mode = mode;
        StopableHtmlPlatform platform = new StopableHtmlPlatform(configuration);
        PlayN.setPlatform(platform);
        platform.init();
        return platform;
    }

    /**
     * Prepares the HTML platform for operation.
     *
     * @param configuration platform-specific settings.
     */
    public static StopableHtmlPlatform register(Configuration configuration) {
        StopableHtmlPlatform platform = new StopableHtmlPlatform(configuration);
        PlayN.setPlatform(platform);
        platform.init();
        return platform;
    }
    private TimerCallback paintCallback;
    private boolean stop;

    protected StopableHtmlPlatform(Configuration configuration) {
        super(configuration);
    }

    public void stop() {
        stop = true;
    }

    public boolean shouldStop() {
        return stop;
    }

    @Override
    public void run(final Game game) {
        final int updateRate = game.updateRate();

        game.init();

        // Game loop.
        paintCallback = new TimerCallback() {
            private float accum = updateRate;
            private double lastTime;

            @Override
            public void fire() {
                if (!shouldStop()) {
                    requestAnimationFrame(paintCallback);
                }

                // process pending actions
                runQueue.execute();

                double now = time();
                float delta = (float) (now - lastTime);
                if (delta > MAX_DELTA) {
                    delta = MAX_DELTA;
                }
                lastTime = now;

                if (updateRate == 0) {
                    game.update(delta);
                    accum = 0;
                } else {
                    accum += delta;
                    while (accum > updateRate) {
                        game.update(updateRate);
                        accum -= updateRate;
                    }
                }

                graphics().paint(game, updateRate == 0 ? 0 : accum / updateRate);
            }
        };
        requestAnimationFrame(paintCallback);
    }

    private native JavaScriptObject getWindow() /*-{
     return $wnd;
     }-*/;

    private native void requestAnimationFrame(TimerCallback callback) /*-{
     var fn = function() {
     callback.@playn.html.TimerCallback::fire()();
     };
     if ($wnd.requestAnimationFrame) {
     $wnd.requestAnimationFrame(fn);
     } else if ($wnd.mozRequestAnimationFrame) {
     $wnd.mozRequestAnimationFrame(fn);
     } else if ($wnd.webkitRequestAnimationFrame) {
     $wnd.webkitRequestAnimationFrame(fn);
     } else {
     // 20ms => 50fps
     $wnd.setTimeout(fn, 20);
     }
     }-*/;

    private native int setInterval(TimerCallback callback, int ms) /*-{
     return $wnd.setInterval(function() {
     callback.@playn.html.TimerCallback::fire()();
     }, ms);
     }-*/;

    private native int setTimeout(TimerCallback callback, int ms) /*-{
     return $wnd.setTimeout(function() {
     callback.@playn.html.TimerCallback::fire()();
     }, ms);
     }-*/;

    private static native playn.html.HtmlPlatform.AgentInfo computeAgentInfo() /*-{
     var userAgent = navigator.userAgent.toLowerCase();
     return {
     // browser type flags
     isFirefox: userAgent.indexOf("firefox") != -1,
     isChrome: userAgent.indexOf("chrome") != -1,
     isSafari: userAgent.indexOf("safari") != -1,
     isOpera: userAgent.indexOf("opera") != -1,
     isIE: userAgent.indexOf("msie") != -1,
     // OS type flags
     isMacOS: userAgent.indexOf("mac") != -1,
     isLinux: userAgent.indexOf("linux") != -1,
     isWindows: userAgent.indexOf("win") != -1
     };
     }-*/;

    /**
     * Return true if the browser supports WebGL
     *
     * Note: This test can have false positives depending on the graphics
     * hardware.
     *
     * @return true if the browser supports WebGL
     */
    private static native boolean hasGLSupport() /*-{
     return !!$wnd.WebGLRenderingContext &&
     // WebGL is slow on Chrome OSX 10.5
     (!/Chrome/.test(navigator.userAgent) || !/OS X 10_5/.test(navigator.userAgent));
     }-*/;

    private static native boolean hasTypedArraySupport() /*-{
     return typeof(Float32Array) != 'undefined';
     }-*/;

    private static native void disableRightClickImpl(JavaScriptObject target) /*-{
     target.oncontextmenu = function() {
     return false;
     };
     }-*/;
}
