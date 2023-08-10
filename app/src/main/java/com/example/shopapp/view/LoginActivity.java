package com.example.shopapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopapp.R;
import com.example.shopapp.model.ApiResponse;
import com.example.shopapp.model.User;
import com.example.shopapp.viewmodel.UserViewModel;

public class LoginActivity extends AppCompatActivity {
    private EditText edUsername;
    private EditText edPassword;
    private Button btnLogin;
    private TextView tvSignUp;
    private CheckBox ckbRemember;
    private UserViewModel userViewModel;

    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        edUsername = (EditText) findViewById(R.id.edUsername);
        edPassword = (EditText) findViewById(R.id.edPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        tvSignUp = (TextView) findViewById(R.id.tvSignUp);
        ckbRemember = (CheckBox) findViewById(R.id.ckbRemember);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        // sharedPreferences
        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        String savedUsername = sharedPreferences.getString("username", "");
        String savedPassword = sharedPreferences.getString("password", "");
        if (!savedUsername.isEmpty() && !savedPassword.isEmpty()) {
            edUsername.setText(savedUsername);
            edPassword.setText(savedPassword);
            ckbRemember.setChecked(true);
        }
        // Set OnCheckedChangeListener for the Remember Me checkbox
        ckbRemember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if (isChecked) {
                    // If the checkbox is checked, save the username and password
                    editor.putString("username", edUsername.getText().toString());
                    editor.putString("password", edPassword.getText().toString());
                } else {
                    // If the checkbox is unchecked, remove the username and password
                    editor.remove("username");
                    editor.remove("password");
                }
                editor.apply();
            }
        });
        // signUp
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        //Login sang main
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edUsername.getText().toString();
                String password = edPassword.getText().toString();


                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập tên đăng nhập và mật khẩu", Toast.LENGTH_SHORT).show();
                    // check remember
                    if (ckbRemember.isChecked()) {
                        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", edUsername.getText().toString());
                        editor.putString("password", edPassword.getText().toString());
                        editor.apply();

                    }

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
    private void navigateToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();  // Close the LoginActivity
    }

}