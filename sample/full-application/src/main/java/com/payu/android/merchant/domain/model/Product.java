package com.payu.android.merchant.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.payu.android.sdk.payment.model.Currency;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor(suppressConstructorProperties = true)
public class Product implements Parcelable {

    public static final Creator<Product> CREATOR = new Creator<Product>() {

        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    private String name;
    private int price;
    private Currency currency;
    private String productImageUrl;

    Product(Parcel source) {
        name = source.readString();
        price = source.readInt();
        currency = (Currency) source.readSerializable();
        productImageUrl = source.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(name);
        parcel.writeInt(price);
        parcel.writeSerializable(currency);
        parcel.writeString(productImageUrl);
    }
}
