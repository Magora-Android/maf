package com.magorasystems.mafmodules.common.mvp.presenter;

import com.magorasystems.mafmodules.common.mvp.interactor.BaseInteractor;
import com.magorasystems.mafmodules.common.mvp.router.BaseRouter;
import com.magorasystems.mafmodules.common.mvp.view.BaseLceView;

import rx.Observer;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class BaseIteratorLcePresenter<M, I extends BaseInteractor<? super M>, V extends BaseLceView<? super M>, R extends BaseRouter>
        extends CommonLcePresenter<M, V, R> implements Observer<M> {

    private I iteractor;

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
    public void onError(Throwable e) {
        showContent();
        showError(e);
    }

    @Override
    public void onNext(M m) {
        setModel(m);
    }
}
