package com.magorasystems.mafmodules.router;

import com.magorasystems.protocolapi.auth.dto.response.StringAuthInfo;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SimpleRegistrationRouter implements RegistrationRouter<StringAuthInfo> {

    @Override
    public void onAfterRegistration(StringAuthInfo info) {

    }

    @Override
    public void onGoToAuthorization() {

    }

    @Override
    public void onBack() {

    }

    @Override
    public void onShowError(Throwable throwable) {

    }
}
