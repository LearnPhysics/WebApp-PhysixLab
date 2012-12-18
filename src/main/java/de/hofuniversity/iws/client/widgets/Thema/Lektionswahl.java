/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.Thema;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import de.hofuniversity.iws.client.widgets.SubWidgets.LektionSelector;
import de.hofuniversity.iws.client.widgets.TestEntities.EntityHolder;
import de.hofuniversity.iws.client.widgets.TestEntities.TestLektion;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Oliver
 */
public class Lektionswahl extends Composite {
    
    private static LektionswahlUiBinder uiBinder = GWT.create(LektionswahlUiBinder.class);
    List<TestLektion> lektionen;
    
    @UiField HTMLPanel lektionTree;
    
    interface LektionswahlUiBinder extends UiBinder<Widget, Lektionswahl> {
    }
    
    public Lektionswahl() {
        initWidget(uiBinder.createAndBindUi(this));
        this.lektionen = EntityHolder.getInstance().getThema().getLektionen();
        if(lektionen != null) {
            setup();
        }
    }
    
    private void setup() {
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
}
