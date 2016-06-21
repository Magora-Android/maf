package com.magorasystems.mafmodules.authmodule.router;

import com.magorasystems.mafmodules.common.mvp.router.BaseRouter;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface AuthRouter extends BaseRouter {

    void onAfterAuth();

    void onRecoverPassword();

    void onShowError(Throwable throwable);
}
