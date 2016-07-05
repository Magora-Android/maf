package com.magorasystems.mafmodules.module.input.impl;

import com.magorasystems.mafmodules.authmodule.view.impl.StringAuthView;
import com.magorasystems.mafmodules.module.input.AbstractSocialViewInput;
import com.magorasystems.mafmodules.module.input.SimpleSocialViewInput;
import com.magorasystems.mafmodules.module.input.SocialInteractiveView;
import com.magorasystems.protocolapi.auth.dto.response.StringAuthInfo;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SimpleSocialViewInputImpl extends AbstractSocialViewInput<String, StringAuthInfo, StringAuthView, SocialInteractiveView>
        implements SimpleSocialViewInput {

    public SimpleSocialViewInputImpl(StringAuthView passiveView, SocialInteractiveView interactiveView) {
        super(passiveView, interactiveView);
    }
}
