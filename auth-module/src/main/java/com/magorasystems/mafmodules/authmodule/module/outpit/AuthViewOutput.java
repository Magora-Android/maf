package com.magorasystems.mafmodules.authmodule.module.outpit;

import com.magorasystems.mafmodules.common.module.output.AbstractViewOutput;
import com.magorasystems.protocolapi.auth.dto.response.StringAuthInfo;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class AuthViewOutput extends AbstractViewOutput<StringAuthInfo> {

    public AuthViewOutput(StringAuthInfo model) {
        super(model);
    }
}
