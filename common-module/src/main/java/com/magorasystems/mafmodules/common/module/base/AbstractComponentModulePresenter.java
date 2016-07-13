package com.magorasystems.mafmodules.common.module.base;

import android.content.Context;

import com.magorasystems.mafmodules.common.module.input.InteractiveView;
import com.magorasystems.mafmodules.common.module.input.ViewInput;
import com.magorasystems.mafmodules.common.module.output.ViewOutput;
import com.magorasystems.mafmodules.common.mvp.router.BaseRouter;
import com.magorasystems.mafmodules.common.mvp.view.BaseView;
import com.magorasystems.mafmodules.common.utils.component.HasComponent;
import com.magorasystems.mafmodules.common.utils.component.Injectable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class AbstractComponentModulePresenter<COMPONENT, R extends BaseRouter, VI extends ViewInput<? extends BaseView, ? extends InteractiveView<?>>,
        VO extends ViewOutput<?>, I extends ModuleInput<VI, R>> extends AbstractModulePresenter<R, VI, VO, I> implements ModulePresenter<R, VI, VO, I>, Injectable<COMPONENT> {


    protected final void injectComponent(Context context, Class<COMPONENT> clazz) {
        if (context instanceof HasComponent<?>) {
            final Object component = ((HasComponent<?>) context).getComponent(clazz.getSimpleName());
            if (component != null) {
                try {
                    inject(clazz.cast(component));
                } catch (ClassCastException e) {
                    throw new IllegalArgumentException("Context has not implement \"" + clazz.getSimpleName() + "\" component", e);
                }
            }
        }

    }
}
