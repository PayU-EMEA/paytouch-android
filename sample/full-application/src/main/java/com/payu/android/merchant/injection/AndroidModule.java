package com.payu.android.merchant.injection;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;

import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import lombok.AllArgsConstructor;

@Module
@AllArgsConstructor(suppressConstructorProperties = true)
public class AndroidModule {

    private final Context application;

    @Provides
    Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    Picasso providePicasso(Context context) {
        return Picasso.with(context);
    }

    @Provides
    @Singleton
    SharedPreferences providePreferenceManager() {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    Resources provideResources() {
        return application.getResources();
    }
}
