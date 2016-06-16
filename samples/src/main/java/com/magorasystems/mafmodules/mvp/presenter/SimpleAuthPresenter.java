package com.magorasystems.mafmodules.mvp.presenter;

import com.magorasystems.mafmodules.authmodule.presenter.AuthPresenter;
import com.magorasystems.mafmodules.authmodule.router.AuthRouter;
import com.magorasystems.mafmodules.authmodule.view.impl.StringAuthView;
import com.magorasystems.mafmodules.common.mvp.presenter.BasePresenter;
import com.magorasystems.mafmodules.common.utils.component.Injectable;
import com.magorasystems.mafmodules.dagger.component.SampleComponent;
import com.magorasystems.mafmodules.mvp.interactor.SimpleAuthInteractor;
import com.magorasystems.protocolapi.auth.dto.response.StringAuthInfo;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface SimpleAuthPresenter extends AuthPresenter<StringAuthView,StringAuthInfo, SimpleAuthInteractor, AuthRouter> , Injectable<SampleComponent>, BasePresenter<StringAuthView, AuthRouter> {
}
