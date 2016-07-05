package com.magorasystems.mafmodules.module;

import com.magorasystems.mafmodules.authmodule.module.outpit.AuthViewOutput;
import com.magorasystems.mafmodules.module.input.SimpleSocialViewInput;
import com.magorasystems.mafmodules.router.SocialRouter;
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
