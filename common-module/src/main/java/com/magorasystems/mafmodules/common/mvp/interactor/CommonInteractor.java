package com.magorasystems.mafmodules.common.mvp.interactor;

import com.magorasystems.mafmodules.common.dagger.HasComponent;
import com.magorasystems.mafmodules.common.utils.SchedulersUtils;

import rx.Observable;
import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class CommonInteractor implements BaseInteractor {

    private final CompositeSubscription subscription = new CompositeSubscription();
    private final SchedulersUtils.CoreScheduler coreScheduler;

    protected abstract void inject(HasComponent<?> hasComponent);

    protected CommonInteractor(HasComponent<?> hasComponent, SchedulersUtils.CoreScheduler scheduler) {
        this.coreScheduler = scheduler;
        if (hasComponent != null) {
            inject(hasComponent);
        }
    }

    @Override
    public void execute(Observable<?> observer, Subscriber<? super Object> subscriber) {
        subscription.add(observer
                .compose(SchedulersUtils.applySchedulers(coreScheduler))
                .subscribe(subscriber));
    }

    @Override
    public void unsubscribe() {
        subscription.clear();
    }
}
