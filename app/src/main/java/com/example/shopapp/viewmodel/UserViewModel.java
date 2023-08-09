package com.example.shopapp.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.shopapp.api.ApiResponseCallback;
import com.example.shopapp.model.ApiResponse;
import com.example.shopapp.model.User;
import com.example.shopapp.repository.UserRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel extends ViewModel {
    private static final String TAG = "UserViewModel";

    private UserRepository userRepository;

    private MutableLiveData<List<User>> usersLiveData = new MutableLiveData<>();
    public UserViewModel() {
        userRepository = new UserRepository();
    }

    public LiveData<List<User>> getUsersLiveData() {
        return usersLiveData;
    }

    public LiveData<ApiResponse<User>> registerUser(User user) {

        return userRepository.registerUser(user.getUsername(), user.getPassword());

    }

    public LiveData<ApiResponse<User>> loginUser(User user) {
        return userRepository.loginUser(user.getUsername(), user.getPassword());
    }
    public void getAllUsers() {
        userRepository.getAllUsers(new Callback<ApiResponse<List<User>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<User>>> call, Response<ApiResponse<List<User>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    usersLiveData.setValue(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<User>>> call, Throwable t) {
                // Handle failure here
            }
        });
    }
    public void createUser(User user, ApiResponseCallback<User> callback) {
        userRepository.createUser(user, new ApiResponseCallback<User>() {
            @Override
            public void onSuccess(User result) {
                callback.onSuccess(result);
            }

            @Override
            public void onFailure(String errorMessage) {
                callback.onFailure(errorMessage);
            }
        });
    }



}
