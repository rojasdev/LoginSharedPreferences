package com.demo.loginsharedpreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

public class LoginActivity extends AppCompatActivity {

    private static final String PREF_NAME = "login_preferences";
    private static final String KEY_USERNAME = "username";

    private EditText etUsername, etPassword;
    private Button btnLogin;

    private AppCompatImageView imageViewShowPassword;
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.editTextUsername);
        etPassword = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.buttonLogin);
        //cbShowPassword = findViewById(R.id.imageViewShowPassword);
        imageViewShowPassword = findViewById(R.id.imageViewShowPassword);

        /*// Set a click listener for the "Show Password" checkbox
        cbShowPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                togglePasswordVisibility(isChecked);
            }
        });*/

        imageViewShowPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve input values
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                // Check if the login is successful
                if (login(username, password)) {
                    // Save login status
                    saveLoginStatus(username);

                    // Start the main activity
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    // Display an error message
                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean login(String username, String password) {
        // In a real-world scenario, you would perform authentication here
        // For simplicity, let's use a hardcoded username and password
        return username.equals("user") && password.equals("password");
    }

    private void saveLoginStatus(String username) {
        // Save login status in SharedPreferences
        SharedPreferences preferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_USERNAME, username);
        editor.apply();
    }

    private void togglePasswordVisibility() {
        isPasswordVisible = !isPasswordVisible;
        int visibility = isPasswordVisible ?
                View.VISIBLE : View.GONE;

        etPassword.setTransformationMethod(isPasswordVisible ?
                HideReturnsTransformationMethod.getInstance() :
                PasswordTransformationMethod.getInstance());

        imageViewShowPassword.setImageResource(isPasswordVisible ?
                R.drawable.ic_eye_opened : R.drawable.ic_eye_closed);
    }
}
