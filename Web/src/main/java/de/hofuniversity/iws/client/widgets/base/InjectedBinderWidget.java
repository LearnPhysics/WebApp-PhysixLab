/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.base;

import com.chrisgammage.ginjitsu.client.AfterInject;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.*;
import javax.inject.Inject;

/**
 *
 * @author Daniel Heinrich <DannyNullZwo@gmail.com>
 */
public class InjectedBinderWidget<E extends UiBinder> extends Composite {

    @Inject
    private E uiBinder;

    @AfterInject
    public void bind() {
        Widget w = (Widget) uiBinder.createAndBindUi(this);
        initWidget(w);
        initWidget();
    }
    
    public void initWidget()
    {        
    }
}
