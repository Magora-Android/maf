package com.magorasystems.mafmodules.module;


import com.magorasystems.mafmodules.module.input.SimpleRegistrationViewInput;
import com.magorasystems.mafmodules.module.output.SimpleRegistrationViewOutput;
import com.magorasystems.mafmodules.registration.module.RegistrationPresenterModule;
import com.magorasystems.mafmodules.router.SimpleRegistrationRouter;
import com.magorasystems.protocolapi.auth.dto.response.StringAuthInfo;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface SimpleRegistrationPresenterModule extends RegistrationPresenterModule<StringAuthInfo,
        SimpleRegistrationRouter, SimpleRegistrationViewInput, SimpleRegistrationViewOutput, SimpleRegistrationModuleInput> {
}
