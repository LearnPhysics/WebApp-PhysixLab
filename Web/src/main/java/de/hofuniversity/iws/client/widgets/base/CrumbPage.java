/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.base;

import com.google.gwt.uibinder.client.*;
import de.hofuniversity.iws.client.widgets.Header;
import de.hofuniversity.iws.client.widgets.SubWidgets.BackButton.BackButtonFactory;
import de.hofuniversity.iws.client.widgets.SubWidgets.*;
import de.hofuniversity.iws.client.widgets.SubWidgets.Breadcrumb.BreadcrumbFactory;
import de.hofuniversity.iws.client.widgets.history.HistoryElement;
import javax.inject.*;

/**
 *
 * @author Daniel Heinrich <DannyNullZwo@gmail.com>
 */
public abstract class CrumbPage<E extends UiBinder> extends HistoryPage<E> {

    @Inject private BackButtonFactory backFactory;
    @Inject private BreadcrumbFactory breadFactory;
    @Inject private Provider<Header> headerProvider;
    private final HistoryElement element;

    public CrumbPage(HistoryElement element, String name) {
        super(name);
        this.element = element;
    }

    @UiFactory
    public Breadcrumb buildCrumb() {
        return breadFactory.create(element);
    }

    @UiFactory
    public BackButton buildBack() {
        return backFactory.create(element);
    }

    @UiFactory
    public Header getHeader() {
        return headerProvider.get();
    }
}
