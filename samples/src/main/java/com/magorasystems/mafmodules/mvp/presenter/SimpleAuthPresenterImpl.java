package com.magorasystems.mafmodules.mvp.presenter;

import com.magorasystems.mafmodules.authmodule.presenter.impl.AuthLcePresenter;
import com.magorasystems.mafmodules.common.utils.component.HasComponent;
import com.magorasystems.mafmodules.dagger.component.SampleComponent;
import com.magorasystems.mafmodules.mvp.interactor.SimpleAuthInteractor;

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
    public void inject(HasComponent<? extends SampleComponent> hasComponent) {
        hasComponent.getComponent().inject(this);
    }
}
