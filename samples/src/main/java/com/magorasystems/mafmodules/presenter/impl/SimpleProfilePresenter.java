package com.magorasystems.mafmodules.presenter.impl;

import com.magorasystems.mafmodules.model.UserProfile;
import com.magorasystems.mafmodules.module.output.UserProfileViewOutput;
import com.magorasystems.mafmodules.profile.presenter.ProfilePresenter;
import com.magorasystems.mafmodules.profile.router.ProfileRouter;
import com.magorasystems.mafmodules.view.impl.UserProfileLceView;

import rx.Observable;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface SimpleProfilePresenter extends ProfilePresenter<UserProfile, UserProfileLceView, ProfileRouter<UserProfile>> {

    Observable<UserProfileViewOutput> output();
}

