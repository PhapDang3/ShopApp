package com.example.shopapp.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.shopapp.api.ApiResponseCallback;
import com.example.shopapp.model.Product;
import com.example.shopapp.repository.ProductRepository;

import java.util.List;

public class ProductViewModel extends ViewModel {
    private final ProductRepository productRepository;
    private final MutableLiveData<List<Product>> allProducts = new MutableLiveData<>();

    public ProductViewModel() {
        productRepository = new ProductRepository();
        fetchAllProducts();
    }

    public LiveData<List<Product>> getAllProducts() {
        return allProducts;
    }

    private void fetchAllProducts() {
        productRepository.getAllProducts(new ApiResponseCallback<List<Product>>() {
            @Override
            public void onSuccess(List<Product> products) {
                allProducts.postValue(products);
                Log.d("ProductViewModel", "Products fetched: " + products.size());
            }

            @Override
            public void onFailure(String errorMessage) {
                Log.e("ProductViewModel", "Error fetching products: " + errorMessage);
                // Handle error, maybe post a null or a specific error message.
            }
        });
    }
}
