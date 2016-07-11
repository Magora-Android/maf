package com.magorasystems.mafmodules.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.widget.TextView;

import com.magorasystems.mafmodules.R;
import com.magorasystems.mafmodules.ui.widget.model.SimpleUserPasswordViewModel;
import com.magorasystems.mafmodules.ui.widget.model.SimpleUserViewModel;

import butterknife.BindView;
import rx.Observable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SimpleRegistrationWidget extends AbstractRegistrationWidget<String,
        SimpleUserViewModel,
        SimpleUserPasswordViewModel>
        implements ValidationRegistrationWidget<String, SimpleUserViewModel, SimpleUserPasswordViewModel> {

    @BindView(R.id.text_email)
    protected TextView textEmail;
    @BindView(R.id.text_password)
    protected TextView textPassword;

    @BindView(R.id.text_phone)
    protected TextView textPhone;

    @BindView(R.id.text_name)
    protected TextView textName;

    @BindView(R.id.text_input_password)
    protected TextInputLayout textInputPassword;

    public SimpleRegistrationWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SimpleRegistrationWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SimpleRegistrationWidget(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public Observable<? extends SimpleUserPasswordViewModel> model() {
        return super.model()
                .doOnNext(model -> {
                    textPassword.setText(null);
                    textInputPassword.setError(null);
                    textInputPassword.setErrorEnabled(false);
                });
    }

    @Override
    protected SimpleUserPasswordViewModel getResult() {
        return new SimpleUserPasswordViewModel(new SimpleUserViewModel.
                Builder()
                .email(textEmail.getText())
                .userName(textName.getText())
                .phone(textPhone.getText()), textPassword.getText());

    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        textPhone.setEnabled(enabled);
        textName.setEnabled(enabled);
        textEmail.setEnabled(enabled);
        textPassword.setEnabled(enabled);
    }

    @Override
    public void update(SimpleUserViewModel model) {
        super.update(model);
        validation().subscribe();
        textEmail.setText(model.getEmail());
        textName.setText(model.getUserName());
        textPhone.setText(model.getPhone());
    }
}
