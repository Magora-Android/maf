package com.magorasystems.mafmodules.common.mvp.presenter;

import com.magorasystems.mafmodules.common.mvp.interactor.BaseInteractor;
import com.magorasystems.mafmodules.common.mvp.router.BaseRouter;
import com.magorasystems.mafmodules.common.mvp.view.BaseView;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class BaseIteratorPresenter<I extends BaseInteractor, V extends BaseView, R extends BaseRouter, M> extends CommonPresenter<V, R,M> {

    private I iterator;

    public BaseIteratorPresenter(I iterator) {
        this.iterator = iterator;
    }
}
