package com.magorasystems.mafmodules.common.mvp.presenter;


import com.magorasystems.mafmodules.common.module.output.ViewOutput;
import com.magorasystems.mafmodules.common.mvp.router.BaseRouter;
import com.magorasystems.mafmodules.common.mvp.view.BaseView;

import rx.Observable;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S. Bolkonsky
 */
public interface BasePresenter<V extends BaseView, R extends BaseRouter, M, VO extends ViewOutput<? extends M>> {

    void attachView(V view);

    void detachView(boolean retainInstance);

    void setRouter(R router);

    void removeRouter();

    Observable<VO> output();

    void destroy();
}
