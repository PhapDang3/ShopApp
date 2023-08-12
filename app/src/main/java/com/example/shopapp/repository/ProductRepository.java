package com.example.shopapp.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.shopapp.api.ApiResponseCallback;
import com.example.shopapp.api.ApiService;
import com.example.shopapp.api.RetrofitClient;
import com.example.shopapp.model.ApiResponse;
import com.example.shopapp.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRepository {
    private final ApiService apiService;
    private static final String TAG = "ProductRepository";
    private MutableLiveData<List<Product>> products;
    public ProductRepository() {
        apiService = RetrofitClient.getClient().create(ApiService.class);
    }

    public void getAllProducts(ApiResponseCallback<List<Product>> apiResponseCallback) {
        Call<ApiResponse<List<Product>>> call = apiService.getAllProducts();
        call.enqueue(new Callback<ApiResponse<List<Product>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<Product>>> call, Response<ApiResponse<List<Product>>> response) {
                if (response.isSuccessful()) {
                    Log.d("API_RESPONSE", "Response is successful");
                    ApiResponse<List<Product>> apiResponse = response.body();
                    if (apiResponse != null && "success".equals(apiResponse.getStatus())) {
                        apiResponseCallback.onSuccess(apiResponse.getData());
                        Log.d("ProductRepository", "API success: Received " + apiResponse.getData().size() + " products");

                    } else {
                        Log.e("API_RESPONSE", "Network error: " + response.code());
                        apiResponseCallback.onFailure("Failed to fetch products.");
                        Log.e("ProductRepository", "API failure: Failed to update product");

                    }
                } else {
                    apiResponseCallback.onFailure("Network error.");
                    Log.e("ProductRepository", "API network error");

                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<Product>>> call, Throwable t) {
                apiResponseCallback.onFailure("Network error: " + t.getMessage());
                Log.e("ProductRepository", "API network error: " + t.getMessage());
            }
        });
    }
    public void createProduct(Product product, ApiResponseCallback<Product> apiResponseCallback) {
        // ... Implementation for createProduct ...
        Call<ApiResponse<Product>> call = apiService.createProduct(product);
        call.enqueue(new Callback<ApiResponse<Product>>() {
            @Override
            public void onResponse(Call<ApiResponse<Product>> call, Response<ApiResponse<Product>> response) {
                if (response.isSuccessful()) {
                    ApiResponse<Product> apiResponse = response.body();
                    if (apiResponse != null && "success".equals(apiResponse.getStatus())) {
                        apiResponseCallback.onSuccess(apiResponse.getData());
                    } else {
                        apiResponseCallback.onFailure("Failed to create product.");
                    }
                } else {
                    apiResponseCallback.onFailure("Network error.");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<Product>> call, Throwable t) {
                apiResponseCallback.onFailure("Network error: " + t.getMessage());
            }
        });
    }

    public void getProductById(String productId, ApiResponseCallback<Product> apiResponseCallback) {
        // ... Implementation for getProductById ...
        Call<ApiResponse<Product>> call = apiService.getProductById(productId);
        call.enqueue(new Callback<ApiResponse<Product>>() {
            @Override
            public void onResponse(Call<ApiResponse<Product>> call, Response<ApiResponse<Product>> response) {
                if (response.isSuccessful()) {
                    ApiResponse<Product> apiResponse = response.body();
                    if (apiResponse != null && "success".equals(apiResponse.getStatus())) {
                        apiResponseCallback.onSuccess(apiResponse.getData());
                    } else {
                        apiResponseCallback.onFailure("Failed to fetch product details.");
                    }
                } else {
                    apiResponseCallback.onFailure("Network error.");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<Product>> call, Throwable t) {
                apiResponseCallback.onFailure("Network error: " + t.getMessage());
            }
        });
    }

    public void updateProduct(String productId, Product product, ApiResponseCallback<Product> apiResponseCallback) {
        // ... Implementation for updateProduct ...
        Call<ApiResponse<Product>> call = apiService.updateProduct(productId, product);
        call.enqueue(new Callback<ApiResponse<Product>>() {
            @Override
            public void onResponse(Call<ApiResponse<Product>> call, Response<ApiResponse<Product>> response) {
                if (response.isSuccessful()) {
                    ApiResponse<Product> apiResponse = response.body();
                    if (apiResponse != null && "success".equals(apiResponse.getStatus())) {
                        apiResponseCallback.onSuccess(apiResponse.getData());
                    } else {
                        apiResponseCallback.onFailure("Failed to update product.");
                    }
                } else {
                    apiResponseCallback.onFailure("Network error.");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<Product>> call, Throwable t) {
                apiResponseCallback.onFailure("Network error: " + t.getMessage());
            }
        });
    }

    public void deleteProduct(String productId, ApiResponseCallback<Void> apiResponseCallback) {
        // ... Implementation for deleteProduct ...
        Call<ApiResponse<Void>> call = apiService.deleteProduct(productId);
        call.enqueue(new Callback<ApiResponse<Void>>() {
            @Override
            public void onResponse(Call<ApiResponse<Void>> call, Response<ApiResponse<Void>> response) {
                if (response.isSuccessful()) {
                    ApiResponse<Void> apiResponse = response.body();
                    if (apiResponse != null && "success".equals(apiResponse.getStatus())) {
                        apiResponseCallback.onSuccess(null);
                    } else {
                        apiResponseCallback.onFailure("Failed to delete product.");
                    }
                } else {
                    apiResponseCallback.onFailure("Network error.");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<Void>> call, Throwable t) {
                apiResponseCallback.onFailure("Network error: " + t.getMessage());
            }
        });

    }
}
