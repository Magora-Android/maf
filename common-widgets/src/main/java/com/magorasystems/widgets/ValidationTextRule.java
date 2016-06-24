package com.magorasystems.widgets;

import android.support.annotation.IdRes;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class ValidationTextRule {

    private final @IdRes int resourceId;
    private final String errorMessage;
    private final boolean isShow;
    private final String pattern;
    private final int minLength;

    private ValidationTextRule(Builder builder) {
        resourceId = builder.resourceId;
        errorMessage = builder.errorMessage;
        isShow = builder.isShow;
        pattern = builder.pattern;
        minLength = builder.minLength;
    }

    @IdRes
    public int getResourceId() {
        return resourceId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isShow() {
        return isShow;
    }

    public String getPattern() {
        return pattern;
    }

    public int getMinLength() {
        return minLength;
    }

    /**
     * {@code ValidationTextRule} builder static inner class.
     */
    public static final class Builder {
        private int resourceId;
        private String errorMessage;
        private boolean isShow;
        private String pattern;
        private int minLength;

        public Builder() {
        }

        /**
         * Sets the {@code resourceId} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code resourceId} to set
         * @return a reference to this Builder
         */
        public Builder resourceId(int val) {
            resourceId = val;
            return this;
        }

        /**
         * Sets the {@code errorMessage} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code errorMessage} to set
         * @return a reference to this Builder
         */
        public Builder errorMessage(String val) {
            errorMessage = val;
            return this;
        }

        /**
         * Sets the {@code isShow} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code isShow} to set
         * @return a reference to this Builder
         */
        public Builder isShow(boolean val) {
            isShow = val;
            return this;
        }

        /**
         * Sets the {@code pattern} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code pattern} to set
         * @return a reference to this Builder
         */
        public Builder pattern(String val) {
            pattern = val;
            return this;
        }

        /**
         * Sets the {@code minLength} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code minLength} to set
         * @return a reference to this Builder
         */
        public Builder minLength(int val) {
            minLength = val;
            return this;
        }

        /**
         * Returns a {@code ValidationTextRule} built from the parameters previously set.
         *
         * @return a {@code ValidationTextRule} built with parameters of this {@code ValidationTextRule.Builder}
         */
        public ValidationTextRule build() {
            if(resourceId == 0) {
                throw new IllegalArgumentException("resource not set");
            }
            return new ValidationTextRule(this);
        }
    }
}
