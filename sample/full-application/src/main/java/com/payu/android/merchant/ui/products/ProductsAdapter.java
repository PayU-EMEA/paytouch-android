package com.payu.android.merchant.ui.products;

import static com.google.common.collect.Lists.newArrayList;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.payu.android.merchant.R;
import com.payu.android.merchant.domain.model.Product;
import com.payu.android.merchant.ui.formatter.PriceFormatter;
import com.payu.android.merchant.ui.products.ProductsAdapter.ViewHolder;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import javax.inject.Inject;
import lombok.Setter;

class ProductsAdapter extends Adapter<ViewHolder> implements OnBuyNowClickListener {

    static class ViewHolder extends RecyclerView.ViewHolder {

        OnBuyNowClickListener onBuyNowClickListener;
        @InjectView(R.id.product_image)
        ImageView productImage;
        @InjectView(R.id.product_name)
        TextView productName;
        @InjectView(R.id.price)
        TextView price;

        public ViewHolder(@NotNull View view, @NotNull OnBuyNowClickListener onBuyNowClickListener) {
            super(view);
            this.onBuyNowClickListener = onBuyNowClickListener;
            ButterKnife.inject(this, view);
        }

        @OnClick(R.id.buy_now)
        public void onBuyNowClick() {
            onBuyNowClickListener.onBuyNowClick(getAdapterPosition());
        }
    }

    List<Product> products = newArrayList();
    @Inject
    PriceFormatter priceFormatter;
    @Inject
    Picasso picasso;
    @Setter
    @Nullable
    OnBuyNowClickListener onBuyNowClickListener;

    @Inject
    ProductsAdapter() {
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Product product = getItem(position);
        viewHolder.productName.setText(product.getName());
        viewHolder.price.setText(priceFormatter.format(product.getPrice(), product.getCurrency()));
        picasso.load(product.getProductImageUrl())
                .into(viewHolder.productImage);
    }

    @Override
    public void onBuyNowClick(int position) {

        if (onBuyNowClickListener != null) {
            onBuyNowClickListener.onBuyNowClick(position);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_product, viewGroup, false);
        return new ViewHolder(view, this);
    }

    public void setData(List<Product> products) {
        this.products.clear();
        this.products.addAll(products);
        notifyDataSetChanged();
    }

    Product getItem(int adapterPosition) {
        return products.get(adapterPosition);
    }
}
