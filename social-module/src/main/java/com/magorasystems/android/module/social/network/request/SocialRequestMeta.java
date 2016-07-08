package com.magorasystems.android.module.social.network.request;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SocialRequestMeta {

    private final String firstName;
    private final String lastName;
    private final String nickName;

    private SocialRequestMeta(Builder builder) {
        firstName = builder.firstName;
        lastName = builder.lastName;
        nickName = builder.nickName;
    }

    @Override
    public String toString() {
        return "SocialRequestMeta{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }

    /**
     * {@code SocialRequestMeta} builder static inner class.
     */
    public static final class Builder {
        private String firstName;
        private String lastName;
        private String nickName;

        public Builder() {
        }

        /**
         * Sets the {@code firstName} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code firstName} to set
         * @return a reference to this Builder
         */
        public Builder firstName(String val) {
            firstName = val;
            return this;
        }

        /**
         * Sets the {@code lastName} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code lastName} to set
         * @return a reference to this Builder
         */
        public Builder lastName(String val) {
            lastName = val;
            return this;
        }

        /**
         * Sets the {@code nickName} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code nickName} to set
         * @return a reference to this Builder
         */
        public Builder nickName(String val) {
            nickName = val;
            return this;
        }

        /**
         * Returns a {@code SocialRequestMeta} built from the parameters previously set.
         *
         * @return a {@code SocialRequestMeta} built with parameters of this {@code SocialRequestMeta.Builder}
         */
        public SocialRequestMeta build() {
            return new SocialRequestMeta(this);
        }
    }
}
