package com.magorasystems.android.module.social.model.model;

import com.mgrmobi.sdk.social.android.model.SocialUser;
import com.mgrmobi.sdk.social.base.SocialType;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class RxSocialProfileResult {

    private final SocialType type;
    private final SocialUser socialUser;

    private RxSocialProfileResult(Builder builder) {
        type = builder.type;
        socialUser = builder.socialUser;
    }

    public SocialType getType() {
        return type;
    }

    public SocialUser getSocialUser() {
        return socialUser;
    }

    @Override
    public String toString() {
        return "RxSocialProfileResult{" +
                "type=" + type +
                ", socialUser=" + socialUser +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * {@code RxSocialProfileResult} builder static inner class.
     */
    public static final class Builder {
        private SocialType type;
        private SocialUser socialUser;

        public Builder() {
        }

        /**
         * Sets the {@code type} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code type} to set
         * @return a reference to this Builder
         */
        public Builder type(SocialType val) {
            type = val;
            return this;
        }

        /**
         * Sets the {@code socialUser} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code socialUser} to set
         * @return a reference to this Builder
         */
        public Builder socialUser(SocialUser val) {
            socialUser = val;
            return this;
        }

        /**
         * Returns a {@code RxSocialProfileResult} built from the parameters previously set.
         *
         * @return a {@code RxSocialProfileResult} built with parameters of this {@code RxSocialProfileResult.Builder}
         */
        public RxSocialProfileResult build() {
            return new RxSocialProfileResult(this);
        }
    }
}
