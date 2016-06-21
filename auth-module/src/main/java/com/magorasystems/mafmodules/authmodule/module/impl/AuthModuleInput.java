package com.magorasystems.mafmodules.authmodule.module.impl;

import com.magorasystems.mafmodules.authmodule.router.AuthRouter;
import com.magorasystems.mafmodules.authmodule.view.input.StringAuthViewInput;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class AuthModuleInput extends AbstractModuleInput<StringAuthViewInput, AuthRouter> {

    public AuthModuleInput(StringAuthViewInput viewInput, AuthRouter router) {
        super(viewInput, router);
    }
}
