package com.payu.android.merchant.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.payu.android.merchant.R;
import com.payu.android.merchant.injection.ActivityComponent;
import com.payu.android.merchant.ui.login.LoginPresenter.View;
import com.payu.android.merchant.ui.products.ProductListActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity implements View {

    @InjectView(R.id.email)
    TextView email;
    @InjectView(R.id.password)
    TextView password;
    @Inject
    LoginPresenter loginPresenter;

    @Override
    public void onLoginFailure() {
        Toast.makeText(this, R.string.login_credentials_invalid, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.log_in)
    public void onLoginRequested() {
        loginPresenter.login(email.getText().toString(), password.getText().toString());
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        loginPresenter.takeView(this);
    }

    @Override
    public void onSuccessfulLogin() {
        startActivity(new Intent(this, ProductListActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActivityComponent.Initializer.init(this).inject(this);
        ButterKnife.inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.dropView();
    }
}
