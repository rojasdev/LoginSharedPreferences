package com.demo.loginsharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String PREF_NAME = "login_preferences";
    private static final String KEY_USERNAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLogout = findViewById(R.id.buttonLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear login status
                clearLoginStatus();

                // Redirect to LoginActivity
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish(); // Close the LogoutActivity to prevent going back to it
            }
        });

        // Check if the user is not logged in
        if (!isLoggedIn()) {
            // Redirect to LoginActivity
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish(); // Close the MainActivity to prevent going back to it
        }

        // Continue with the normal initialization of the MainActivity
        // ...
        Toast.makeText(MainActivity.this, "Hello, Toast!", Toast.LENGTH_SHORT).show();
    }

    private boolean isLoggedIn() {
        // Check if the user is logged in by verifying the presence of a username in SharedPreferences
        SharedPreferences preferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String username = preferences.getString(KEY_USERNAME, null);
        return username != null;
    }

    private void clearLoginStatus() {
        // Clear login status in SharedPreferences
        SharedPreferences preferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(KEY_USERNAME);
        editor.apply();
    }
}