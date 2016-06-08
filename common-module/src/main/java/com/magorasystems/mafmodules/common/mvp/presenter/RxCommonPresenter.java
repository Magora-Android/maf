package com.magorasystems.mafmodules.common.mvp.presenter;


import com.magorasystems.mafmodules.common.mvp.router.BaseRouter;
import com.magorasystems.mafmodules.common.mvp.view.BaseLceView;
import com.magorasystems.mafmodules.common.utils.SchedulersUtils;

import rx.Observable;
import rx.Subscriber;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S. Bolkonsky
 */
public abstract class RxCommonPresenter<V extends BaseLceView<M>, R extends BaseRouter, M>
        extends CommonLcePresenter<M, V, R> {

    protected Subscriber<M> subscriber;
    protected SchedulersUtils.CoreScheduler coreScheduler;

    public RxCommonPresenter(SchedulersUtils.CoreScheduler coreScheduler) {
        this.coreScheduler = coreScheduler;
    }


    /**
     * Unsubscribes the subscriber and set it to null
     */
    protected void unsubscribe() {
        if (subscriber != null && !subscriber.isUnsubscribed()) {
            subscriber.unsubscribe();
        }

        subscriber = null;
    }

    /**
     * Subscribes the presenter himself as subscriber on the observable
     *
     * @param observable The observable to subscribe
     */
    public void subscribe(Observable<M> observable) {

        if (isViewAttached()) {
            showProgress();
        }

        unsubscribe();

        subscriber = new Subscriber<M>() {

            @Override
            public void onCompleted() {
                RxCommonPresenter.this.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                RxCommonPresenter.this.onError(e);
            }

            @Override
            public void onNext(M m) {
                RxCommonPresenter.this.onNext(m);
            }
        };

        observable.compose(SchedulersUtils.applySchedulers(coreScheduler))
                .onBackpressureDrop()
                .subscribe(subscriber);
    }

    public boolean isSubscribed() {
        return subscriber != null && !subscriber.isUnsubscribed();
    }

    protected void onCompleted() {
        if (isViewAttached()) {
            showContent();
        }
        unsubscribe();
    }

    protected void onError(Throwable e) {
        if (isViewAttached()) {
            showError(e);
        }
        unsubscribe();
    }

    protected void onNext(M data) {
        if (isViewAttached()) {
            setModel(data);
        }
    }

    @Override
    public void detachView(boolean retainInstance) {
        if (isViewAttached()) {
            removeView();
        }
        super.detachView(retainInstance);
        if (!retainInstance) {
            unsubscribe();
        }
    }

}