package com.magorasystems.mafmodules.authmodule.presenter;

import com.magorasystems.mafmodules.authmodule.interactor.AuthInteractor;
import com.magorasystems.mafmodules.authmodule.model.AuthViewModel;
import com.magorasystems.mafmodules.authmodule.router.AuthRouter;
import com.magorasystems.mafmodules.authmodule.view.impl.StringAuthView;
import com.magorasystems.mafmodules.common.mvp.presenter.BaseLifecyclePresenter;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface AuthPresenter<R, I extends AuthInteractor<R>> extends BaseLifecyclePresenter<StringAuthView, AuthRouter> {

    void authorization(AuthViewModel view);

}
