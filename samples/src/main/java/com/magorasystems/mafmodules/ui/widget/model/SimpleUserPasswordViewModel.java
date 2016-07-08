package com.magorasystems.mafmodules.ui.widget.model;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SimpleUserPasswordViewModel extends SimpleUserViewModel {

    private CharSequence password;

    public SimpleUserPasswordViewModel(SimpleUserViewModel.Builder builder, CharSequence password) {
        super(builder);
        this.password = password;
    }

    public CharSequence getPassword() {
        return password;
    }

}
