package com.magorasystems.mafmodule.security.provider;

import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.network.config.SimpleTokenConfig;
import com.magorasystems.mafmodules.network.config.TokenConfig;
import com.magorasystems.mafmodules.network.exception.NetworkErrorException;
import com.magorasystems.mafmodules.network.exception.NetworkErrorExceptionFactory;
import com.magorasystems.mafmodules.network.manager.NetworkConnectionManager;
import com.magorasystems.mafmodules.network.provider.RestBaseDataProvider;
import com.magorasystems.mafmodules.network.rx.RxRestApiFunctions;
import com.magorasystems.mafmodules.network.store.ApiTokenStorable;
import com.magorasystems.protocolapi.auth.dto.response.AuthResponseData;
import com.magorasystems.protocolapi.dto.response.SuccessResponse;

import java.util.concurrent.TimeUnit;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class GenericRestRefreshTokenProvider<W, COMPONENT, TOKEN extends TokenConfig, S extends ApiTokenStorable<TOKEN>, R, A> extends RestBaseDataProvider<W, COMPONENT> implements BaseRefreshTokenProvider<A> {

    protected R refreshTokenApiClient;

    protected S tokenStorage;

    public GenericRestRefreshTokenProvider(COMPONENT component, SchedulersUtils.CoreScheduler scheduler,
                                           W restApiClientWrapper,
                                           R refreshTokenApiClient,
                                           S tokenStorage) {
        super(component, scheduler, restApiClientWrapper);
        this.refreshTokenApiClient = refreshTokenApiClient;
        this.tokenStorage = tokenStorage;
    }

    protected abstract TOKEN getTokenConfig();

    protected  <RESULT, RESPONSE extends AuthResponseData<?>> Func1<? super Observable<? extends Throwable>, ? extends Observable<?>> retryAndRefreshTokenFunc(
            final Observable<RESULT> toBeResumed, final Observable<RESPONSE> refresher, final NetworkConnectionManager networkConnectionManager) {
        return new Func1<Observable<? extends Throwable>, Observable<?>>() {

            private int retriesCount = 0;

            @Override
            public Observable<?> call(Observable<? extends Throwable> attempts) {
                return attempts
                        .flatMap(throwable -> {
                            if (throwable instanceof HttpException) {
                                int code = ((HttpException) throwable).code();
                                //if error in business-logic
                                if (code / 100 == 4) {
                                    final NetworkErrorException networkErrorException = NetworkErrorExceptionFactory.fromHttpException((HttpException) throwable);
                                    if (networkErrorException.isTokenExpired()) {
                                        return refresher.flatMap((Func1<RESPONSE, Observable<?>>) r -> {
                                            final TOKEN token = getTokenConfig();
                                            token.setAccessToken(r.getAccessToken());
                                            token.setRefreshToken(r.getRefreshToken());
                                            tokenStorage.storeObject(SimpleTokenConfig.HEADER_FIELD_NAME, token);
                                            return toBeResumed;
                                        });
                                    } else {
                                        return Observable.error(networkErrorException);
                                    }
                                }
                            }
                            if (++retriesCount <= networkConnectionManager.getCount()) {
                                if (!networkConnectionManager.isActiveInternetConnection()) {
                                    return Observable.timer(networkConnectionManager.getDelay(),
                                            TimeUnit.MILLISECONDS);
                                } else {
                                    return toBeResumed;
                                }
                            }
                            return Observable.error(throwable);
                        });
            }
        };
    }

    protected final <RESPONSE extends SuccessResponse<R>, A extends AuthResponseData<?>, R> Observable.Transformer<RESPONSE, R> commonTransformer(final Observable<RESPONSE> toBeResumed,
                                                                                                                                                  final Observable<A> refresher) {
        return observable -> observable.subscribeOn(Schedulers.newThread())
                .retryWhen(retryAndRefreshTokenFunc(toBeResumed, refresher, networkConnectionManager))
                .flatMap(RxRestApiFunctions.responseDataMapping());
    }
}
