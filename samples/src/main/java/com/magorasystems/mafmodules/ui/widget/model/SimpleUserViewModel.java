package com.magorasystems.mafmodules.ui.widget.model;

import com.magorasystems.widgets.model.UserViewModel;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SimpleUserViewModel extends UserViewModel<String> {

    private CharSequence userName;
    private CharSequence phone;
    private CharSequence email;

    protected SimpleUserViewModel(Builder builder) {
        userName = builder.userName;
        phone = builder.phone;
        email = builder.email;
    }

    public CharSequence getUserName() {
        return userName;
    }

    public CharSequence getPhone() {
        return phone;
    }

    public CharSequence getEmail() {
        return email;
    }


    /**
     * {@code SimpleUserViewModel} builder static inner class.
     */
    public static final class Builder {
        private CharSequence userName;
        private CharSequence phone;
        private CharSequence email;

        public Builder() {
        }

        /**
         * Sets the {@code userName} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code userName} to set
         * @return a reference to this Builder
         */
        public Builder userName(CharSequence val) {
            userName = val;
            return this;
        }

        /**
         * Sets the {@code phone} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code phone} to set
         * @return a reference to this Builder
         */
        public Builder phone(CharSequence val) {
            phone = val;
            return this;
        }

        /**
         * Sets the {@code email} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code email} to set
         * @return a reference to this Builder
         */
        public Builder email(CharSequence val) {
            email = val;
            return this;
        }

        /**
         * Returns a {@code SimpleUserViewModel} built from the parameters previously set.
         *
         * @return a {@code SimpleUserViewModel} built with parameters of this {@code SimpleUserViewModel.Builder}
         */
        public SimpleUserViewModel build() {
            return new SimpleUserViewModel(this);
        }
    }
}
