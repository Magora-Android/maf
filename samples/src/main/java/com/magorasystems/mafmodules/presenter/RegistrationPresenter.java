package com.magorasystems.mafmodules.presenter;

import com.magorasystems.mafmodules.authmodule.view.AuthLceView;
import com.magorasystems.mafmodules.common.module.output.ViewOutput;
import com.magorasystems.mafmodules.common.mvp.presenter.BaseLifecyclePresenter;
import com.magorasystems.mafmodules.router.RegistrationRouter;
import com.magorasystems.protocolapi.auth.dto.response.AuthInfo;

import java.io.Serializable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface RegistrationPresenter<ID extends Serializable, M extends AuthInfo<ID>,
        V extends AuthLceView<ID, M>, R extends RegistrationRouter<M>, VO extends ViewOutput<? extends M>> extends BaseLifecyclePresenter<V, R, M, VO> {
}
