package com.magorasystems.mafmodules.module;

import com.magorasystems.mafmodules.view.SimpleRegistrationInteractiveView;
import com.magorasystems.mafmodules.view.impl.SimpleRegistrationLceView;
import com.magorasystems.protocolapi.auth.dto.response.StringAuthInfo;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface SimpleRegistrationInputView extends RegistrationViewInput<StringAuthInfo, SimpleRegistrationLceView,SimpleRegistrationInteractiveView> {
}
