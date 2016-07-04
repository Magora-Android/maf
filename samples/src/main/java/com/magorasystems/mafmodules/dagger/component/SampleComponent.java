package com.magorasystems.mafmodules.dagger.component;

import com.magorasystems.mafmodules.application.SampleApplication;
import com.magorasystems.mafmodules.common.dagger.component.CommonModuleComponent;
import com.magorasystems.mafmodules.dagger.module.SampleApplicationModule;
import com.magorasystems.mafmodules.dagger.scope.ApplicationScope;
import com.magorasystems.mafmodules.ui.activity.SampleActivity;
import com.magorasystems.mafmodules.ui.activity.SocialAuthorizationActivity;
import com.magorasystems.mafmodules.ui.fragment.SocialAuthorizationFragment;

import dagger.Component;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */

@Component(dependencies = {CommonModuleComponent.class}, modules = {SampleApplicationModule.class})
@ApplicationScope
public interface SampleComponent {

    void inject(SampleApplication application);

    void inject(SampleActivity activity);

    void inject(SocialAuthorizationActivity activity);

    void inject(SocialAuthorizationFragment f);

}
