package com.magorasystems.mafmodules.registration.presenter;

import com.magorasystems.mafmodules.common.module.output.ViewOutput;
import com.magorasystems.mafmodules.common.mvp.presenter.BaseIteratorLcePresenter;
import com.magorasystems.mafmodules.common.utils.rx.PresenterSubscriber;
import com.magorasystems.mafmodules.registration.interactor.RegistrationInteractor;
import com.magorasystems.mafmodules.registration.router.RegistrationRouter;
import com.magorasystems.mafmodules.registration.view.RegistrationLceView;
import com.magorasystems.protocolapi.auth.dto.request.AuthRequest;
import com.magorasystems.protocolapi.auth.dto.response.AuthInfo;
import com.magorasystems.widgets.model.UserViewModel;

import java.io.Serializable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class AbstractRegistrationPresenter<M extends AuthInfo<? extends Serializable>,
        I extends RegistrationInteractor<M>,
        V extends RegistrationLceView<M>, R extends RegistrationRouter<M>, VO extends ViewOutput<? extends M>>
        extends BaseIteratorLcePresenter<M, I, V, R, VO> implements RegistrationPresenter<M, V, R, VO> {

    protected AbstractRegistrationPresenter(I interactor) {
        super(interactor);
    }

    @Override
    public void registration(UserViewModel<? extends Serializable> view) {
        showProgress();
        getIteractor().executeRegistration(createAuthRequest(view), new PresenterSubscriber<>(this));
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        destroy();
    }

    @Override
    public void toAuthorization(Void v) {
        final RegistrationRouter<M> router = getRouter();
        if (router != null) {
            router.onGoToAuthorization();
        }
    }

    protected abstract AuthRequest createAuthRequest(UserViewModel<? extends Serializable> view);
}
