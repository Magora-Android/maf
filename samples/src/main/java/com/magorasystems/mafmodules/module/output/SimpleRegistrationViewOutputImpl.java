package com.magorasystems.mafmodules.module.output;

import com.magorasystems.protocolapi.auth.dto.response.StringAuthInfo;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SimpleRegistrationViewOutputImpl extends AbstractRegistrationViewOutput<StringAuthInfo> implements SimpleRegistrationViewOutput {

    public SimpleRegistrationViewOutputImpl(StringAuthInfo model) {
        super(model);
    }

}
