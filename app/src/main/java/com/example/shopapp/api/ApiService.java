package com.example.shopapp.api;

import androidx.lifecycle.LiveData;

import com.example.shopapp.model.ApiResponse;
import com.example.shopapp.model.Product;
import com.example.shopapp.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
    // user
    @GET("users")
    Call<ApiResponse<List<User>>> getAllUsers();

    @POST("users")
    Call<ApiResponse<User>> createUser(@Body User user);

    @GET("users/{id}")
    Call<ApiResponse<User>> getUserById(@Path("id") String userId);

    @PUT("users/{id}")
    Call<ApiResponse<User>> updateUser(@Path("id") String userId, @Body User user);

    @DELETE("users/{id}")
    Call<ApiResponse<Void>> deleteUser(@Path("id") String userId);

    @POST("register")
    LiveData<ApiResponse<User>> registerUser(@Body User user);

    @POST("login")
    LiveData<ApiResponse<User>> loginUser(@Body User user);

    // product
    @GET("products")
    Call<ApiResponse<List<Product>>> getAllProducts();

    @GET("products/{id}")
    Call<ApiResponse<Product>> getProductById(@Path("id") String id);

    @POST("products")
    Call<ApiResponse<Product>> createProduct(@Body Product product);

    @PUT("products/{id}")
    Call<ApiResponse<Product>> updateProduct(@Path("id") String id, @Body Product product);

    @DELETE("products/{id}")
    Call<ApiResponse<Void>> deleteProduct(@Path("id") String id);
}
