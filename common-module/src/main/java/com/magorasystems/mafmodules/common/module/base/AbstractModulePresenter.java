package com.magorasystems.mafmodules.common.module.base;

import com.magorasystems.mafmodules.common.module.input.InteractiveView;
import com.magorasystems.mafmodules.common.module.input.ViewInput;
import com.magorasystems.mafmodules.common.module.output.ViewOutput;
import com.magorasystems.mafmodules.common.mvp.presenter.BaseLifecyclePresenter;
import com.magorasystems.mafmodules.common.mvp.router.BaseRouter;
import com.magorasystems.mafmodules.common.mvp.view.BaseView;

import rx.Observable;
import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

/**
 * @param <R>  router, that must extends {@link BaseRouter}
 * @param <VI> view input, that must extends {@link ViewInput}
 * @param <VO> view output, that must extends {@link ViewOutput}
 * @param <I>  module input, that must extends {@link ModuleInput}
 * @author Valentin S.Bolkonsky
 *         Developed by Magora Team (magora-systems.com). 2016.
 */
public abstract class AbstractModulePresenter<R extends BaseRouter, VI extends ViewInput<? extends BaseView, ? extends InteractiveView<?>>,
        VO extends ViewOutput<?>, I extends ModuleInput<VI, R>> implements ModulePresenter<R, VI, VO, I> {

    private I moduleInput;

    protected final CompositeSubscription subscription = new CompositeSubscription();
    private Subscriber<? super VO> outSubscriber;

    /**
     * Setting module input
     *
     * @param input your module input
     */
    @Override
    public void input(I input) {
        this.moduleInput = input;
    }

    /**
     * @return module input
     */
    protected final I getModuleInput() {
        return moduleInput;
    }

    /**
     * Start presenter
     */
    @Override
    public void start() {
        getPresenter().onStart();
    }

    /**
     * Stop presenter
     */
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

    /**
     * Detach view and remove router from presenter
     * clear and set to null {@code moduleInput} <br>
     * unsubscribe all subscribers (if has) and clear CompositeSubscriptions
     *
     * @param retainInstance answer
     */
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

    /**
     * do nothing
     */
    @Override
    public void onCompleted() {

    }

    /**
     * do nothing
     */
    @Override
    public void onError(Throwable e) {

    }

    /**
     * do nothing
     */
    @Override
    public void onNext(VO vo) {

    }

    protected R getRouter() {
        if (moduleInput != null) {
            return moduleInput.getRouter();
        }
        return null;
    }

    protected  BaseView getPassiveView() {
        final VI vi = getInputView();
        if (vi == null) {
            return null;
        }
        return vi.getPassiveView();
    }

    protected InteractiveView<?> getInteractiveView() {
        final VI vi = getInputView();
        if (vi == null) {
            return null;
        }
        return vi.getInteractiveView();
    }

    protected VI getInputView() {
        if (moduleInput == null) {
            return null;
        }
        return moduleInput.getViewInput();
    }

    /**
     * Call lifecycle methods of {@link Subscriber} from {@link AbstractModulePresenter} <br>
     * if its not unsubscribed
     *
     * @return subcriber
     */
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
