package com.magorasystems.mafmodules.common.mvp.presenter;

import com.magorasystems.mafmodules.common.mvp.router.BaseRouter;
import com.magorasystems.mafmodules.common.mvp.view.BaseLceView;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class CommonLcePresenter<M, V extends BaseLceView<M>, R extends BaseRouter> extends CommonPresenter<V, R> {

    protected final void showProgress() {
        V view = getView();
        if (null != view) {
            view.showProgress();
        }
    }

    protected final void removeView() {
        V view = getView();
        if (null != view) {
            view.detachView();
        }
    }

    protected final void showContent() {
        V view = getView();
        if (null != view) {
            view.showContent();
        }
    }

    protected final void setModel(M model) {
        V view = getView();
        if (null != view) {
            view.setModel(model);
        }
    }
}