package com.payu.android.merchant.presenter;

import org.jetbrains.annotations.NotNull;

public abstract class BasePresenter<T> implements Presenter<T> {

    protected T view;

    @Override
    public void dropView() {
        this.view = null;
    }

    @Override
    public void onLoad() {
        // template method
    }

    @Override
    public void takeView(@NotNull T view) {
        this.view = view;
        onLoad();
    }

    protected boolean hasView() {
        return view != null;
    }
}