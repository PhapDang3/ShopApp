package com.example.shopapp.api;

public interface ApiResponseCallback<T> {
    void onSuccess(T result);
    void onFailure(String errorMessage);
}
