package com.magorasystems.mafmodules.interactor;

import com.magorasystems.mafmodules.common.utils.component.Injectable;
import com.magorasystems.mafmodules.dagger.component.ProfileComponent;
import com.magorasystems.mafmodules.profile.interactor.ProfileInteractor;
import com.magorasystems.mafmodules.model.UserProfile;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface SimpleProfileInteractor extends ProfileInteractor<UserProfile>, Injectable<ProfileComponent> {
}
