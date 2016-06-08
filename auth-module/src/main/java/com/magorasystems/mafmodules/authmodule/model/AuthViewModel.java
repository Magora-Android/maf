package com.magorasystems.mafmodules.authmodule.model;

import com.magorasystems.mafmodules.common.mvp.model.BaseViewModel;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S. Bolkonsky
 */
public class AuthViewModel implements BaseViewModel {

    private final String login;
    private final String password;

    private AuthViewModel(Builder builder) {
        login = builder.login;
        password = builder.password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    /**
     * {@code AuthViewModel} builder static inner class.
     */
    public static final class Builder {
        private String login;
        private String password;

        public Builder() {
        }

        /**
         * Sets the {@code login} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code login} to set
         * @return a reference to this Builder
         */
        public Builder login(String val) {
            login = val;
            return this;
        }

        /**
         * Sets the {@code password} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code password} to set
         * @return a reference to this Builder
         */
        public Builder password(String val) {
            password = val;
            return this;
        }

        /**
         * Returns a {@code AuthViewModel} built from the parameters previously set.
         *
         * @return a {@code AuthViewModel} built with parameters of this {@code AuthViewModel.Builder}
         */
        public AuthViewModel build() {
            return new AuthViewModel(this);
        }
    }
}
