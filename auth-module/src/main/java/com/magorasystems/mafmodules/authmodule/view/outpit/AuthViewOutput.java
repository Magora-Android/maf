package com.magorasystems.mafmodules.authmodule.view.outpit;

import com.magorasystems.protocolapi.auth.dto.response.StringAuthInfo;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class AuthViewOutput implements ViewOutput<StringAuthInfo> {

    private StringAuthInfo model;

    public AuthViewOutput(StringAuthInfo model) {
        this.model = model;
    }

    @Override
    public StringAuthInfo getModel() {
        return model;
    }
}
