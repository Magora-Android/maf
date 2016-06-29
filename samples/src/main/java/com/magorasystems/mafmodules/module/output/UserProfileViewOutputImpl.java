package com.magorasystems.mafmodules.module.output;

import com.magorasystems.mafmodules.common.module.output.AbstractViewOutput;
import com.magorasystems.mafmodules.model.UserProfile;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class UserProfileViewOutputImpl extends AbstractViewOutput<UserProfile> implements UserProfileViewOutput {

    public UserProfileViewOutputImpl(UserProfile model) {
        super(model);
    }
}
