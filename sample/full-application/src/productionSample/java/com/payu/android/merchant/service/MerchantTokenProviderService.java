package com.payu.android.merchant.service;

import android.content.Context;

import com.payu.android.sdk.payment.model.MerchantOAuthAccessToken;
import com.payu.android.sdk.payment.service.TokenProviderService;
import com.payu.android.sdk.payment.service.exception.ExternalRequestError;

public class MerchantTokenProviderService extends TokenProviderService {

    public MerchantTokenProviderService(Context context) {
        super(context);
    }

    @Override
    public MerchantOAuthAccessToken provideAccessToken() throws ExternalRequestError {
        // Fetch access token here from your backend and wrap into MerchantOAuthAccessToken
        return new MerchantOAuthAccessToken("token_here");
    }
}
