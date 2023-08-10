package com.example.shopapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shopapp.R;
import com.example.shopapp.model.ApiResponse;
import com.example.shopapp.model.User;
import com.example.shopapp.viewmodel.UserViewModel;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";
    private EditText edUsername, edPassword;
    private Button btnRegister;
    private UserViewModel userViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Log.d(TAG, "onCreate: started.");
        edUsername = (EditText) findViewById(R.id.edUsername);
        edPassword = (EditText) findViewById(R.id.edPassword);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edUsername.getText().toString().trim();
                String password = edPassword.getText().toString().trim();
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                    return;
                }

                User user = new User();
                user.setUsername(username);
                user.setPassword(password);

                userViewModel.registerUser(user).observe(RegisterActivity.this, new Observer<ApiResponse<User>>() {
                    @Override
                    public void onChanged(ApiResponse<User> apiResponse) {
                        if ("success".equals(apiResponse.getStatus())) {
                            Toast.makeText(RegisterActivity.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                            startActivity(intent);
                            finish();
                            // You can navigate to another activity here
                        } else {
                            Log.e("RegisterActivity", "User registration failed: " + apiResponse.getMessage());
                            Toast.makeText(RegisterActivity.this, "Đăng ký thất bại: " + apiResponse.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });


    }

}