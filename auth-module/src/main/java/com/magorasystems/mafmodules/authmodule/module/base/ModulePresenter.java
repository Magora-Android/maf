package com.magorasystems.mafmodules.authmodule.module.base;

import com.magorasystems.mafmodules.authmodule.module.impl.AbstractModuleInput;
import com.magorasystems.mafmodules.authmodule.router.AuthRouter;
import com.magorasystems.mafmodules.authmodule.view.input.StringAuthViewInput;
import com.magorasystems.mafmodules.common.utils.component.Injectable;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface ModulePresenter<COMPONENT, I extends AbstractModuleInput<StringAuthViewInput, AuthRouter>> extends Injectable<COMPONENT> {

    void start();

    void stop();

    void input(I input);
}
