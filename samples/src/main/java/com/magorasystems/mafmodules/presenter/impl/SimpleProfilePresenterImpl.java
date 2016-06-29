package com.magorasystems.mafmodules.presenter.impl;

import android.content.Context;

import com.magorasystems.mafmodules.common.utils.component.HasComponent;
import com.magorasystems.mafmodules.common.utils.component.Injectable;
import com.magorasystems.mafmodules.common.utils.rx.PresenterSubscriber;
import com.magorasystems.mafmodules.dagger.component.ProfileComponent;
import com.magorasystems.mafmodules.interactor.impl.SimpleProfileInteractor;
import com.magorasystems.mafmodules.model.UserProfile;
import com.magorasystems.mafmodules.presenter.GenericProfilePresenter;
import com.magorasystems.mafmodules.router.ProfileRouter;
import com.magorasystems.mafmodules.view.impl.UserProfileLceView;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SimpleProfilePresenterImpl extends GenericProfilePresenter<UserProfile,
        UserProfileLceView,
        SimpleProfileInteractor,
        ProfileRouter<UserProfile>>
        implements SimpleProfilePresenter, Injectable<ProfileComponent> {


    public SimpleProfilePresenterImpl(Context context, SimpleProfileInteractor interactor) {
        super(interactor);
        inject((ProfileComponent) ((HasComponent<?>) context).getComponent(ProfileComponent.class.getSimpleName()));
    }

    @Override
    public void takeMyProfile() {
        showContent();
        getIteractor().executeMyProfile(new PresenterSubscriber<>(this));
    }

    @Override
    public void onStart() {
        takeMyProfile();
    }

    @Override
    public void inject(ProfileComponent profileComponent) {
        profileComponent.inject(this);
    }

    @Override
    public void onNext(UserProfile userProfile) {
        super.onNext(userProfile);
    }
}
