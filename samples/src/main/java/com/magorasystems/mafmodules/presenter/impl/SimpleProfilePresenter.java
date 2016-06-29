package com.magorasystems.mafmodules.presenter.impl;

import com.magorasystems.mafmodules.model.UserProfile;
import com.magorasystems.mafmodules.presenter.ProfilePresenter;
import com.magorasystems.mafmodules.router.ProfileRouter;
import com.magorasystems.mafmodules.view.impl.UserProfileLceView;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface SimpleProfilePresenter extends ProfilePresenter<UserProfile, UserProfileLceView, ProfileRouter<UserProfile>> {
}
