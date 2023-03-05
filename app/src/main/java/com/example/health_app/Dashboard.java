package com.example.health_app;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.health_app.ds.User;
import com.google.gson.Gson;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        Bundle extras = getIntent().getExtras();
        User user = null;
        if (extras != null) {
            String jsonUser = extras.getString("json_user");
            user = new Gson().fromJson(jsonUser, User.class);
        }

        TextView textView = findViewById(R.id.textView);

        String text = "User logged in: " + user.getUsername() + "\n" +
                "user's pass is:" + user.getPassword();

        textView.setText(text);
    }
}
