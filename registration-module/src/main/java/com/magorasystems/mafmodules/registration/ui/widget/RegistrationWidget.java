package com.magorasystems.mafmodules.registration.ui.widget;

import com.magorasystems.widgets.auth.IdentityWidget;
import com.magorasystems.widgets.BaseWidget;
import com.magorasystems.widgets.model.UserViewModel;

import java.io.Serializable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface RegistrationWidget<ID extends Serializable, INPUT extends UserViewModel<ID>, RESULT extends UserViewModel<ID>>
        extends IdentityWidget<INPUT, RESULT>, BaseWidget<INPUT, RESULT> {

    void setEnabled(boolean isEnabled);

}
