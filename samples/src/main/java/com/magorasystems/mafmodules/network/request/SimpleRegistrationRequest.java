package com.magorasystems.mafmodules.network.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.magorasystems.mafmodules.model.RegistrationMeta;
import com.magorasystems.mafmodules.protocolapi.auth.request.AbstractRegistrationRequest;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SimpleRegistrationRequest extends AbstractRegistrationRequest<RegistrationMeta> {

    public SimpleRegistrationRequest(@JsonProperty("login") String login,
                                     @JsonProperty("password") String password,
                                     @JsonProperty("meta") RegistrationMeta meta) {
        super(login, password, meta);
    }

    @Override
    public String toString() {
        return "SimpleRegistrationRequest{" +
                "login=" + getLogin() +
                ", meta=" + getMeta() +
                ", password is set " + (getPassword() != null) +
                "}";
    }
}
