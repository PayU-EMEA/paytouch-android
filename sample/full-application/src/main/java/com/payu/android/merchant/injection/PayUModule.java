package com.payu.android.merchant.injection;

import android.app.Activity;

import com.payu.android.sdk.payment.PaymentEventBus;
import com.payu.android.sdk.payment.PaymentService;

import dagger.Module;
import dagger.Provides;
import lombok.AllArgsConstructor;

@Module
@AllArgsConstructor(suppressConstructorProperties = true)
public class PayUModule {

    private Activity activity;

    @Provides
    Activity activity() {
        return activity;
    }

    @Provides
    PaymentEventBus paymentEventBus() {
        return new PaymentEventBus();
    }

    @Provides
    PaymentService paymentService() {
        return PaymentService.createInstance(activity);
    }
}
