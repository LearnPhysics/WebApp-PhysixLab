/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import de.hofuniversity.iws.client.services.LoginServiceAsync;
import java.util.Date;
import javax.inject.Inject;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class PhysixLabWidget extends Composite {

    @Inject
    private LoginServiceAsync loginService;
    private PhysixLabWidgetUiBinder uiBinder = GWT.create(PhysixLabWidgetUiBinder.class);

    interface PhysixLabWidgetUiBinder extends UiBinder<Widget, PhysixLabWidget> {
    }

    public PhysixLabWidget() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @UiHandler("googleLogin")
    public void loginWithGoogle(ClickEvent ev) {
        loginService.SocialNetworkLogin("Google", new PopupCallback("http://www.google.com"));
    }

    @UiHandler("twitterLogin")
    public void loginWithTwitter(ClickEvent ev) {
        loginService.SocialNetworkLogin("Twitter", new PopupCallback("http://www.twitter.com"));
    }

    @UiHandler("facebookLogin")
    public void loginWithFacebook(ClickEvent ev) {
        loginService.SocialNetworkLogin("Facebook", new PopupCallback("http://www.facebook.com"));
    }
    
    private static class PopupCallback implements AsyncCallback<String> {

        private final String url;

        public PopupCallback(String url) {
            this.url = url;
        }

        public void onFailure(Throwable caught) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void onSuccess(String result) {
                openNewWindow("Testing Window", result, url);
        }
    }

    public static void openNewWindow(String name, String url, String URI) {
        String variable_url_part = "height=500,width=700";
        if(URI.contains("facebook"))
        {
            variable_url_part = "height=510,width=860";
        }
        if(URI.contains("google"))
        {
            variable_url_part = "height=400,width=860";
        }
        com.google.gwt.user.client.Window.open(url, name.replace(" ", "_"),
                                               "menubar=no,"
                                               + "location=false,"
                                               + "resizable=no,"
                                               + "scrollbars=no,"
                                               + "status=no,"+variable_url_part);
    }
}
