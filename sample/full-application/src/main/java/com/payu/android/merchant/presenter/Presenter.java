package com.payu.android.merchant.presenter;

import org.jetbrains.annotations.NotNull;

public interface Presenter<V> {

    void dropView();

    void onLoad();

    void takeView(@NotNull V view);
}
