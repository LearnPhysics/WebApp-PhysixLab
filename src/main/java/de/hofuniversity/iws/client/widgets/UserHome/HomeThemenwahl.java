/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.UserHome;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.inject.Inject;
import de.hofuniversity.iws.client.PhysixLab;
import de.hofuniversity.iws.client.widgets.SubWidgets.ThemaSelector;
import de.hofuniversity.iws.client.widgets.TestEntities.*;
import de.hofuniversity.iws.client.widgets.Thema.Thema;
import de.hofuniversity.iws.shared.dto.ThemaDTO;
import de.hofuniversity.iws.shared.services.LessonService;
import de.hofuniversity.iws.shared.services.LessonServiceAsync;
import java.util.LinkedList;

/**
 *
 * @author Oliver
 */
public class HomeThemenwahl extends Composite {

    private static HomeThemenwahlUiBinder uiBinder = GWT.create(HomeThemenwahlUiBinder.class);
    @Inject
    private final LessonServiceAsync lessonService = (LessonServiceAsync) GWT.create(LessonService.class);
    private List<TestThema> themen = new LinkedList<TestThema>();
    @UiField
    VerticalPanel themaPanel;
    interface HomeThemenwahlUiBinder extends UiBinder<Widget, HomeThemenwahl> {
    }

    public HomeThemenwahl() {
        initWidget(uiBinder.createAndBindUi(this));
        
         lessonService.readThemes(new AsyncCallback() {

            @Override
            public void onFailure(Throwable caught) {
                throw new UnsupportedOperationException(caught.getLocalizedMessage());
            }

            @Override
            public void onSuccess(Object result) {
              //  throw new UnsupportedOperationException("");
                List<ThemaDTO> themenres = (List<ThemaDTO>) result;
                if (themenres != null) {
                   for (ThemaDTO thema : themenres) {
                       TestThema theme = new TestThema();
                       theme.setTitle(thema.getTitle());
                       theme.setDescription(thema.getDescription());
                       theme.setImageURL(thema.getImageURL());
                       theme.setTopic_name(thema.getTopic_name());
                       themen.add(theme);
                   }
                } 
    //    List<TestThema> themen = FakeDatabase.getInstance().getAllThemes();
        if (themen != null) {
            for (TestThema thema : themen) {
                themaPanel.add(new ThemaSelector(thema));
            }
        }
            }
        });   
 }
}
