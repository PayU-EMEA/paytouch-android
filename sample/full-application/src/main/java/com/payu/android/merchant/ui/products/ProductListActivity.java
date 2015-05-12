package com.payu.android.merchant.ui.products;

import static java.util.Arrays.asList;

import static timber.log.Timber.i;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.payu.android.merchant.R;
import com.payu.android.merchant.domain.model.Product;
import com.payu.android.merchant.injection.ActivityComponent;
import com.payu.android.merchant.ui.checkout.ProductCheckoutActivity;
import com.payu.android.sdk.payment.model.Currency;

import butterknife.ButterKnife;
import butterknife.InjectView;
import javax.inject.Inject;

public class ProductListActivity extends AppCompatActivity {

    static final int COLUMN_COUNT = 2;
    @InjectView(R.id.recycler)
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    @Inject
    ProductsAdapter productsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        ActivityComponent.Initializer.init(this).inject(this);
        ButterKnife.inject(this);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, COLUMN_COUNT);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(productsAdapter);
        productsAdapter.setOnBuyNowClickListener(new OnBuyNowClickListener() {

            @Override
            public void onBuyNowClick(int position) {
                startCheckoutProcessForItemAtPosition(position);
            }
        });
        productsAdapter.setData(asList(
                new Product("Glebogryzarka", 199999, Currency.PLN,
                        "http://www.irpol.pl/_var/gfx/1e61cea74557fd2925da3c742c115f16.jpg"),
                new Product("Turbo napawarka", 45537, Currency.PLN,
                        "http://www.haltertuning.ch/images/Elektro%20Turbo.jpg"),
                new Product("Taczka lewitacyjna", 31137, Currency.PLN,
                        "http://www.bartimport.pl/upload/product/taczka--tx-06/taczka--tx-06_b_1.jpg"),
                new Product("Betoniarka", 15037, Currency.PLN, "http://www.betoniarki.eu/images/beton2.jpg")
        ));
    }

    private void startCheckoutProcessForItemAtPosition(int position) {
        Product product = productsAdapter.getItem(position);
        i("Buy product %s", product);
        ProductCheckoutActivity.doCheckout(this, product);
    }
}
