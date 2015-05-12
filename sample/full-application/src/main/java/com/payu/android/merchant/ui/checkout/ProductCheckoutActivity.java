package com.payu.android.merchant.ui.checkout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.payu.android.merchant.R;
import com.payu.android.merchant.domain.model.Product;
import com.payu.android.merchant.injection.ActivityComponent;
import com.payu.android.merchant.ui.formatter.PriceFormatter;
import com.payu.android.sdk.payment.model.Currency;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import javax.inject.Inject;

public class ProductCheckoutActivity extends AppCompatActivity implements ProductCheckoutPresenter.View {

    private static final String EXTRA_PRODUCT = "extra_product";
    @Inject
    ProductCheckoutPresenter presenter;
    @Inject
    Picasso picasso;
    @Inject
    PriceFormatter priceFormatter;
    @InjectView(R.id.product_image)
    ImageView productImage;
    @InjectView(R.id.product_name)
    TextView productName;
    @InjectView(R.id.price)
    TextView productPrice;
    @InjectView(R.id.price_sum)
    TextView totalPrice;
    @InjectView(R.id.confirm_payment)
    Button paymentButton;

    public static void doCheckout(@NotNull Context context, @NotNull Product product) {
        Intent intent = new Intent(context, ProductCheckoutActivity.class);
        intent.putExtra(EXTRA_PRODUCT, product);
        context.startActivity(intent);
    }

    @OnClick(R.id.confirm_payment)
    public void onPaymentClick() {
        presenter.payForProduct();
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        presenter.takeView(this);
    }

    @Override
    public void setPaymentButtonEnabled(boolean enabled) {
        paymentButton.setEnabled(enabled);
    }

    @Override
    public void setProductImage(@NotNull String productImageUrl) {
        picasso.load(productImageUrl).fit().centerInside().into(productImage);
    }

    @Override
    public void setProductName(@NotNull String name) {
        productName.setText(name);
    }

    @Override
    public void setProductPrice(int price, @NotNull Currency currency) {
        productPrice.setText(priceFormatter.format(price, currency));
    }

    @Override
    public void setTotalPrice(int price, @NotNull Currency currency) {
        totalPrice.setText(priceFormatter.format(price, currency));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_checkout);
        ActivityComponent.Initializer.init(this).inject(this);
        ButterKnife.inject(this);
        presenter.showProduct(getProductFromIntent());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dropView();
    }

    private Product getProductFromIntent() {
        return getIntent().getParcelableExtra(EXTRA_PRODUCT);
    }
}
