package com.magorasystems.mafmodules.authmodule.presenter;


import com.magorasystems.mafmodules.authmodule.dagger.component.AuthComponent;
import com.magorasystems.mafmodules.authmodule.interactor.impl.SimpleAuthInteractor;
import com.magorasystems.mafmodules.authmodule.module.outpit.AuthViewOutput;
import com.magorasystems.mafmodules.authmodule.presenter.impl.AuthLcePresenter;
import com.magorasystems.protocolapi.auth.dto.response.StringAuthInfo;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SimpleAuthPresenterImpl extends AuthLcePresenter<SimpleAuthInteractor> implements SimpleAuthPresenter {

    public SimpleAuthPresenterImpl(AuthComponent component, SimpleAuthInteractor iterator) {
        super(iterator);
        inject(component);
    }

    @Override
    public void inject(AuthComponent component) {
        component.inject(this);
    }


    @Override
    protected AuthViewOutput newEvent(StringAuthInfo model) {
        return new AuthViewOutput(model);
    }
}
