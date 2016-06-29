package com.magorasystems.mafmodules.authmodule.presenter;

import com.magorasystems.mafmodules.authmodule.dagger.component.AuthComponent;
import com.magorasystems.mafmodules.authmodule.module.outpit.AuthViewOutput;
import com.magorasystems.mafmodules.authmodule.router.AuthRouter;
import com.magorasystems.mafmodules.authmodule.view.impl.StringAuthView;
import com.magorasystems.mafmodules.common.mvp.presenter.BasePresenter;
import com.magorasystems.mafmodules.common.utils.component.Injectable;

import rx.Observable;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface SimpleAuthPresenter extends AuthPresenter<StringAuthView, AuthRouter>,
        Injectable<AuthComponent>, BasePresenter<StringAuthView, AuthRouter> {

    Observable<AuthViewOutput> output();
}
