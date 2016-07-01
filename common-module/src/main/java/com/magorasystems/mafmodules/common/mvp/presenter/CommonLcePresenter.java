package com.magorasystems.mafmodules.common.mvp.presenter;

import com.magorasystems.mafmodules.common.module.output.ViewOutput;
import com.magorasystems.mafmodules.common.mvp.router.BaseRouter;
import com.magorasystems.mafmodules.common.mvp.view.BaseLceView;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class CommonLcePresenter<M, V extends BaseLceView<? super M>, R extends BaseRouter, VO extends ViewOutput<? extends M>> extends CommonPresenter<V, R,M, VO> {

    protected final void showProgress() {
        if(!isViewAttached()) {
            return;
        }
        V view = getView();
        if (null != view) {
            view.showProgress();
        }
    }

    protected final void removeView() {
        if(!isViewAttached()) {
            return;
        }
        V view = getView();
        if (null != view) {
            view.detachView();
        }
    }

    protected final void showContent() {
        if(!isViewAttached()) {
            return;
        }
        V view = getView();
        if (null != view) {
            view.showContent();
        }
    }

    protected final void setModel(M model) {
        if(!isViewAttached()) {
            return;
        }
        V view = getView();
        if (null != view) {
            view.setModel(model);
        }
    }
}
