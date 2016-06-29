package com.magorasystems.mafmodules.module;

import com.magorasystems.mafmodules.common.module.base.ModuleInput;
import com.magorasystems.mafmodules.common.module.base.ModulePresenter;
import com.magorasystems.mafmodules.module.input.ProfileViewInput;
import com.magorasystems.mafmodules.module.output.ProfileViewOutput;
import com.magorasystems.mafmodules.router.ProfileRouter;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface ProfilePresenterModule<M, VI extends ProfileViewInput<M, ?, ?>, VO extends ProfileViewOutput<M>,
        I extends ModuleInput<VI, ProfileRouter<M>>>
        extends ModulePresenter<ProfileRouter<M>, VI, VO, I> {
}
