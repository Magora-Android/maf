package com.magorasystems.mafmodules.authmodule.presenter;


import com.magorasystems.mafmodules.authmodule.dagger.component.AuthComponent;
import com.magorasystems.mafmodules.authmodule.interactor.impl.SimpleAuthInteractor;
import com.magorasystems.mafmodules.authmodule.presenter.impl.AuthLcePresenter;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SimpleAuthPresenterImpl extends AuthLcePresenter<SimpleAuthInteractor> implements SimpleAuthPresenter{

    public SimpleAuthPresenterImpl(SimpleAuthInteractor iterator) {
        super(iterator);
    }

    @Override
    public void inject(AuthComponent component) {
        component.inject(this);
    }
}
