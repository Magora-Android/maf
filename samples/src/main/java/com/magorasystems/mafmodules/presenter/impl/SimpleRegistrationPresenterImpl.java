package com.magorasystems.mafmodules.presenter.impl;

import com.magorasystems.mafmodules.interactor.SimpleRegistrationInteractor;
import com.magorasystems.mafmodules.model.RegistrationMeta;
import com.magorasystems.mafmodules.module.output.SimpleRegistrationViewOutput;
import com.magorasystems.mafmodules.module.output.SimpleRegistrationViewOutputImpl;
import com.magorasystems.mafmodules.network.request.SimpleRegistrationRequest;
import com.magorasystems.mafmodules.registration.presenter.AbstractRegistrationPresenter;
import com.magorasystems.mafmodules.presenter.SimpleRegistrationPresenter;
import com.magorasystems.mafmodules.registration.router.RegistrationRouter;
import com.magorasystems.mafmodules.ui.widget.model.SimpleUserPasswordViewModel;
import com.magorasystems.mafmodules.view.impl.SimpleRegistrationLceView;
import com.magorasystems.protocolapi.auth.dto.request.AuthRequest;
import com.magorasystems.protocolapi.auth.dto.response.StringAuthInfo;
import com.magorasystems.widgets.model.UserViewModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SimpleRegistrationPresenterImpl extends AbstractRegistrationPresenter<StringAuthInfo,
        SimpleRegistrationInteractor, SimpleRegistrationLceView, RegistrationRouter<StringAuthInfo>, SimpleRegistrationViewOutput>
        implements SimpleRegistrationPresenter {

    private final static Logger LOGGER = LoggerFactory.getLogger(SimpleRegistrationPresenterImpl.class);

    public SimpleRegistrationPresenterImpl(SimpleRegistrationInteractor interactor) {
        super(interactor);
    }

    @Override
    protected AuthRequest createAuthRequest(UserViewModel<? extends Serializable> view) {
        if (view instanceof SimpleUserPasswordViewModel) {
            final SimpleUserPasswordViewModel model = (SimpleUserPasswordViewModel) view;
            return new SimpleRegistrationRequest(String.valueOf(model.getEmail()), String.valueOf(model.getPassword()),
                    new RegistrationMeta(String.valueOf(model.getUserName()), String.valueOf(model.getPhone())));
        }
        LOGGER.warn("request from view \'" + view + "\' is empty");
        return null;
    }

    @Override
    protected SimpleRegistrationViewOutput newEvent(StringAuthInfo model) {
        return new SimpleRegistrationViewOutputImpl(model);
    }

    @Override
    public void toAuthorization(Void v) {
        LOGGER.debug("go to authorization");
        super.toAuthorization(v);
    }
}
