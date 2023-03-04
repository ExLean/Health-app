package com.example.health_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.health_app.database.BackgroundWorker;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText username_field = findViewById(R.id.usr_text_field);
        EditText password_field = findViewById(R.id.psw_text_field);
        Button login = findViewById(R.id.login_btn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnLogin(username_field, password_field);
            }
        });
    }

    public void OnLogin(EditText username_field, EditText password_field) {
        String username = username_field.getText().toString();
        String password = password_field.getText().toString();
        String type = "login";

        System.out.println(username + " / " + password);

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, username, password);
    }
}