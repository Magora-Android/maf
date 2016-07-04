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

    /**
     * Set your scheduler with background and main thread
     *
     * @param scheduler scheduler
     */
    protected CommonInteractor(SchedulersUtils.CoreScheduler scheduler) {
        this.coreScheduler = scheduler;
    }

    /**
     * Use this for execute your task with schedulers. <br>
     * Add to instance of {@link CompositeSubscription} your {@code observer} extends R <br>
     * with schedulers from {@link com.magorasystems.mafmodules.common.utils.SchedulersUtils.CoreScheduler}
     *
     * @param observer   your observer
     * @param subscriber your subscriber
     */
    @Override
    public void execute(Observable<? extends R> observer, Subscriber<R> subscriber) {
        subscription.add(observer
                .compose(SchedulersUtils.applySchedulers(coreScheduler))
                .subscribe(subscriber));
    }

    /**
     * Unsubscribe all subscribers with CompositeSubscription <br>
     * so that the CompositeSubscription is empty and able to manage new subscriptions.
     *
     * @see CompositeSubscription
     */
    @Override
    public void unsubscribe() {
        subscription.clear();
    }
}
