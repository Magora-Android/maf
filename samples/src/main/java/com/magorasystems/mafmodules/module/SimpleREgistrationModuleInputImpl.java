package com.magorasystems.mafmodules.module;

import com.magorasystems.mafmodules.common.module.impl.AbstractModuleInput;
import com.magorasystems.mafmodules.module.input.SimpleRegistrationViewInput;
import com.magorasystems.mafmodules.router.SimpleRegistrationRouter;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SimpleRegistrationModuleInputImpl extends AbstractModuleInput<SimpleRegistrationViewInput,
        SimpleRegistrationRouter> implements SimpleRegistrationModuleInput {

    public SimpleRegistrationModuleInputImpl(SimpleRegistrationViewInput viewInput, SimpleRegistrationRouter router) {
        super(viewInput, router);
    }
}
