package com.magorasystems.mafmodules.interactor.impl;

import android.content.Context;

import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.common.utils.component.HasComponent;
import com.magorasystems.mafmodules.dagger.component.ProfileComponent;
import com.magorasystems.mafmodules.interactor.GenericProfileInteractor;
import com.magorasystems.mafmodules.model.UserProfile;
import com.magorasystems.mafmodules.provider.SimpleRestProfileProvider;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SimpleProfileInteractorImpl extends GenericProfileInteractor<UserProfile>
        implements SimpleProfileInteractor {

    private SimpleRestProfileProvider profileProvider;

    public SimpleProfileInteractorImpl(Context context, SchedulersUtils.CoreScheduler scheduler, SimpleRestProfileProvider profileProvider) {
        super(scheduler);
        this.profileProvider = profileProvider;
        inject((ProfileComponent) ((HasComponent<?>) context).getComponent(ProfileComponent.class.getSimpleName()));
    }

    @Override
    protected SimpleRestProfileProvider getProfileProvider() {
        return profileProvider;
    }

    @Override
    public void inject(ProfileComponent profileComponent) {
        profileComponent.inject(this);
    }
}
