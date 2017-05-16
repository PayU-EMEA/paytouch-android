package com.payu.android.merchant.domain.service;


import android.content.Context;

import com.payu.android.sdk.payment.model.MerchantOAuthAccessToken;
import com.payu.android.sdk.payment.service.TokenProviderService;
import com.payu.android.sdk.payment.service.exception.ExternalRequestError;

/**
 * TODO:
 * This part needs to be implemented by merchant.
 * Token should be obtained from merchant server, for more information check documentation,
 * section: Token Provider Service
 */
public class MerchantTokenProviderService extends TokenProviderService {

    public MerchantTokenProviderService(Context context) {
        super(context);
    }

    @Override
    public MerchantOAuthAccessToken provideAccessToken() throws ExternalRequestError {
        return new MerchantOAuthAccessToken("tocken");
    }
}
