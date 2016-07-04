package com.magorasystems.mafmodules.common.mvp.presenter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.magorasystems.mafmodules.common.module.output.ViewOutput;
import com.magorasystems.mafmodules.common.mvp.interactor.BaseInteractor;
import com.magorasystems.mafmodules.common.mvp.router.BaseRouter;
import com.magorasystems.mafmodules.common.mvp.view.BaseLceView;

import rx.Observable;
import rx.Observer;
import rx.subjects.PublishSubject;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class BaseIteratorLcePresenter<M, I extends BaseInteractor<? super M>, V extends BaseLceView<? super M>, R extends BaseRouter, VO extends ViewOutput<? extends M>>
        extends CommonLcePresenter<M, V, R, VO> implements Observer<M> {

    private I iteractor;

    private PublishSubject<VO> outputPublisher;

    protected abstract VO newEvent(M model);

    public BaseIteratorLcePresenter(I interactor) {
        this.iteractor = interactor;
    }

    protected final I getIteractor() {
        return iteractor;
    }

    @Override
    public void onCompleted() {
        showContent();
    }

    @Override
    public void onNext(M m) {
        setModel(m);
        if (outputPublisher != null) {
            outputPublisher.onNext(newEvent(m));
        }
    }

    @Override
    public Observable<VO> output() {
        if (outputPublisher == null) {
            outputPublisher = PublishSubject.create();
        }
        return outputPublisher;
    }

    @Override
    public void onError(Throwable e) {
        showContent();
        showError(e);
        if (outputPublisher != null) {
            outputPublisher.onError(e);
        }
    }

    @Override
    public void destroy() {
        if (outputPublisher != null) {
            outputPublisher.onCompleted();
            outputPublisher = null;
        }
    }
}
