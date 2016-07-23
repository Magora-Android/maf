package com.magorasystems.mafmodules.registration.ui.widget;

import com.magorasystems.widgets.ValidationTextRule;
import com.magorasystems.widgets.ValidationWidget;
import com.magorasystems.widgets.model.UserViewModel;

import java.io.Serializable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface ValidationRegistrationWidget<ID extends Serializable, INPUT extends UserViewModel<ID>, RESULT extends UserViewModel<ID>>
        extends RegistrationWidget<ID, INPUT, RESULT>, ValidationWidget<ValidationTextRule> {
}
