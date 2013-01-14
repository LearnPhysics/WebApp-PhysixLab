/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.Thema;

import java.util.*;
import java.util.Map.Entry;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.inject.assistedinject.*;
import de.hofuniversity.iws.client.jsonbeans.SubjectJson;
import de.hofuniversity.iws.client.widgets.SubWidgets.LektionSelector.LektionSelectorFactory;
import de.hofuniversity.iws.shared.CollectionUtils.Selector;
import de.hofuniversity.iws.shared.dto.LessonPreview;
import de.hofuniversity.iws.shared.services.LessonServiceAsync;

import static de.hofuniversity.iws.shared.CollectionUtils.groupBy;
import static de.hofuniversity.iws.shared.CollectionUtils.select;

/**
 *
 * @author Oliver
 */
public class Lektionswahl extends Composite {

    interface LektionswahlUiBinder extends UiBinder<Widget, Lektionswahl> {
    }
    private static LektionswahlUiBinder uiBinder = GWT.create(LektionswahlUiBinder.class);
    private static final int TOTAL_TREE_WIDTH = 880;
    private static final int TREE_Y_STRIDE = 160;
    private static final int TREE_X_OFFSET = -40;
    private final LektionSelectorFactory factory;
    private final SubjectJson subject;
    @UiField HTMLPanel lektionTree;

    public interface LektionswahlFactory {

        public Lektionswahl create(SubjectJson thema);
    }

    @AssistedInject
    public Lektionswahl(LektionSelectorFactory factory, LessonServiceAsync lessonService,
                        @Assisted SubjectJson thema) {
        subject = thema;
        this.factory = factory;
        initWidget(uiBinder.createAndBindUi(this));
        lessonService.getLessonPreviews(thema.getName(), new AsyncCallback<LessonPreview[]>() {
            @Override
            public void onFailure(Throwable caught) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void onSuccess(LessonPreview[] result) {
                setup(result);
            }
        });
    }

    private void setup(LessonPreview[] l) {
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

        List<Entry<Integer, List<LessonPreview>>> lektionsDepth = new ArrayList<Entry<Integer, List<LessonPreview>>>(groupedByDepth.entrySet());
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

        if (lektionsDepth.size() > 0) {
            addToTree(lektionsDepth.get(0).getValue(), 0, 0, widths, idToChildren);
        }
    }

    private void addToTree(List<LessonPreview> lek, int depth, int offset,
                           List<Integer> widths, Map<String, List<LessonPreview>> idToChildren) {
        int unit = TOTAL_TREE_WIDTH / widths.get(depth);
        int childs = 0;
        for (int i = 0; i < lek.size(); i++) {
            int x = unit * (i + offset) + unit / 2 + TREE_X_OFFSET;
            int y = depth * TREE_Y_STRIDE;
            lektionTree.add(factory.create(lek.get(i), subject, x, y));
            List<LessonPreview> children = idToChildren.get(lek.get(i).getName());
            if (children != null) {
                addToTree(children, depth + 1, childs, widths, idToChildren);
                childs += children.size();
            }
        }
    }
}
