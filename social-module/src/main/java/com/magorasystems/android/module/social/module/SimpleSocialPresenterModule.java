package com.magorasystems.android.module.social.module;

import com.magorasystems.mafmodules.authmodule.module.outpit.AuthViewOutput;
import com.magorasystems.android.module.social.module.input.SimpleSocialViewInput;
import com.magorasystems.android.module.social.router.SocialRouter;
import com.magorasystems.protocolapi.auth.dto.response.StringAuthInfo;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface SimpleSocialPresenterModule
        extends SocialPresenterModule<String, StringAuthInfo,
        SocialRouter<String>, SimpleSocialViewInput,
        SimpleSocialModuleInput, AuthViewOutput> {
}
