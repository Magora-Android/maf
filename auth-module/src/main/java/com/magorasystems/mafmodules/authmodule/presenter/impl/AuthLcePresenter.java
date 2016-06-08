package com.magorasystems.mafmodules.authmodule.presenter.impl;

import com.magorasystems.mafmodules.authmodule.interactor.BaseAuthInteractor;
import com.magorasystems.mafmodules.authmodule.model.AuthViewModel;
import com.magorasystems.mafmodules.authmodule.presenter.AuthPresenter;
import com.magorasystems.mafmodules.authmodule.router.AuthRouter;
import com.magorasystems.mafmodules.authmodule.view.impl.StringAuthView;
import com.magorasystems.mafmodules.common.mvp.presenter.BaseIteratorLcePresenter;
import com.magorasystems.mafmodules.common.mvp.presenter.BaseLifecyclePresenter;
import com.magorasystems.mafmodules.common.utils.rx.PresenterSubscriber;
import com.magorasystems.protocolapi.auth.dto.request.AuthRequest;
import com.magorasystems.protocolapi.auth.dto.response.StringAuthInfo;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class AuthLcePresenter<COMPONENT, I extends BaseAuthInteractor<COMPONENT, StringAuthInfo>>
        extends BaseIteratorLcePresenter<StringAuthInfo, I, StringAuthView, AuthRouter>
        implements AuthPresenter<StringAuthInfo, I>, BaseLifecyclePresenter<StringAuthView, AuthRouter> {

    public AuthLcePresenter(I iterator) {
        super(iterator);
    }

    @Override
    public void authorization(final AuthViewModel view) {
        getIteractor().executeAuthToken(new AuthRequest(view.getLogin(), view.getPassword()), new PresenterSubscriber<>(this));
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        getIteractor().unsubscribe();
    }
}
