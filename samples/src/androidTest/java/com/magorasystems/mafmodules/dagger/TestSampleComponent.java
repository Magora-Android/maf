package com.magorasystems.mafmodules.dagger;

import com.magorasystems.mafmodules.BaseTest;
import com.magorasystems.mafmodules.DaggerTest;
import com.magorasystems.mafmodules.InteractorTest;
import com.magorasystems.mafmodules.NetworkTest;
import com.magorasystems.mafmodules.ProvidersTest;
import com.magorasystems.mafmodules.common.dagger.component.CommonModuleComponent;
import com.magorasystems.mafmodules.dagger.component.SampleComponent;
import com.magorasystems.mafmodules.dagger.module.TestAuthIteratorModule;
import com.magorasystems.mafmodules.dagger.module.TestAuthNetworkModule;
import com.magorasystems.mafmodules.dagger.module.TestAuthPresenterModule;
import com.magorasystems.mafmodules.dagger.module.TestAuthProviderModule;
import com.magorasystems.mafmodules.dagger.scope.ApplicationScope;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
@dagger.Component(dependencies = CommonModuleComponent.class,
                  modules = {TestAuthNetworkModule.class, TestAuthProviderModule.class,
                          TestAuthPresenterModule.class, TestAuthIteratorModule.class})
@ApplicationScope
public interface TestSampleComponent extends SampleComponent {

    void inject(BaseTest test);

    void inject(DaggerTest test);

    void inject(NetworkTest test);

    void inject(ProvidersTest test);

    void inject(InteractorTest test);
}
