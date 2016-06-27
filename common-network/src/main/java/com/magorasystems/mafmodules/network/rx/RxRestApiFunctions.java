package com.magorasystems.mafmodules.network.rx;

import com.magorasystems.mafmodules.network.exception.NetworkErrorExceptionFactory;
import com.magorasystems.mafmodules.network.manager.NetworkConnectionManager;
import com.magorasystems.protocolapi.dto.response.SuccessResponse;

import java.util.concurrent.TimeUnit;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class RxRestApiFunctions {

    public static <R> Func1<? super Observable<? extends Throwable>, ? extends Observable<?>> networkNoAvailableRetry(
            final Observable<R> toBeResumed, final NetworkConnectionManager networkConnectionManager) {
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
                                    return Observable.error(NetworkErrorExceptionFactory.fromHttpException((HttpException) throwable));
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

    public static <S extends SuccessResponse<R>, R> Func1<S, Observable<R>> responseDataMapping() {
        return new Func1<S, Observable<R>>() {
            @Override
            public Observable<R> call(final S response) {
                return Observable.create(new Observable.OnSubscribe<R>() {
                    @Override
                    public void call(Subscriber<? super R> subscriber) {
                        if (subscriber.isUnsubscribed()) {
                            subscriber.onCompleted();
                            return;
                        }
                        subscriber.onNext(response.getData());
                        subscriber.onCompleted();
                    }
                });
            }
        };
    }
}
