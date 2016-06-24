package com.magorasystems.mafmodules.common.ui.fragment;

import com.magorasystems.mafmodules.common.module.base.ModuleInput;
import com.magorasystems.mafmodules.common.module.base.ModulePresenter;
import com.magorasystems.mafmodules.common.module.input.ViewInput;
import com.magorasystems.mafmodules.common.module.output.ViewOutput;
import com.magorasystems.mafmodules.common.mvp.router.BaseRouter;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class GenericModuleFragment extends BaseFragmentImpl implements CommonModuleFragment {

    public GenericModuleFragment() {
        super();
    }

    protected abstract ModulePresenter<?, ? extends BaseRouter,
            ? extends ViewInput<?,?>, ? extends ViewOutput<?>,? extends ModuleInput<?,?>> getModulePresenter();
}
