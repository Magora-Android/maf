package com.magorasystems.mafmodules.module;

import com.magorasystems.mafmodules.common.module.base.ModuleInput;
import com.magorasystems.mafmodules.model.UserProfile;
import com.magorasystems.mafmodules.module.input.impl.UserProfileViewInput;
import com.magorasystems.mafmodules.router.ProfileRouter;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface UserProfileModuleInput extends ModuleInput<UserProfileViewInput, ProfileRouter<UserProfile>> {
}
