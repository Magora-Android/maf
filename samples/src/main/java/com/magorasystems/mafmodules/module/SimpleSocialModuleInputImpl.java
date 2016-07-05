package com.magorasystems.mafmodules.module;

import com.magorasystems.mafmodules.common.module.impl.AbstractModuleInput;
import com.magorasystems.mafmodules.module.input.SimpleSocialViewInput;
import com.magorasystems.mafmodules.router.SocialRouter;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SimpleSocialModuleInputImpl extends AbstractModuleInput<SimpleSocialViewInput, SocialRouter> implements SimpleSocialModuleInput{

    public SimpleSocialModuleInputImpl(SimpleSocialViewInput viewInput, SocialRouter router) {
        super(viewInput, router);
    }
}
