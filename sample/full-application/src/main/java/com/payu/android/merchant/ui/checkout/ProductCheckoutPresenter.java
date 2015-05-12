package com.payu.android.merchant.ui.checkout;

import android.app.Activity;

import com.payu.android.merchant.domain.model.Product;
import com.payu.android.merchant.presenter.BasePresenter;
import com.payu.android.merchant.ui.checkout.ProductCheckoutPresenter.View;
import com.payu.android.merchant.ui.confirmation.PaymentConfirmationActivity;
import com.payu.android.sdk.payment.PaymentEventBus;
import com.payu.android.sdk.payment.PaymentService;
import com.payu.android.sdk.payment.event.AbsentSelectedPaymentMethodEvent;
import com.payu.android.sdk.payment.event.PaymentFailedEvent;
import com.payu.android.sdk.payment.event.PaymentSuccessEvent;
import com.payu.android.sdk.payment.event.PresentSelectedPaymentMethodEvent;
import com.payu.android.sdk.payment.model.Currency;
import com.payu.android.sdk.payment.model.Order;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

import javax.inject.Inject;

class ProductCheckoutPresenter extends BasePresenter<View> {

    public interface View {

        void setPaymentButtonEnabled(boolean enabled);

        void setProductImage(@NotNull String productImageUrl);

        void setProductName(@NotNull String name);

        void setProductPrice(int price, @NotNull Currency currency);

        void setTotalPrice(int price, @NotNull Currency currency);
    }

    private static final int DELIVERY_PRICE = 1599;
    @Inject
    PaymentService payuPaymentService;
    @Inject
    Activity activity;
    @Inject
    PaymentEventBus paymentEventBus;
    private Product loadedProduct;

    @Inject
    ProductCheckoutPresenter() {
    }

    @Override
    public void dropView() {
        super.dropView();
        paymentEventBus.unregister(this);
    }

    @Override
    public void onLoad() {

        if (loadedProduct != null) {
            showProduct(loadedProduct);
        }
    }

    public void onPaymentProcessEventMainThread(PaymentFailedEvent paymentFailedEvent) {
        PaymentConfirmationActivity.showError(activity);
    }

    public void onPaymentProcessEventMainThread(PaymentSuccessEvent event) {
        activity.finish();
        PaymentConfirmationActivity.showSuccess(activity);
    }

    public void onPaymentProcessEventMainThread(PresentSelectedPaymentMethodEvent event) {

        if (hasView()) {
            view.setPaymentButtonEnabled(true);
        }
    }

    public void onPaymentProcessEventMainThread(AbsentSelectedPaymentMethodEvent event) {

        if (hasView()) {
            view.setPaymentButtonEnabled(false);
        }
    }

    public void payForProduct() {
        payuPaymentService.pay(new Order.Builder()
                .withAmount(loadedProduct.getPrice())
                .withCurrency(loadedProduct.getCurrency())
                .withDescription(loadedProduct.getName())
                .withExtOrderId(UUID.randomUUID().toString())
                .build());
    }

    public void showProduct(@NotNull Product product) {
        this.loadedProduct = product;

        if (hasView()) {
            view.setProductName(product.getName());
            view.setProductImage(product.getProductImageUrl());
            view.setProductPrice(product.getPrice(), product.getCurrency());
            view.setTotalPrice(calculateTotalPrice(product.getPrice()), product.getCurrency());
        }
    }

    @Override
    public void takeView(@NotNull View view) {
        super.takeView(view);
        paymentEventBus.register(this);
    }

    private int calculateTotalPrice(int productPrice) {
        return productPrice + DELIVERY_PRICE;
    }
}
