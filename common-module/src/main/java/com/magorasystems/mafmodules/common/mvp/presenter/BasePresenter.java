package com.magorasystems.mafmodules.common.mvp.presenter;


import com.magorasystems.mafmodules.common.mvp.router.BaseRouter;
import com.magorasystems.mafmodules.common.mvp.view.BaseView;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S. Bolkonsky
 */
public interface BasePresenter<V extends BaseView, R extends BaseRouter> {

    void attachView(V view);

    void detachView(boolean retainInstance);

    void setRouter(R router);

    void removeRouter();
}
