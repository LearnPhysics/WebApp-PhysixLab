/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.Thema;

import java.util.*;
import java.util.Map.Entry;

import de.hofuniversity.iws.client.jsonbeans.SubjectJson;
import de.hofuniversity.iws.client.widgets.SubWidgets.LektionSelector;
import de.hofuniversity.iws.shared.dto.LessonPreview;
import de.hofuniversity.iws.shared.services.*;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import org.tritonus.share.ArraySet;

import static de.hofuniversity.iws.shared.CollectionUtils.*;

/**
 *
 * @author Oliver
 */
public class Lektionswahl extends Composite {

    private static LektionswahlUiBinder uiBinder = GWT.create(LektionswahlUiBinder.class);
    private static final int TOTAL_TREE_WIDTH = 880;
    private static final int TREE_Y_STRIDE = 160;
    private static final int TREE_X_OFFSET = -40;
    private final LessonServiceAsync lessonService = (LessonServiceAsync) GWT.create(LessonService.class);
    @UiField
    HTMLPanel lektionTree;
    private final SubjectJson subject;

    interface LektionswahlUiBinder extends UiBinder<Widget, Lektionswahl> {
    }

    public Lektionswahl(SubjectJson thema) {
        subject = thema;
        initWidget(uiBinder.createAndBindUi(this));
        lessonService.getLessonPreviews(thema.getName(), new LessionAsyncCallback());
    }

    private void setup(List<LessonPreview> l) {
        //set parents
        final Map<String, LessonPreview> idToBean = new HashMap<String, LessonPreview>();
        for (LessonPreview lektionDTO : l) {
            idToBean.put(lektionDTO.getName(), lektionDTO);
        }

        Map<String, List<LessonPreview>> idToChildren = new HashMap<String, List<LessonPreview>>();
        for (LessonPreview lektionDTO : l) {
            List<LessonPreview> get = idToChildren.get(lektionDTO.getParent());
            if (get == null) {
                get = new ArrayList<LessonPreview>();
                idToChildren.put(lektionDTO.getParent(), get);
            }
            get.add(lektionDTO);
        }

        Map<Integer, List<LessonPreview>> groupedByDepth = groupBy(l, new Selector<LessonPreview, Integer>() {
            @Override
            public Integer select(LessonPreview e) {
                int count = 0;
                LessonPreview acc = e;
                while ((acc = idToBean.get(acc.getParent())) != null) {
                    ++count;
                }
                return count;
            }
        });

        List<Entry<Integer, List<LessonPreview>>> lektionsDepth = new ArraySet(groupedByDepth.entrySet());
        Collections.sort(lektionsDepth, new Comparator<Entry<Integer, List<LessonPreview>>>() {
            @Override
            public int compare(Entry<Integer, List<LessonPreview>> o1, Entry<Integer, List<LessonPreview>> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });

        List<Integer> widths = select(lektionsDepth, new Selector<Entry<Integer, List<LessonPreview>>, Integer>() {
            @Override
            public Integer select(Entry<Integer, List<LessonPreview>> e) {
                return e.getValue().size();
            }
        });

        addToTree(lektionsDepth.get(0).getValue(), 0, 0, widths, idToChildren);
    }

    private void addToTree(List<LessonPreview> lek, int depth, int offset,
                           List<Integer> widths, Map<String, List<LessonPreview>> idToChildren) {
        int unit = TOTAL_TREE_WIDTH / widths.get(depth);
        int childs = 0;
        for (int i = 0; i < lek.size(); i++) {
            int x = unit * (i + offset) + unit / 2 + TREE_X_OFFSET;
            int y = depth * TREE_Y_STRIDE;
            lektionTree.add(new LektionSelector(lek.get(i), subject, x, y));
            List<LessonPreview> children = idToChildren.get(lek.get(1).getName());
            if (children != null) {
                addToTree(children, depth + 1, childs, widths, idToChildren);
                childs += children.size();
            }
        }
    }

    private class LessionAsyncCallback implements AsyncCallback<List<LessonPreview>> {

        @Override
        public void onFailure(Throwable caught) {
            throw new UnsupportedOperationException(caught.getMessage());
        }

        @Override
        public void onSuccess(List<LessonPreview> result) {
            setup(result);
        }
    }
}
