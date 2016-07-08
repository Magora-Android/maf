package com.magorasystems.android.module.social.presenter;

import com.magorasystems.android.module.social.router.SocialRouter;
import com.magorasystems.mafmodules.authmodule.module.outpit.AuthViewOutput;
import com.magorasystems.mafmodules.authmodule.view.impl.StringAuthView;
import com.magorasystems.protocolapi.auth.dto.response.StringAuthInfo;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface SimpleSocialPresenter extends SocialPresenter<String, StringAuthInfo,
        StringAuthView, SocialRouter<String>, AuthViewOutput> {

}
