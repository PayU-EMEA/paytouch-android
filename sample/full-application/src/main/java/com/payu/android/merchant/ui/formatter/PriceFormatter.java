package com.payu.android.merchant.ui.formatter;

import com.payu.android.sdk.payment.model.Currency;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

public class PriceFormatter {

    @Inject
    PriceFormatter() {
    }

    public String format(int price, @NotNull Currency currency) {
        return String.format("%s %s", toDecimal(price), currency.name());
    }

    private float toDecimal(int price) {
        return price / 100f;
    }
}
