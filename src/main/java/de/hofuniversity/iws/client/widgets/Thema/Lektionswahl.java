/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.Thema;

import java.util.*;
import java.util.Map.Entry;

import de.hofuniversity.iws.client.widgets.SubWidgets.LektionSelector;
import de.hofuniversity.iws.shared.dto.*;
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

    interface LektionswahlUiBinder extends UiBinder<Widget, Lektionswahl> {
    }

    public Lektionswahl(ThemaDTO thema) {
        initWidget(uiBinder.createAndBindUi(this));
        lessonService.readLessons(thema.getTopicName(), new LessionAsyncCallback());
    }

    private void setup(List<LektionDTO> l) {
        //set parents
        final Map<String, LektionDTO> idToBean = new HashMap<String, LektionDTO>();
        for (LektionDTO lektionDTO : l) {
            idToBean.put(lektionDTO.getId(), lektionDTO);
        }

        Map<String, List<LektionDTO>> idToChildren = new HashMap<String, List<LektionDTO>>();
        for (LektionDTO lektionDTO : l) {
            List<LektionDTO> get = idToChildren.get(lektionDTO.getParent());
            if (get == null) {
                get = new ArrayList<LektionDTO>();
                idToChildren.put(lektionDTO.getParent(), get);
            }
            get.add(lektionDTO);
        }

        Map<Integer, List<LektionDTO>> groupedByDepth = groupBy(l, new Selector<LektionDTO, Integer>() {
            @Override
            public Integer select(LektionDTO e) {
                int count = 0;
                LektionDTO acc = e;
                while ((acc = idToBean.get(acc.getParent())) != null) {
                    ++count;
                }
                return count;
            }
        });

        List<Entry<Integer, List<LektionDTO>>> lektionsDepth = new ArraySet(groupedByDepth.entrySet());
        Collections.sort(lektionsDepth, new Comparator<Entry<Integer, List<LektionDTO>>>() {
            @Override
            public int compare(Entry<Integer, List<LektionDTO>> o1, Entry<Integer, List<LektionDTO>> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });

        List<Integer> widths = select(lektionsDepth, new Selector<Entry<Integer, List<LektionDTO>>, Integer>() {
            @Override
            public Integer select(Entry<Integer, List<LektionDTO>> e) {
                return e.getValue().size();
            }
        });

        addToTree(lektionsDepth.get(0).getValue(), 0, 0, widths, idToChildren);
    }

    private void addToTree(List<LektionDTO> lek, int depth, int offset,
                           List<Integer> widths, Map<String, List<LektionDTO>> idToChildren) {
        int unit = TOTAL_TREE_WIDTH / widths.get(depth);
        int childs = 0;
        for (int i = 0; i < lek.size(); i++) {
            int x = unit * (i + offset) + unit / 2 + TREE_X_OFFSET;
            int y = depth * TREE_Y_STRIDE;
            lektionTree.add(new LektionSelector(lek.get(i), x, y));
            List<LektionDTO> children = idToChildren.get(lek.get(1).getId());
            if (children != null) {
                addToTree(children, depth + 1, childs, widths, idToChildren);
                childs += children.size();
            }
        }
    }

    private class LessionAsyncCallback implements AsyncCallback<List<LektionDTO>> {

        @Override
        public void onFailure(Throwable caught) {
            throw new UnsupportedOperationException(caught.getMessage());
        }

        @Override
        public void onSuccess(List<LektionDTO> result) {
            setup(result);
        }
    }
}
