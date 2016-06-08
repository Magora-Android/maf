package com.magorasystems.mafmodules.common.mvp.presenter;

import com.magorasystems.mafmodules.common.mvp.interactor.BaseInteractor;
import com.magorasystems.mafmodules.common.mvp.router.BaseRouter;
import com.magorasystems.mafmodules.common.mvp.view.BaseLceView;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class BaseIteratorLcePresenter<I extends BaseInteractor, M, V extends BaseLceView<M>, R extends BaseRouter>
        extends CommonLcePresenter<M, V, R> {

    private I iterator;

    public BaseIteratorLcePresenter(I iterator) {
        this.iterator = iterator;
    }
}
