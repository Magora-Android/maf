package com.magorasystems.mafmodules.common.ui.fragment;

import android.content.Context;

import com.magorasystems.mafmodules.common.module.base.ModuleInput;
import com.magorasystems.mafmodules.common.module.base.ModulePresenter;
import com.magorasystems.mafmodules.common.module.input.ViewInput;
import com.magorasystems.mafmodules.common.module.output.ViewOutput;
import com.magorasystems.mafmodules.common.mvp.router.BaseRouter;
import com.magorasystems.mafmodules.common.utils.component.HasComponent;
import com.magorasystems.mafmodules.common.utils.component.Injectable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class GenericModuleFragment<COMPONENT> extends BaseFragmentImpl implements CommonModuleFragment,
        Injectable<COMPONENT>, BaseRouter {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericModuleFragment.class);

    protected abstract void initialization();

    public GenericModuleFragment() {
        super();
    }

    protected abstract ModulePresenter<? extends BaseRouter,
            ? extends ViewInput<?, ?>,
            ? extends ViewOutput<?>,
            ? extends ModuleInput<?, ?>> getModulePresenter();

    @Override
    public void showError(Throwable e) {
        LOGGER.error("showError ", e);
    }

    @Override
    public void onBack() {
        if (!isActivityDetached()) {
            getActivity().onBackPressed();
        }
    }

    @Override
    public void onShowError(Throwable throwable) {
        if (!isActivityDetached()) {
            LOGGER.error("something wrong ", throwable);
            showErrorDialog(throwable.getMessage(), (v, i) -> {
            });
        }
    }

    @Override
    public void detachView() {

    }

    @SuppressWarnings("unchecked")
    protected final void injectComponent(Context context, Class<COMPONENT> clazz) {
        if (context instanceof HasComponent<?>) {
            final Object component = ((HasComponent<?>) context).getComponent(clazz.getSimpleName());
            if (component != null) {
                try {
                    inject((COMPONENT) component);
                } catch (ClassCastException e) {
                    throw new IllegalArgumentException("Context has not implement \"" + clazz.getSimpleName() + "\" component");
                }
                return;
            }
        }
        throw new IllegalArgumentException("Context has not implement \"" + clazz.getSimpleName() + "\" component");
    }
}
