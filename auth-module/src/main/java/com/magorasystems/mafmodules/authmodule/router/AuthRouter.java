package com.magorasystems.mafmodules.authmodule.router;

import com.magorasystems.mafmodules.common.mvp.router.BaseRouter;
import com.magorasystems.protocolapi.auth.dto.response.AuthInfo;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface AuthRouter extends BaseRouter {

    void onAfterAuth(AuthInfo<?> authInfo);

    void onNotAuth();

    void onRecoverPassword();
}
