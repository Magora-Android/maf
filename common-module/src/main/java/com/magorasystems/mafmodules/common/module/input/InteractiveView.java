package com.magorasystems.mafmodules.common.module.input;

import rx.Observable;

/**
 * @param <P> parameter of Observable model extends from this argument P\
 * @author Valentin S.Bolkonsky
 *         Developed by Magora Team (magora-systems.com). 2016.
 */
public interface InteractiveView<P> {

    Observable<? extends P> model();

    void destroy();
}
