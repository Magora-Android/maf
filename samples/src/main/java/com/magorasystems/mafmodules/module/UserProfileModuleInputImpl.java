package com.magorasystems.mafmodules.module;

import com.magorasystems.mafmodules.common.module.impl.AbstractModuleInput;
import com.magorasystems.mafmodules.model.UserProfile;
import com.magorasystems.mafmodules.module.input.impl.UserProfileViewInput;
import com.magorasystems.mafmodules.router.ProfileRouter;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class UserProfileModuleInputImpl extends AbstractModuleInput<UserProfileViewInput, ProfileRouter<UserProfile>> implements UserProfileModuleInput {

    public UserProfileModuleInputImpl(UserProfileViewInput viewInput, ProfileRouter<UserProfile> router) {
        super(viewInput, router);
    }
}
