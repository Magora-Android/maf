package com.magorasystems.mafmodules.dagger.component;

import com.magorasystems.mafmodules.application.SampleApplication;
import com.magorasystems.mafmodules.common.dagger.component.CommonModuleComponent;
import com.magorasystems.mafmodules.dagger.module.AuthInteratorModule;
import com.magorasystems.mafmodules.dagger.module.AuthNetworkModule;
import com.magorasystems.mafmodules.dagger.module.AuthPresenterModule;
import com.magorasystems.mafmodules.dagger.module.AuthProviderModule;
import com.magorasystems.mafmodules.dagger.scope.ApplicationScope;
import com.magorasystems.mafmodules.mvp.interactor.SimpleAuthInteractorImpl;
import com.magorasystems.mafmodules.mvp.presenter.SimpleAuthPresenterImpl;
import com.magorasystems.mafmodules.network.provider.AuthRestProvider;
import com.magorasystems.mafmodules.ui.activity.SampleActivity;
import com.magorasystems.mafmodules.ui.fragment.AuthorizationFragmentImpl;

import dagger.Component;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */

@Component(dependencies = CommonModuleComponent.class,
           modules = {AuthNetworkModule.class,
                   AuthProviderModule.class, AuthInteratorModule.class,
                   AuthPresenterModule.class})
@ApplicationScope
public interface SampleComponent {

    CommonModuleComponent commonModuleComponent();

    void inject(SampleApplication application);

    void inject(SampleActivity activity);

    void inject(AuthRestProvider provider);

    void inject(SimpleAuthInteractorImpl i);

    void inject(SimpleAuthPresenterImpl p);

    void inject(AuthorizationFragmentImpl f);
}
