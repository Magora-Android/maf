package com.magorasystems.mafmodules.network;

import com.fernandocejas.frodo.annotation.RxLogObservable;
import com.magorasystems.mafmodules.network.response.ProfileSuccessResponse;
import com.magorasystems.mafmodules.utils.JsonStub;
import com.magorasystems.protocolapi.dto.response.HttpCodes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class MockProfileRestClient implements ProfileApiClient {

    private final static Logger LOGGER = LoggerFactory.getLogger(MockRefreshTokenRestClient.class);

    private int useCase;

    public MockProfileRestClient(int useCase) {
        this.useCase = useCase;
    }

    @Override
    @RxLogObservable
    public Observable<ProfileSuccessResponse> getMyProfile() {
        LOGGER.debug("use case {} ", useCase);
        switch (useCase) {
            case 0:
                //useCase = 1;
                final ProfileSuccessResponse response = JsonStub.generateProfileSuccessResponse();
                return Observable.just(response).delay(1000L, TimeUnit.MILLISECONDS);
            case 1:
                useCase = 0;
                return Observable.error(new HttpException(Response.error(HttpCodes.HTTP_SECURITY_FORBIDDEN,
                        ResponseBody.create(MediaType.parse("application/json"), JsonStub.generateTokenErrorResponse()))));
            case 2:
                return Observable.error(new RuntimeException("unknown exception"));
            default:
                return Observable.empty();
        }

    }
}
