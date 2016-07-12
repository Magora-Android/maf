package com.magorasystems.mafmodules.router;

import com.magorasystems.mafmodules.common.mvp.router.BaseRouter;
import com.magorasystems.protocolapi.auth.dto.response.AuthInfo;

import java.io.Serializable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface RegistrationRouter<M extends AuthInfo<? extends Serializable>> extends BaseRouter {

    void onAfterRegistration(M info);

    void onGoToAuthorization();

}
