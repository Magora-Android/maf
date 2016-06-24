package com.magorasystems.mafmodules.ui.fragment;

import android.view.View;

import com.magorasystems.mafmodules.R;
import com.magorasystems.mafmodules.common.module.base.ModuleInput;
import com.magorasystems.mafmodules.common.module.base.ModulePresenter;
import com.magorasystems.mafmodules.common.module.input.ViewInput;
import com.magorasystems.mafmodules.common.module.output.ViewOutput;
import com.magorasystems.mafmodules.common.mvp.router.BaseRouter;
import com.magorasystems.mafmodules.common.ui.fragment.GenericModuleFragment;

import butterknife.BindView;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class ShowProfileFragment extends GenericModuleFragment {

    @BindView(R.id.progress_view)
    protected View progressView;

    @Override
    protected ModulePresenter<?, ? extends BaseRouter, ? extends ViewInput<?, ?>, ? extends ViewOutput<?>, ? extends ModuleInput<?, ?>> getModulePresenter() {
        return null;
    }

    @Override
    protected int getResourceLayout() {
        return R.layout.fragment_show_my_profile;
    }

    @Override
    protected View getProgressView() {
        return progressView;
    }

    @Override
    public String getTitle() {
        return "Valentin S. Bolkonsky";
    }

    @Override
    public void detachView() {

    }

    @Override
    public void showError(Throwable e) {

    }
}
