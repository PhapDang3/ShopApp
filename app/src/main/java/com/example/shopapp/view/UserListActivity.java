package com.example.shopapp.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;



import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.shopapp.R;
import com.example.shopapp.apdapter.UserAdapter;
import com.example.shopapp.api.ApiResponseCallback;
import com.example.shopapp.model.User;
import com.example.shopapp.viewmodel.UserViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class UserListActivity extends AppCompatActivity {
    private UserViewModel viewModel;
    private RecyclerView recyclerView;
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        // create  RecyclerView and Adapter
        recyclerView = findViewById(R.id.user_recycler_view);
        adapter = new UserAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize ViewModel
        viewModel = new ViewModelProvider(this).get(UserViewModel.class);


        // Observe LiveData from ViewModel
        viewModel.getUsersLiveData().observe(this, users -> {
            // Update UI with the list of users
            adapter.setUsers(users);
            adapter.notifyDataSetChanged();
            // For example, populate a RecyclerView with the data
        });

        // Fetch users
        viewModel.getAllUsers();


        FloatingActionButton fabAddUser = new FloatingActionButton(this);
        fabAddUser.setImageResource(R.drawable.ic_add);
        fabAddUser.setOnClickListener(view -> {
            // Show an AlertDialog for creating a new user
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Create New User");

            View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_create_user, null);
            EditText edUsername = dialogView.findViewById(R.id.edUsername);
            EditText edPassword = dialogView.findViewById(R.id.edPassword);
            builder.setView(dialogView);

            builder.setPositiveButton("Create", (dialog, which) -> {
                String username = edUsername.getText().toString();
                String password = edPassword.getText().toString();

                // Call the ViewModel's createUser method to create the user via API
                User newUser = new User();
                newUser.setUsername(username);
                newUser.setPassword(password);
                viewModel.createUser(newUser, new ApiResponseCallback<User>() {
                    @Override
                    public void onSuccess(User user) {
                        // Handle success case, e.g., show a toast or update the UI
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        // Handle error case, e.g., show a toast or display an error message
                    }
                });
            });
            builder.setNegativeButton("Cancel", null);

            AlertDialog dialog = builder.create();
            dialog.show();
        });

        // Create FAB vào layout
        CoordinatorLayout.LayoutParams layoutParams = new CoordinatorLayout.LayoutParams(
                CoordinatorLayout.LayoutParams.WRAP_CONTENT,
                CoordinatorLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.gravity = Gravity.END | Gravity.BOTTOM;
        int margin = getResources().getDimensionPixelSize(R.dimen.fab_margin);
        layoutParams.setMargins(margin, margin, margin, margin);
        fabAddUser.setLayoutParams(layoutParams);
        ((CoordinatorLayout) findViewById(R.id.coordinator_layout)).addView(fabAddUser);
    }

}