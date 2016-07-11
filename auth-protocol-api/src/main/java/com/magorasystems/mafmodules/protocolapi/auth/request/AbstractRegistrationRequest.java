package com.magorasystems.mafmodules.protocolapi.auth.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.magorasystems.protocolapi.auth.dto.request.AuthRequest;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class AbstractRegistrationRequest<M> extends AuthRequest implements RegistrationRequest<M> {

    private M meta;

    public AbstractRegistrationRequest(@JsonProperty("login") String login, @JsonProperty("password") String password, @JsonProperty("meta") M meta) {
        super(login, password);
        this.meta = meta;
    }

    @Override
    public M getMeta() {
        return meta;
    }
}
