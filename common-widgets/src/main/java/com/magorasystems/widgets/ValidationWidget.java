package com.magorasystems.widgets;

import java.util.Collection;

import rx.Observable;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S. Bolkonsky
 */
public interface ValidationWidget<T> {

    Observable<Boolean> validation();

    void updateRules(Collection<T> rules);
}
