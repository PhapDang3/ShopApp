package com.example.shopapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shopapp.R;
import com.example.shopapp.model.ApiResponse;
import com.example.shopapp.model.User;
import com.example.shopapp.viewmodel.UserViewModel;

public class LoginActivity extends AppCompatActivity {
    private EditText edUsername;
    private EditText edPassword;
    private Button btnLogin;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edUsername = findViewById(R.id.edUsername);
        edPassword = findViewById(R.id.edPassword);
        btnLogin = findViewById(R.id.btnLogin);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edUsername.getText().toString();
                String password = edPassword.getText().toString();

                // Validate input (e.g., check for empty fields)
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập tên đăng nhập và mật khẩu", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Create User object for login
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);

                // Perform login using UserViewModel
                userViewModel.loginUser(user).observe(LoginActivity.this, new Observer<ApiResponse<User>>() {
                    @Override
                    public void onChanged(ApiResponse<User> apiResponse) {
                        if ("success".equals(apiResponse.getStatus())) {
                            Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                            // Navigate to MainActivity after successful login
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();  // Optional: to close the LoginActivity
                        } else {
                            Toast.makeText(LoginActivity.this, "Đăng nhập thất bại: " + apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

}