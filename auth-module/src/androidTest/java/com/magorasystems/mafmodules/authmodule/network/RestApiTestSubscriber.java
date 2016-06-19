package com.magorasystems.mafmodules.authmodule.network;

import com.magorasystems.mafmodules.network.exception.NetworkErrorException;
import com.magorasystems.mafmodules.network.exception.NetworkErrorExceptionFactory;

import retrofit2.adapter.rxjava.HttpException;
import rx.observers.TestSubscriber;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S. Bolkonsky
 */
public class RestApiTestSubscriber<T> extends TestSubscriber<T> {

    @Override
    public void onError(Throwable e) {
        if (e instanceof HttpException) {
            try {
                super.onError(NetworkErrorExceptionFactory.fromHttpException((HttpException) e));
                return;
            } catch (NetworkErrorException ex) {
                super.onError(ex);
            }
        }
        super.onError(e);
    }
}
