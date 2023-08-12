package com.example.shopapp.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopapp.R;
import com.example.shopapp.model.Product;


public class ProductDetailFragment extends Fragment {
    private Product product;

    public ProductDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);
        if (getArguments() != null) {
            Product selectedProduct = getArguments().getParcelable("selected_product");
            if (selectedProduct != null) {
                // Update the UI with the details of the selected product
                // For example:
//                TextView productNameTextView = view.findViewById(R.id.product_name_text_view);
//                productNameTextView.setText(selectedProduct.getName());

                // And similarly for other details like price, description, etc.
            }
        }
            return view;
        }

        @Override
        public void onViewCreated (@NonNull View view, @Nullable Bundle savedInstanceState){
            super.onViewCreated(view, savedInstanceState);
            // Get product data from arguments
            if (getArguments() != null) {
                product = getArguments().getParcelable("selected_product");
            }

            // Update UI elements with product details
            if (product != null) {
                ((TextView) view.findViewById(R.id.product_name)).setText(product.getName());
                TextView productPriceTextView = view.findViewById(R.id.product_price);
                productPriceTextView.setText(String.format("%s VND", product.getPrice().toString()));
                ((TextView) view.findViewById(R.id.product_description)).setText(product.getDescription());


                // Using Glide to load and display the product image with a placeholder
                Glide.with(this)
                        .load(product.getImage()) // Assuming product.getImage() returns the URL or URI of the image
                        .placeholder(R.drawable.default_image) // Your default image in res/drawable
                        .into((ImageView) view.findViewById(R.id.product_image));
            }
        }
    }
