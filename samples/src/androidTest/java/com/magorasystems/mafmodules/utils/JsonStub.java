package com.magorasystems.mafmodules.utils;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.magorasystems.mafmodules.protocolapi.auth.response.SimpleStringAuthSuccessResponse;
import com.magorasystems.mafmodules.protocolapi.auth.response.StringAuthResponseData;
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

    public static SimpleStringAuthSuccessResponse generateAuthSuccessResponse() {
        final Faker faker = new Faker(Locale.US.getCountry());
        final StringAuthInfo info = new StringAuthInfo(faker.name.name(), faker.number.number(7));
        final StringAuthResponseData responseData = new StringAuthResponseData(faker.number.number(20),
                faker.date.forward(14).getTime(), faker.number.number(16), info);
        return generator(new SimpleStringAuthSuccessResponse("sample.code.success", responseData));
    }

    public static <T> T generator(T response) {
        LOGGER.debug("class {} data {} ", response.getClass().getSimpleName(), create(response));
        return response;
    }

}