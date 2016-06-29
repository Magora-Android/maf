package com.magorasystems.mafmodules.common.module.input;

import rx.Observable;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface InteractiveView<P> {

    Observable<? extends P> model();

    void destroy();
}
