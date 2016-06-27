package com.magorasystems.mafmodules.utils;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.magorasystems.mafmodules.authmodule.performance.AuthViewModel;
import com.magorasystems.mafmodules.common.utils.MiscUtils;
import com.magorasystems.mafmodules.model.UserProfile;
import com.magorasystems.mafmodules.network.config.SimpleTokenConfig;
import com.magorasystems.mafmodules.network.response.ProfileSuccessResponse;
import com.magorasystems.mafmodules.protocolapi.auth.response.SimpleStringAuthSuccessResponse;
import com.magorasystems.mafmodules.protocolapi.auth.response.StringAuthResponseData;
import com.magorasystems.protocolapi.auth.dto.request.AuthRequest;
import com.magorasystems.protocolapi.auth.dto.request.SimpleAuthMeta;
import com.magorasystems.protocolapi.auth.dto.request.SimpleAuthRequest;
import com.magorasystems.protocolapi.auth.dto.response.StringAuthInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

import io.bloco.faker.Faker;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class JsonStub {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonStub.class);

    private JsonStub() {

    }

    public static <T> T create(String json, Class<T> clazz) {
        final Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .create();
        return gson.fromJson(json, clazz);
    }

    public static <T> String create(T t) {
        final Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .create();
        return gson.toJson(t);
    }

    public static AuthRequest generateMetaAuthRequest() {
        final Faker faker = new Faker(Locale.US.getLanguage());
        final SimpleAuthMeta authMeta = new SimpleAuthMeta(
                MiscUtils.getHardwareId(), faker.app.version(),
                faker.internet.deviceToken());
        final SimpleAuthRequest request = new SimpleAuthRequest(
                faker.internet.freeEmail(), faker.internet.password(),
                authMeta);
        return generator(request);
    }

    public static SimpleTokenConfig generateTokenConfig() {
        final Faker faker = new Faker(Locale.US.getLanguage());
        return new SimpleTokenConfig(faker.number.number(20), faker.number.number(16));
    }

    public static SimpleStringAuthSuccessResponse generateAuthSuccessResponse() {
        final Faker faker = new Faker(Locale.US.getLanguage());
        final StringAuthInfo info = new StringAuthInfo(faker.name.name(), faker.number.number(7));
        final StringAuthResponseData responseData = new StringAuthResponseData(faker.number.number(20),
                faker.date.forward(14).getTime(), faker.number.number(16), info);
        return generator(new SimpleStringAuthSuccessResponse("sample.code.success", responseData));
    }

    public static ProfileSuccessResponse generateProfileSuccessResponse() {
        final Faker faker = new Faker(Locale.US.getLanguage());
        final UserProfile profile = UserProfile.create(faker.number.positive(1L, 10000L),
                faker.number.number(10), faker.name.name(), faker.name.firstName(),
                faker.name.lastName(), faker.phoneNumber.phoneNumber());
        return generator(new ProfileSuccessResponse(profile));
    }

    public static AuthViewModel generateAuthViewModel() {
        final Faker faker = new Faker(Locale.US.getLanguage());
        final AuthViewModel viewModel = new AuthViewModel.Builder()
                .login(faker.internet.email())
                .password(faker.internet.password())
                .build();
        LOGGER.debug("authViewModel {} ", viewModel);
        return viewModel;
    }

    public static <T> T generator(T response) {
        LOGGER.debug("class {} data {} ", response.getClass().getSimpleName(), create(response));
        return response;
    }

}
