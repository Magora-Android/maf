package com.magorasystems.mafmodules.dagger.component;

import com.magorasystems.mafmodules.common.dagger.component.CommonModuleComponent;
import com.magorasystems.mafmodules.dagger.module.profile.ProfileInteractorModule;
import com.magorasystems.mafmodules.dagger.module.profile.ProfileNetworkModule;
import com.magorasystems.mafmodules.dagger.module.profile.ProfilePresenterModule;
import com.magorasystems.mafmodules.dagger.module.profile.ProfileProviderModule;
import com.magorasystems.mafmodules.dagger.module.profile.ProfileStoreModule;
import com.magorasystems.mafmodules.dagger.scope.ProfileScope;
import com.magorasystems.mafmodules.interactor.SimpleProfileInteractor;
import com.magorasystems.mafmodules.interactor.impl.SimpleProfileInteractorImpl;
import com.magorasystems.mafmodules.module.UserProfilePresenterModuleImpl;
import com.magorasystems.mafmodules.presenter.SimpleProfilePresenter;
import com.magorasystems.mafmodules.presenter.impl.SimpleProfilePresenterImpl;
import com.magorasystems.mafmodules.provider.SimpleRestProfileProvider;
import com.magorasystems.mafmodules.provider.impl.SimpleRestProfileProviderImpl;
import com.magorasystems.mafmodules.ui.fragment.ShowProfileFragment;

import dagger.Component;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
@Component(dependencies = {CommonModuleComponent.class},
           modules = {
                   ProfileNetworkModule.class,
                   ProfileProviderModule.class,
                   ProfileInteractorModule.class,
                   ProfilePresenterModule.class,
                   ProfileStoreModule.class
           })
@ProfileScope
public interface ProfileComponent {

    SimpleRestProfileProvider getRestApiProvider();

    SimpleProfileInteractor getProfileInteractor();

    SimpleProfilePresenter getProfilePresneter();

    void inject(ProfileComponentProvider provider);

    void inject(SimpleRestProfileProviderImpl provider);

    void inject(SimpleProfileInteractorImpl i);

    void inject(SimpleProfilePresenterImpl p);

    void inject(UserProfilePresenterModuleImpl m);

    void inject(ShowProfileFragment f);
}
