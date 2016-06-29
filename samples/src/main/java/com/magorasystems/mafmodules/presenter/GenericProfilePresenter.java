package com.magorasystems.mafmodules.presenter;

import com.magorasystems.mafmodules.common.mvp.presenter.BaseIteratorLcePresenter;
import com.magorasystems.mafmodules.interactor.ProfileInteractor;
import com.magorasystems.mafmodules.router.ProfileRouter;
import com.magorasystems.mafmodules.view.ProfileLceView;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class GenericProfilePresenter<M, V extends ProfileLceView<? super M>,
        I extends ProfileInteractor<? super M>, R extends ProfileRouter<? super M>>
        extends BaseIteratorLcePresenter<M, I, V, R> implements ProfilePresenter<M, V, R> {

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
    }
}
