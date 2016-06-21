package com.magorasystems.mafmodules.authmodule.module.impl;

import com.magorasystems.mafmodules.authmodule.dagger.component.AuthComponent;
import com.magorasystems.mafmodules.authmodule.module.base.ModulePresenter;
import com.magorasystems.mafmodules.authmodule.router.AuthRouter;
import com.magorasystems.mafmodules.authmodule.view.input.StringAuthViewInput;
import com.magorasystems.mafmodules.authmodule.view.outpit.AuthViewOutput;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface AuthModulePresenter extends ModulePresenter<AuthComponent, AuthRouter,
        StringAuthViewInput, AuthViewOutput, AuthModuleInput> {
}
