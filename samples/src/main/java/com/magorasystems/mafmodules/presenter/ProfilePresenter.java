package com.magorasystems.mafmodules.presenter;

import com.magorasystems.mafmodules.common.mvp.presenter.BaseLifecyclePresenter;
import com.magorasystems.mafmodules.router.ProfileRouter;
import com.magorasystems.mafmodules.view.ProfileView;

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
