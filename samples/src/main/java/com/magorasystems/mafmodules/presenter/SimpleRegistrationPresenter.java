package com.magorasystems.mafmodules.presenter;

import com.magorasystems.mafmodules.module.output.SimpleRegistrationViewOutput;
import com.magorasystems.mafmodules.registration.presenter.RegistrationPresenter;
import com.magorasystems.mafmodules.registration.router.RegistrationRouter;
import com.magorasystems.mafmodules.view.impl.SimpleRegistrationLceView;
import com.magorasystems.protocolapi.auth.dto.response.StringAuthInfo;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface SimpleRegistrationPresenter
        extends RegistrationPresenter<StringAuthInfo,
                SimpleRegistrationLceView,
                RegistrationRouter<StringAuthInfo>,
                SimpleRegistrationViewOutput> {

}
