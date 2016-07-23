package com.magorasystems.mafmodules.module.input;

import com.magorasystems.mafmodules.registration.module.input.RegistrationViewInput;
import com.magorasystems.mafmodules.view.SimpleRegistrationInteractiveView;
import com.magorasystems.mafmodules.view.impl.SimpleRegistrationLceView;
import com.magorasystems.protocolapi.auth.dto.response.StringAuthInfo;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface SimpleRegistrationViewInput extends RegistrationViewInput<StringAuthInfo, SimpleRegistrationLceView,SimpleRegistrationInteractiveView> {
}
