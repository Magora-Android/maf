package com.magorasystems.mafmodules.profile.router;

import com.magorasystems.mafmodules.common.mvp.router.BaseRouter;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface ProfileRouter<P> extends BaseRouter {

    void onEditProfile(P model);

    void onUpdateProfile(P model);
}
