package com.payu.android.merchant.injection;

import android.content.Context;
import android.content.res.Resources;

import com.squareup.picasso.Picasso;

import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = { AndroidModule.class })
public interface SampleComponent {

    Context context();

    Picasso picasso();

    Resources resources();
}

