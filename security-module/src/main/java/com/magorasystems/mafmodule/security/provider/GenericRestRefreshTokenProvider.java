package com.magorasystems.mafmodule.security.provider;

import com.fernandocejas.frodo.annotation.RxLogObservable;
import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.network.config.SimpleTokenConfig;
import com.magorasystems.mafmodules.network.config.TokenConfig;
import com.magorasystems.mafmodules.network.exception.NetworkErrorException;
import com.magorasystems.mafmodules.network.exception.NetworkErrorExceptionFactory;
import com.magorasystems.mafmodules.network.manager.NetworkConnectionManager;
import com.magorasystems.mafmodules.network.rx.RxRestApiFunctions;
import com.magorasystems.mafmodules.network.store.ApiTokenStorable;
import com.magorasystems.protocolapi.auth.dto.response.AuthResponseData;
import com.magorasystems.protocolapi.dto.response.SuccessResponse;

import java.util.concurrent.TimeUnit;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.functions.Func1;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class GenericRestRefreshTokenProvider<W, COMPONENT, TOKEN extends TokenConfig, R, A>
        extends GenericRestTokenProvider<W, COMPONENT, TOKEN> implements BaseRefreshTokenProvider<A> {

    protected R refreshTokenApiClient;

    /**
     * @param component             - dagger component
     * @param scheduler             - background worker
     * @param restApiClientWrapper  - wrapper for profile api
     * @param refreshTokenApiClient - client for refresh token
     * @param tokenStorage          - storage for tokens
     */
    public GenericRestRefreshTokenProvider(COMPONENT component, SchedulersUtils.CoreScheduler scheduler,
                                           W restApiClientWrapper,
                                           R refreshTokenApiClient,
                                           ApiTokenStorable<TOKEN> tokenStorage) {
        super(component, scheduler, restApiClientWrapper, tokenStorage);
        this.refreshTokenApiClient = refreshTokenApiClient;
    }

    protected <RESULT, RESPONSE extends AuthResponseData<?>> Func1<? super Observable<? extends Throwable>, ? extends Observable<?>> retryAndRefreshTokenFunc(
            final Observable<RESULT> toBeResumed, final Observable<RESPONSE> refresher, final NetworkConnectionManager networkConnectionManager) {
        return new Func1<Observable<? extends Throwable>, Observable<?>>() {

            private int retriesCount = 0;

            @Override
            @RxLogObservable
            public Observable<?> call(Observable<? extends Throwable> attempts) {
                return attempts
                        .flatMap(throwable -> {
                            if (++retriesCount <= networkConnectionManager.getCount()) {
                                if (!networkConnectionManager.isActiveInternetConnection()) {
                                    return Observable.timer(networkConnectionManager.getDelay(),
                                            TimeUnit.MILLISECONDS);
                                }
                                if (throwable instanceof HttpException) {
                                    int code = ((HttpException) throwable).code();
                                    //if error in business-logic
                                    if (code / 100 == 4) {
                                        final NetworkErrorException networkErrorException = NetworkErrorExceptionFactory.fromHttpException((HttpException) throwable);
                                        if (networkErrorException.isTokenExpired()) {
                                            return refresher.flatMap(r -> {
                                                final TOKEN token = getTokenConfig(SimpleTokenConfig.HEADER_FIELD_NAME);
                                                token.setAccessToken(r.getAccessToken());
                                                token.setRefreshToken(r.getRefreshToken());
                                                saveToken(SimpleTokenConfig.HEADER_FIELD_NAME, token);
                                                return toBeResumed;
                                            });
                                        } else {
                                            return Observable.error(networkErrorException);
                                        }
                                    }
                                }
                                return Observable.timer(networkConnectionManager.getDelay(),
                                        TimeUnit.MILLISECONDS);
                            }
                            return Observable.error(throwable);

                        });
            }
        };
    }

    protected final <RESPONSE extends SuccessResponse<REF>, AUTH extends AuthResponseData<?>, REF> Observable.Transformer<RESPONSE, REF> commonTransformer(final Observable<RESPONSE> toBeResumed,

                                                                                                                                                           final Observable<AUTH> refresher) {
        return observable -> observable
                .onBackpressureDrop()
                .subscribeOn(scheduler.backgroundThread())
                .retryWhen(retryAndRefreshTokenFunc(toBeResumed, refresher, networkConnectionManager))
                .flatMap(RxRestApiFunctions.responseDataMapping());
    }
}
