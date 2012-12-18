/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.UserHome;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import de.hofuniversity.iws.shared.services.UserFriendService;
import de.hofuniversity.iws.shared.services.UserFriendServiceAsync;

/**
 *
 * @author Oliver
 */
public class HomeThemenwahl extends Composite {
    
    private static HomeThemenwahlUiBinder uiBinder = GWT.create(HomeThemenwahlUiBinder.class);
    private UserFriendServiceAsync userfriend = (UserFriendServiceAsync)GWT.create(UserFriendService.class);

    
    interface HomeThemenwahlUiBinder extends UiBinder<Widget, HomeThemenwahl> {
    }
    
    public HomeThemenwahl() {
        initWidget(uiBinder.createAndBindUi(this));
        userfriend.parseThemes(new ThemeParserCallback());
    }
    
        private static class ThemeParserCallback implements AsyncCallback<String> {

        public ThemeParserCallback() {
        }

        @Override
        public void onFailure(Throwable caught) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void onSuccess(String result) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
