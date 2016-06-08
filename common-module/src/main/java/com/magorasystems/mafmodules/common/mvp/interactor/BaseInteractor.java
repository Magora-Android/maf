package com.magorasystems.mafmodules.common.mvp.interactor;

import rx.Observable;
import rx.Subscriber;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface BaseInteractor<R> {

    void execute(Observable<R> observer, Subscriber<R> subscriber);

    void unsubscribe();
}
