package com.magorasystems.mafmodules.network.provider;

import com.google.common.base.Function;
import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.common.utils.component.HasComponent;
import com.magorasystems.mafmodules.common.utils.component.Injectable;
import com.magorasystems.mafmodules.network.manager.NetworkConnectionManager;
import com.magorasystems.mafmodules.network.rx.RxRestApiFunctions;
import com.magorasystems.protocolapi.dto.response.SuccessResponse;

import javax.inject.Inject;

import rx.Observable;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S. Bolkonsky
 */
public abstract class RestBaseDataProvider<T, COMPONENT> implements Injectable<COMPONENT> {

    protected final T restApiClientWrapper;

    protected final SchedulersUtils.CoreScheduler scheduler;

    @Inject
    protected NetworkConnectionManager networkConnectionManager;

    public RestBaseDataProvider(final HasComponent<COMPONENT> hasComponent, final SchedulersUtils.CoreScheduler scheduler, T restApiClientWrapper) {
        inject(hasComponent);
        this.scheduler = scheduler;
        this.restApiClientWrapper = restApiClientWrapper;
    }

    protected <M, F, T extends SuccessResponse<F>> Observable.Transformer<T, M> converter(Function<F, M> mapper) {
        return observable -> observable.onBackpressureDrop().subscribeOn(scheduler.backgroundThread())
                .retryWhen(RxRestApiFunctions.networkNoAvailableRetry(observable, networkConnectionManager))
                .map(SuccessResponse::getData)
                .map(data -> data != null ? mapper.apply(data) : null);
    }

    protected <F, T extends SuccessResponse<F>> Observable.Transformer<T, F> converter() {
        return observable -> observable.onBackpressureDrop().subscribeOn(scheduler.backgroundThread())
                .retryWhen(RxRestApiFunctions.networkNoAvailableRetry(observable, networkConnectionManager))
                .map(SuccessResponse::getData);

    }
}
