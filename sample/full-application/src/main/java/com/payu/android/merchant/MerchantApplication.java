package com.payu.android.merchant;

import android.app.Application;
import android.content.Context;

import com.payu.android.merchant.injection.AndroidModule;
import com.payu.android.merchant.injection.DaggerSampleComponent;
import com.payu.android.merchant.injection.SampleComponent;

import org.jetbrains.annotations.NotNull;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import timber.log.Timber;

public class MerchantApplication extends Application {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public final static class DaggerComponentInitializer {

        public static SampleComponent init(@NotNull Context app) {
            return DaggerSampleComponent.builder()
                    .androidModule(new AndroidModule(app.getApplicationContext()))
                    .build();
        }
    }

    private SampleComponent objectGraph;

    public static SampleComponent component(Context context) {
        return ((MerchantApplication) context.getApplicationContext()).objectGraph;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        objectGraph = DaggerComponentInitializer.init(this);
    }
}
