package com.magorasystems.android.module.social.network.request;

import com.google.gson.annotations.SerializedName;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SocialRequest<T> {

    @SerializedName("type")
    private final String type;
    @SerializedName("token")
    private final String token;
    @SerializedName("meta")
    private final T meta;

    private SocialRequest(Builder<T> builder) {
        type = builder.type;
        token = builder.token;
        meta = builder.meta;
    }

    @Override
    public String toString() {
        return "SocialRequest{" +
                "type='" + type + '\'' +
                ", token='" + token + '\'' +
                ", meta=" + meta +
                '}';
    }

    /**
     * {@code SocialRequest} builder static inner class.
     */
    public static final class Builder<T> {
        private String type;
        private String token;
        private T meta;

        public Builder() {
        }

        /**
         * Sets the {@code type} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code type} to set
         * @return a reference to this Builder
         */
        public Builder<T> type(String val) {
            type = val;
            return this;
        }

        /**
         * Sets the {@code token} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code token} to set
         * @return a reference to this Builder
         */
        public Builder<T> token(String val) {
            token = val;
            return this;
        }

        /**
         * Sets the {@code meta} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code meta} to set
         * @return a reference to this Builder
         */
        public Builder<T> meta(T val) {
            meta = val;
            return this;
        }

        /**
         * Returns a {@code SocialRequest} built from the parameters previously set.
         *
         * @return a {@code SocialRequest} built with parameters of this {@code SocialRequest.Builder}
         */
        public SocialRequest<T> build() {
            return new SocialRequest<>(this);
        }
    }
}
