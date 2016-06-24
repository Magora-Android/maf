package com.magorasystems.widgets;

import com.magorasystems.widgets.model.BaseViewModel;

import rx.Observable;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S. Bolkonsky
 */
public interface BaseWidget<M extends BaseViewModel, T> {

    Observable<? super M> model();

    void destroyWidget();

    WidgetAttributes getWidgetAttributes();

    void update(T model);
}
