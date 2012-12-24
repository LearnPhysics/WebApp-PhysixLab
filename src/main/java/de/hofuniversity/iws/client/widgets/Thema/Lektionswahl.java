/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.Thema;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import de.hofuniversity.iws.client.widgets.SubWidgets.LektionSelector;
import de.hofuniversity.iws.client.widgets.TestEntities.EntityHolder;
import de.hofuniversity.iws.client.widgets.TestEntities.TestLektion;
import de.hofuniversity.iws.client.widgets.TestEntities.TestThema;
import de.hofuniversity.iws.shared.dto.LektionDTO;
import de.hofuniversity.iws.shared.services.LessonService;
import de.hofuniversity.iws.shared.services.LessonServiceAsync;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Oliver
 */
public class Lektionswahl extends Composite {
    
    private static LektionswahlUiBinder uiBinder = GWT.create(LektionswahlUiBinder.class);
    List<TestLektion> lektionen;
    static TestThema thema;
    
    private final LessonServiceAsync lessonService = (LessonServiceAsync) GWT.create(LessonService.class);
 
    
    @UiField HTMLPanel lektionTree;

    interface LektionswahlUiBinder extends UiBinder<Widget, Lektionswahl> {
    }
    
    public Lektionswahl() {
        initWidget(uiBinder.createAndBindUi(this));
        this.thema = EntityHolder.getInstance().getThema();
        lessonService.readLessons(this.thema.getTopic_name(), new LessionAsyncCallback());
    }
    
    private void setup(List<TestLektion> l) {
        this.lektionen = l;
        System.out.println("<<< Called Setup >>>");
        
        int width = 880;
        List<TestLektion> copy = new LinkedList<TestLektion>();
        for(TestLektion lek : lektionen) {
            copy.add(lek);
        }
        
        // Make tree
        LinkedList<LinkedList<TestLektion>> tree = new LinkedList<LinkedList<TestLektion>>();    
        boolean added = true;
        while(added) {
            System.out.println("--------- Entered Added ---------");
            added = false;
            tree.add(new LinkedList<TestLektion>());
            for(int i = copy.size()-1; i >= 0; i--) {
            TestLektion lek = copy.get(i);
                if(tree.size() > 1) {
                    for(TestLektion parent : tree.get(tree.size()-2)) {
                        if(lek.getParent().equals(parent)) {
                            System.out.println("child added");
                            tree.get(tree.size()-1).add(lek);
                            copy.remove(i);
                            added = true;
                        }
                    }
                }
                else {
                    
                    if(lek.getParent() == null) {
                        System.out.println("Root added");
                        tree.get(tree.size()-1).add(lek);
                        copy.remove(i);
                        added = true;
                    }
                }
            }
        }
        
        // Draw tree
        for(int y = 0; y < tree.size()-1; y++) {
            System.out.println("TreeSize: " + tree.size());
            LinkedList<TestLektion> layer = tree.get(y);
            int unit = width/layer.size();
            System.out.println(unit);
            for(int x = 0; x < layer.size(); x++) {
                System.out.println("Size Layer " + y +": " + layer.size());
                System.out.println(layer.get(x).getTitle() + " drawn at " + ((unit/2)+(x*unit)-40) + ":" + y*160);
                lektionTree.add(new LektionSelector(layer.get(x), (unit/2)+(x*unit)-40, y*160));
            }
        }
        
        System.out.println("<<< Left Setup >>>");
    }
    
        private class LessionAsyncCallback implements AsyncCallback<List> {
        @Override
        public void onFailure(Throwable caught) {
            throw new UnsupportedOperationException(caught.getMessage());
        }

        @Override
        public void onSuccess(List result) {
         //   throw new UnsupportedOperationException("Not supported yet.");
            Map<String, TestLektion> map = new HashMap<String,TestLektion>();
            List<LektionDTO> lektion = (LinkedList<LektionDTO>) result;  
            TestThema thema = EntityHolder.getInstance().getThema();
            for (LektionDTO x : lektion)
            {
                TestLektion lesson = new TestLektion(); 
                lesson.setLessonId(x.getId());
                lesson.setLesson_name(x.getLesson_name());
                lesson.setTitle(x.getTitle());
                lesson.setPreviewURL(x.getPreviewURL());
                lesson.setLessonText(x.getLessonText());
                lesson.setParent_id(x.getParent());
                lesson.setExperiment_id(x.getWidget_id());
                lesson.setFormular_id(x.getFormular_id());
                
                //lesson.setExperiment(this);
                //lesson.setFormular(this);
                
                map.put(x.getId(), lesson);
                thema.getLektionen().add(lesson);
            }
           EntityHolder.getInstance().setThema(thema);           
           lektionen = EntityHolder.getInstance().getThema().getLektionen();
           /*
            * zuordnen der Elternlektionen 
            */
              for(TestLektion x : lektionen)
              {              
                 TestLektion t = (TestLektion) map.get(x.getParent_id());
                 if(t!=null)
                 {
                    System.out.println(x.getParent_id()+"   "+t.getLessonId());
                    x.setParent(t);
                 }
              }
           if(lektionen != null) {
              setup(lektionen);
           }
        }
    }
}
