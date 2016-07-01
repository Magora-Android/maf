package com.magorasystems.mafmodules.common.module.base;

import com.magorasystems.mafmodules.common.module.input.ViewInput;
import com.magorasystems.mafmodules.common.module.output.ViewOutput;
import com.magorasystems.mafmodules.common.mvp.presenter.BaseLifecyclePresenter;
import com.magorasystems.mafmodules.common.mvp.router.BaseRouter;
import com.magorasystems.mafmodules.common.mvp.view.BaseView;

import rx.Observable;
import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class AbstractModulePresenter<R extends BaseRouter, VI extends ViewInput<?, ?>,
        VO extends ViewOutput<?>, I extends ModuleInput<VI, R>> implements ModulePresenter<R, VI, VO, I> {

    private I moduleInput;

    protected final CompositeSubscription subscription = new CompositeSubscription();
    private Subscriber<? super VO> outSubscriber;

    @Override
    public void input(I input) {
        this.moduleInput = input;
    }

    protected final I getModuleInput() {
        return moduleInput;
    }

    @Override
    public void start() {
        getPresenter().onStart();
    }

    @Override
    public void stop() {
        getPresenter().onStop();
    }

    protected abstract BaseLifecyclePresenter<? extends BaseView, ? extends BaseRouter, ?, ? extends VO> getPresenter();

    @Override
    public void output(Observable<VO> output) {
        if (outSubscriber != null && !outSubscriber.isUnsubscribed()) {
            outSubscriber.unsubscribe();
        }
        outSubscriber = outputSubscriber();
        subscription.add(output.subscribe(outSubscriber));
    }

    @Override
    public void destroy(boolean retainInstance) {
        getPresenter().detachView(retainInstance);
        getPresenter().removeRouter();
        moduleInput.clear();
        moduleInput = null;
        if (subscription.hasSubscriptions()) {
            subscription.unsubscribe();
            subscription.clear();
        }
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(VO vo) {

    }

    protected Subscriber<? super VO> outputSubscriber() {
        return new Subscriber<VO>() {
            @Override
            public void onCompleted() {
                if (!isUnsubscribed()) {
                    AbstractModulePresenter.this.onCompleted();
                }
            }

            @Override
            public void onError(Throwable e) {
                if (!isUnsubscribed()) {
                    AbstractModulePresenter.this.onError(e);
                }
            }

            @Override
            public void onNext(VO vo) {
                if (!isUnsubscribed()) {
                    AbstractModulePresenter.this.onNext(vo);
                }
            }
        };

    }
}
