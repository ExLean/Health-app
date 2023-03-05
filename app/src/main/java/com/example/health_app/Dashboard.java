package com.example.health_app;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.health_app.ds.User;

public class Dashboard extends AppCompatActivity {

    String user_name, user_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user_name = extras.getString("username");
            user_pass = extras.getString("password");
        }

        User user = new User(user_name, user_pass);

        TextView textView = findViewById(R.id.textView);

        String text = "User logged in: " + user.getUsername() + "\n" +
                "user's pass is:" + user.getPassword();
        textView.setText(text);
    }
}
