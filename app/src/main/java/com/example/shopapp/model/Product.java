package com.example.shopapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Product implements Parcelable {
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

//    @SerializedName("category")
//    private String categoryId;
    //new category
    @SerializedName("category")
    private Category category;
    @SerializedName("price")
    private Object price; // Sử dụng Object vì kiểu của price là Mixed

    @SerializedName("image")
    private Object image; // Sử dụng Object vì kiểu của image là Mixed

    @SerializedName("description")
    private String description;

    public Product() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Object getPrice() {
        return price;
    }

    public void setPrice(Object price) {
        this.price = price;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    protected Product(Parcel in) {
        id = in.readString();
        name = in.readString();
        description = in.readString();
        // Assuming you will change the Object type for price and image to appropriate types in the future.
        // For now, I'm using readString for simplicity. Adjust as needed.
        price = in.readString();
        image = in.readString();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int i) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(description);
        // Assuming you will change the Object type for price and image to appropriate types in the future.
        // For now, I'm using writeString for simplicity. Adjust as needed.
        dest.writeString((String) price);
        dest.writeString((String) image);
    }
}
