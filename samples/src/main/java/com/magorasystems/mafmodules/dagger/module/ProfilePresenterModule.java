package com.magorasystems.mafmodules.dagger.module;

import android.content.Context;

import com.magorasystems.mafmodules.common.dagger.module.BaseModule;
import com.magorasystems.mafmodules.dagger.scope.ProfileScope;
import com.magorasystems.mafmodules.interactor.impl.SimpleProfileInteractor;
import com.magorasystems.mafmodules.presenter.impl.SimpleProfilePresenter;
import com.magorasystems.mafmodules.presenter.impl.SimpleProfilePresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
@Module
public class ProfilePresenterModule implements BaseModule {

    @Provides
    @ProfileScope
    protected SimpleProfilePresenter providerProfilePresenter(Context context, SimpleProfileInteractor interactor) {
        return new SimpleProfilePresenterImpl(context, interactor);
    }
}
