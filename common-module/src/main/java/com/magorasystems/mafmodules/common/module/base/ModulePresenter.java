package com.magorasystems.mafmodules.common.module.base;

import com.magorasystems.mafmodules.common.module.input.ViewInput;
import com.magorasystems.mafmodules.common.module.output.ViewOutput;
import com.magorasystems.mafmodules.common.mvp.router.BaseRouter;

import rx.Observable;
import rx.Observer;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface ModulePresenter<R extends BaseRouter, VI extends ViewInput<?, ?>, VO extends ViewOutput<?>, I extends ModuleInput<VI, R>>
        extends Observer<VO> {

    void start();

    void stop();

    void input(I input);

    void output(Observable<VO> output);

    void destroy(boolean retainInstance);
}
