package com.magorasystems.mafmodules.module.input.impl;

import com.magorasystems.mafmodules.model.UserProfile;
import com.magorasystems.mafmodules.profile.module.input.ProfileViewInput;
import com.magorasystems.mafmodules.view.impl.UserProfileInteractiveView;
import com.magorasystems.mafmodules.view.impl.UserProfileLceView;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class UserProfileViewInput extends ProfileViewInput<UserProfile, UserProfileLceView, UserProfileInteractiveView> {

    public UserProfileViewInput(UserProfileLceView passiveView, UserProfileInteractiveView interactiveView) {
        super(passiveView, interactiveView);
    }
}
