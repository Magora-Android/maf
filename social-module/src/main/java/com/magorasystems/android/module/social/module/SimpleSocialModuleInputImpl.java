package com.magorasystems.android.module.social.module;

import com.magorasystems.mafmodules.common.module.impl.AbstractModuleInput;
import com.magorasystems.android.module.social.module.input.SimpleSocialViewInput;
import com.magorasystems.android.module.social.router.SocialRouter;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SimpleSocialModuleInputImpl extends AbstractModuleInput<SimpleSocialViewInput, SocialRouter<String>> implements SimpleSocialModuleInput{

    public SimpleSocialModuleInputImpl(SimpleSocialViewInput viewInput, SocialRouter<String> router) {
        super(viewInput, router);
    }
}
