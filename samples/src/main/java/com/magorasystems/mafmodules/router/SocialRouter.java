package com.magorasystems.mafmodules.router;


import com.magorasystems.mafmodules.common.mvp.router.BaseRouter;
import com.magorasystems.protocolapi.auth.dto.response.AuthInfo;

import java.io.Serializable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface SocialRouter<I extends Serializable> extends BaseRouter {

    void onAfterSocialAuth(AuthInfo<I> authInfo);
}
