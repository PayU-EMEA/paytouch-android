package com.payu.android.merchant.injection;

import android.app.Activity;

import com.payu.android.merchant.MerchantApplication;
import com.payu.android.merchant.ui.checkout.ProductCheckoutActivity;
import com.payu.android.merchant.ui.confirmation.PaymentConfirmationActivity;
import com.payu.android.merchant.ui.login.LoginActivity;
import com.payu.android.merchant.ui.products.ProductListActivity;

import dagger.Component;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@PerActivityScope
@Component(dependencies = SampleComponent.class, modules = PayUModule.class)
public interface ActivityComponent extends SampleComponent {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    final class Initializer {

        public static ActivityComponent init(Activity ctx) {
            return DaggerActivityComponent.builder()
                    .sampleComponent(MerchantApplication.component(ctx))
                    .payUModule(new PayUModule(ctx))
                    .build();
        }
    }

    void inject(LoginActivity loginActivity);

    void inject(ProductListActivity productListActivity);

    void inject(ProductCheckoutActivity productCheckoutActivity);

    void inject(PaymentConfirmationActivity paymentConfirmation);
}
