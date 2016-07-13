package com.magorasystems.mafmodules.dagger.module;

import com.magorasystems.mafmodules.common.dagger.module.BaseModule;
import com.magorasystems.mafmodules.dagger.scope.ApplicationScope;
import com.magorasystems.mafmodules.interactor.SimpleRegistrationInteractor;
import com.magorasystems.mafmodules.presenter.SimpleRegistrationPresenter;
import com.magorasystems.mafmodules.presenter.impl.SimpleRegistrationPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
@Module
public class SamplePresenterModule implements BaseModule {

    @Provides
    @ApplicationScope
    protected SimpleRegistrationPresenter providerRegistrationPresenter(SimpleRegistrationInteractor interactor) {
        return new SimpleRegistrationPresenterImpl(interactor);
    }
}
