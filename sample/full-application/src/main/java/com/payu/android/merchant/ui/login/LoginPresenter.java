package com.payu.android.merchant.ui.login;

import com.payu.android.merchant.domain.credentials.CredentialsValidator;
import com.payu.android.merchant.presenter.BasePresenter;
import com.payu.android.merchant.ui.login.LoginPresenter.View;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

class LoginPresenter extends BasePresenter<View> {

    interface View {

        void onLoginFailure();

        void onSuccessfulLogin();
    }

    @Inject
    CredentialsValidator credentialsValidator;

    @Inject
    LoginPresenter() {
    }

    public void login(@NotNull String login, @NotNull String password) {

        if (hasView()) {

            if (credentialsValidator.validate(login, password)) {
                view.onSuccessfulLogin();
            } else {
                view.onLoginFailure();
            }
        }
    }
}
