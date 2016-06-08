package com.magorasystems.mafmodules.common.utils.rx;

import rx.Observer;
import rx.Subscriber;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class PresenterSubscriber<T> extends Subscriber<T> {

    private final Observer<T> internalObserver;

    public PresenterSubscriber(Observer<T> internalObserver) {
        this.internalObserver = internalObserver;
    }

    @Override
    public void onCompleted() {
        internalObserver.onCompleted();
    }

    @Override
    public void onError(Throwable e) {
        internalObserver.onError(e);
    }

    @Override
    public void onNext(T t) {
        internalObserver.onNext(t);
    }
}
