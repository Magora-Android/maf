package com.magorasystems.mafmodules.common.mvp.interactor;

import rx.Observable;
import rx.Subscriber;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface BaseInteractor {

    void execute(Observable<?> observer, Subscriber<? super Object> subscriber);

    void unsubscribe();
}
