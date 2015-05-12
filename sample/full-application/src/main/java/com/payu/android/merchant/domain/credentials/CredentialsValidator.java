package com.payu.android.merchant.domain.credentials;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

public class CredentialsValidator {

    static final String TEST_EMAIL = "test@test.pl";
    static final String TEST_PASSWORD = "xyz";

    @Inject
    public CredentialsValidator() {
    }

    public boolean validate(@NotNull String login, @NotNull String password) {
        return TEST_EMAIL.equalsIgnoreCase(login) && TEST_PASSWORD.equalsIgnoreCase(password);
    }
}
