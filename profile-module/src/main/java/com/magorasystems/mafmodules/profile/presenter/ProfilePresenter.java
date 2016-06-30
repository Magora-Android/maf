package com.magorasystems.mafmodules.profile.presenter;

import com.magorasystems.mafmodules.common.mvp.presenter.BaseLifecyclePresenter;
import com.magorasystems.mafmodules.profile.router.ProfileRouter;
import com.magorasystems.mafmodules.profile.view.ProfileView;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface ProfilePresenter<M, V extends ProfileView<? super M>, R extends ProfileRouter<? super M>>
        extends BaseLifecyclePresenter<V, R, M> {

    void takeMyProfile();

    void editProfile(M model);
}
