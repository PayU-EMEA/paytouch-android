package com.payu.android.merchant.ui.confirmation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.payu.android.merchant.R;
import com.payu.android.merchant.injection.ActivityComponent;

import org.jetbrains.annotations.NotNull;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class PaymentConfirmationActivity extends AppCompatActivity {

    private static final String EXTRA_ERROR_STATE = "extra_error";
    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.subtitle)
    TextView subtitle;
    @InjectView(R.id.information_text)
    TextView informationText;
    @InjectView(R.id.continue_shopping)
    Button continueButton;

    public static void showError(@NotNull Context context) {
        Intent intent = new Intent(context, PaymentConfirmationActivity.class);
        intent.putExtra(EXTRA_ERROR_STATE, true);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void showSuccess(@NotNull Context context) {
        Intent intent = new Intent(context, PaymentConfirmationActivity.class);
        intent.putExtra(EXTRA_ERROR_STATE, false);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @OnClick(R.id.continue_shopping)
    public void onContinuePayment() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean isInErrorState = getIntent().getBooleanExtra(EXTRA_ERROR_STATE, false);
        setContentView(R.layout.activity_payment_confirmation);
        ActivityComponent.Initializer.init(this).inject(this);
        ButterKnife.inject(this);

        if (isInErrorState) {
            bindErrorState();
        }
    }

    private void bindErrorState() {
        title.setText(R.string.ups_text);
        subtitle.setText(R.string.something_went_wrong);
        informationText.setText(R.string.an_error_ocurred_during_payment);
        continueButton.setText(R.string.back_to_checkout);
    }
}
