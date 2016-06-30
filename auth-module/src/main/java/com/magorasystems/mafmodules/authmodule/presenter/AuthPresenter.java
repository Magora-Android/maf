package com.magorasystems.mafmodules.authmodule.presenter;

import com.magorasystems.mafmodules.authmodule.performance.AuthViewModel;
import com.magorasystems.mafmodules.authmodule.router.AuthRouter;
import com.magorasystems.mafmodules.authmodule.view.impl.StringAuthView;
import com.magorasystems.mafmodules.common.mvp.presenter.BaseLifecyclePresenter;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface AuthPresenter<V  extends StringAuthView,R extends AuthRouter, M> extends BaseLifecyclePresenter<V, R, M> {

    void authorization(AuthViewModel view);

    void passwordRecover(Void v);

}
