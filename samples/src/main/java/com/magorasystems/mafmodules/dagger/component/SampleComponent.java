package com.magorasystems.mafmodules.dagger.component;

import com.magorasystems.mafmodules.application.SampleApplication;
import com.magorasystems.mafmodules.authmodule.dagger.component.AuthComponent;
import com.magorasystems.mafmodules.dagger.module.SampleApplicationModule;
import com.magorasystems.mafmodules.dagger.scope.ApplicationScope;
import com.magorasystems.mafmodules.ui.activity.AuthorizationActivityImpl;
import com.magorasystems.mafmodules.ui.activity.SampleActivity;
import com.magorasystems.mafmodules.ui.fragment.AuthorizationFragmentImpl;

import dagger.Component;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */

@Component(dependencies = {AuthComponent.class}, modules = {SampleApplicationModule.class})
@ApplicationScope
public interface SampleComponent {

    AuthComponent authComponent();

    void inject(SampleApplication application);

    void inject(SampleActivity activity);

    void inject(AuthorizationFragmentImpl f);

    void inject(AuthorizationActivityImpl a);
}
