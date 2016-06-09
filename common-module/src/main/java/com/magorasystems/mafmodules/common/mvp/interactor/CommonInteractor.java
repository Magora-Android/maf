package com.magorasystems.mafmodules.common.mvp.interactor;

import com.magorasystems.mafmodules.common.utils.SchedulersUtils;

import rx.Observable;
import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class CommonInteractor<R> implements BaseInteractor<R> {

    private final CompositeSubscription subscription = new CompositeSubscription();
    private final SchedulersUtils.CoreScheduler coreScheduler;

    protected CommonInteractor( SchedulersUtils.CoreScheduler scheduler) {
        this.coreScheduler = scheduler;
    }

    @Override
    public void execute(Observable<? extends R> observer, Subscriber<R> subscriber) {
        subscription.add(observer
                .compose(SchedulersUtils.applySchedulers(coreScheduler))
                .subscribe(subscriber));
    }

    @Override
    public void unsubscribe() {
        subscription.clear();
    }
}
