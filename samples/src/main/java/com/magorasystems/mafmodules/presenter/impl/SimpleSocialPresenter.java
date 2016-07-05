package com.magorasystems.mafmodules.presenter.impl;

import com.magorasystems.mafmodules.authmodule.module.outpit.AuthViewOutput;
import com.magorasystems.mafmodules.authmodule.view.impl.StringAuthView;
import com.magorasystems.mafmodules.presenter.SocialPresenter;
import com.magorasystems.mafmodules.router.SocialRouter;
import com.magorasystems.protocolapi.auth.dto.response.StringAuthInfo;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface SimpleSocialPresenter extends SocialPresenter<String, StringAuthInfo, StringAuthView, SocialRouter, AuthViewOutput> {

}
