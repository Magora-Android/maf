package com.magorasystems.android.module.social.model.model;

import com.mgrmobi.sdk.social.base.SocialType;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class RxSocialAuthResult<TOKEN> {

    private final SocialType socialType;
    private final TOKEN token;

    private RxSocialAuthResult(Builder<TOKEN> builder) {
        socialType = builder.socialType;
        token = builder.token;
    }

    public SocialType getSocialType() {
        return socialType;
    }

    public TOKEN getToken() {
        return token;
    }

    public static <TOKEN> Builder<TOKEN> builder() {
        return new Builder<>();
    }

    /**
     * {@code RxSocialAuthResult} builder static inner class.
     */
    public static final class Builder<TOKEN> {
        private SocialType socialType;
        private TOKEN token;

        public Builder() {
        }

        /**
         * Sets the {@code socialType} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code socialType} to set
         * @return a reference to this Builder
         */
        public Builder<TOKEN> socialType(SocialType val) {
            socialType = val;
            return this;
        }

        /**
         * Sets the {@code token} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code token} to set
         * @return a reference to this Builder
         */
        public Builder<TOKEN> token(TOKEN val) {
            token = val;
            return this;
        }

        /**
         * Returns a {@code RxSocialAuthResult} built from the parameters previously set.
         *
         * @return a {@code RxSocialAuthResult} built with parameters of this {@code RxSocialAuthResult.Builder}
         */
        public RxSocialAuthResult<TOKEN> build() {
            return new RxSocialAuthResult<>(this);
        }
    }
}
