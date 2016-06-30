package com.magorasystems.mafmodules.module;

import com.magorasystems.mafmodules.model.UserProfile;
import com.magorasystems.mafmodules.module.input.impl.UserProfileViewInput;
import com.magorasystems.mafmodules.module.output.UserProfileViewOutput;
import com.magorasystems.mafmodules.profile.module.ProfilePresenterModule;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface UserProfilePresenterModule extends ProfilePresenterModule<UserProfile, UserProfileViewInput,
        UserProfileViewOutput,
        UserProfileModuleInput> {
}
