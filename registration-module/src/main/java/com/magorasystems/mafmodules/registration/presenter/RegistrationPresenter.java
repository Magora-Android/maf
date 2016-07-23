package com.magorasystems.mafmodules.registration.presenter;

import com.magorasystems.mafmodules.common.module.output.ViewOutput;
import com.magorasystems.mafmodules.common.mvp.presenter.BaseLifecyclePresenter;
import com.magorasystems.mafmodules.registration.router.RegistrationRouter;
import com.magorasystems.mafmodules.registration.view.RegistrationLceView;
import com.magorasystems.protocolapi.auth.dto.response.AuthInfo;
import com.magorasystems.widgets.model.UserViewModel;

import java.io.Serializable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface RegistrationPresenter<M extends AuthInfo<? extends Serializable>,
        V extends RegistrationLceView<M>, R extends RegistrationRouter<M>, VO extends ViewOutput<? extends M>> extends BaseLifecyclePresenter<V, R, M, VO> {

    void registration(UserViewModel<? extends Serializable> view);

    void toAuthorization(Void v);
}
