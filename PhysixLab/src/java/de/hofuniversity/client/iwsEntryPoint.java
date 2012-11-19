/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Main entry point.
 *
 * @author User
 */
public class iwsEntryPoint implements EntryPoint {
    Button btnTwitter = new Button("Twitter Login");
    Button btnFacebook = new Button("Facebook Login");
    Button btnGoogle = new Button("Google Login");
    Label lbl = new Label("");
    
    /**
* Opens a new windows with a specified URL..
* 
* @param name String with the name of the window.
* @param url String with your URL.
*/
    public static void openNewWindow(String name, String url) {
        com.google.gwt.user.client.Window.open(url, name.replace(" ", "_"),
               "menubar=no," + 
               "location=false," + 
               "resizable=yes," + 
               "scrollbars=yes," + 
               "status=no," + 
               "dependent=true, height=300, width=600");
    }

    public iwsEntryPoint() {
    }

    /**
     * The entry point method, called automatically by loading a module that
     * declares an implementing class as an entry-point
     */
    @Override
    public void onModuleLoad() {
   
            // Create an asynchronous callback to handle the result.
        final AsyncCallback<String> TwitterCallback = new AsyncCallback<String>() {
            public void onSuccess(String result) {
               String winUrl = "http://www.twitter.de";
               String winName = "Testing Window";
               openNewWindow (winName, winUrl); 
            }
            
            public void onFailure(Throwable caught) {
            }
        };
        
                    // Create an asynchronous callback to handle the result.
        final AsyncCallback<String> FacebookCallback = new AsyncCallback<String>() {
            public void onSuccess(String result) {
               String winUrl = "http://www.facebook.de";
               String winName = "Testing Window";
               openNewWindow (winName, winUrl); 
            }
            
            public void onFailure(Throwable caught) {
            }
        };
        
                    // Create an asynchronous callback to handle the result.
        final AsyncCallback<String> GoogleCallback = new AsyncCallback<String>() {
            public void onSuccess(String result) {
               String winUrl = "http://www.twitter.de";
               String winName = "Testing Window";
               openNewWindow (winName, winUrl); 
            }
            
            public void onFailure(Throwable caught) {
            }
        };
    
        // Listen for the button clicks
        btnTwitter.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                getService().SocialNetworkLogin("T", TwitterCallback);
            }
        });    
        btnFacebook.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                getService().SocialNetworkLogin("F", FacebookCallback);
               String winUrl = "http://www.facebook.de";
               String winName = "Testing Window";
                openNewWindow (winName, winUrl);             }
        });    
        btnGoogle.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                getService().SocialNetworkLogin("G", GoogleCallback);
               String winUrl = "http://www.google.de";
               String winName = "Testing Window";
                openNewWindow (winName, winUrl);             }
        });    

        RootPanel.get().add(btnTwitter);
        RootPanel.get().add(btnFacebook);
        RootPanel.get().add(btnGoogle);
        RootPanel.get().add(lbl);        
   }
        
    public static PhysixLabRPCAsync getService() {
        // Create the client proxy. Note that although you are creating the
        // service interface proper, you cast the result to the asynchronous
        // version of the interface. The cast is always safe because the
        // generated proxy implements the asynchronous interface automatically.

        return GWT.create(PhysixLabRPC.class);
    }
}
