package com.magorasystems.mafmodules.view.impl;

import android.view.View;

import com.magorasystems.mafmodules.common.mvp.view.impl.AbstractLceView;
import com.magorasystems.protocolapi.auth.dto.response.StringAuthInfo;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SimpleRegistrationLceViewImpl extends AbstractLceView<StringAuthInfo>
        implements SimpleRegistrationLceView {

    public SimpleRegistrationLceViewImpl(View progressView, View contentView) {
        super(progressView, contentView);
    }
}
