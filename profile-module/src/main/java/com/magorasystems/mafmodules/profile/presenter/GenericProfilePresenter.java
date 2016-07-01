package com.magorasystems.mafmodules.profile.presenter;

import com.magorasystems.mafmodules.common.mvp.presenter.BaseIteratorLcePresenter;
import com.magorasystems.mafmodules.profile.interactor.ProfileInteractor;
import com.magorasystems.mafmodules.profile.module.output.ProfileViewOutput;
import com.magorasystems.mafmodules.profile.router.ProfileRouter;
import com.magorasystems.mafmodules.profile.view.ProfileLceView;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class GenericProfilePresenter<M, V extends ProfileLceView<? super M>,
        I extends ProfileInteractor<? super M>, R extends ProfileRouter<? super M>, VO extends ProfileViewOutput<? extends M>>
        extends BaseIteratorLcePresenter<M, I, V, R, VO> implements ProfilePresenter<M, V, R, VO> {

    public GenericProfilePresenter(I interactor) {
        super(interactor);
    }

    @Override
    public void editProfile(M model) {
        final ProfileRouter<? super M> router = getRouter();
        if (router != null) {
            router.onEditProfile(model);
        }
    }

    @Override
    public void onStop() {
        getIteractor().unsubscribe();
        destroy();
    }
}
