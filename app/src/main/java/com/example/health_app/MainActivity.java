package com.example.health_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.health_app.model.User;
import com.example.health_app.retrofit.RetrofitService;
import com.example.health_app.retrofit.UserApi;
import com.google.gson.Gson;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText username_field = findViewById(R.id.username_login_textField);
        EditText password_field = findViewById(R.id.password_login_textField);
        Button login = findViewById(R.id.login_btn);
        Button goToRegister = findViewById(R.id.goToRegister_btn);

        RetrofitService retrofitService = new RetrofitService();
        UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = username_field.getText().toString();
                String password = password_field.getText().toString();

                User user = new User();
                user.setUsername(username);
                user.setPassword(password);

                userApi.getUserByLoginData(user)
                        .enqueue(new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                Toast.makeText(MainActivity.this, "Prisijungta", Toast.LENGTH_SHORT).show();

                                Intent i = new Intent(MainActivity.this, MenuActivity.class);
                                i.putExtra("json_user", (new Gson()).toJson(response.body()));
                                startActivity(i);
                                finish();
                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                                Toast.makeText(MainActivity.this, "Prisijungti nepavyko", Toast.LENGTH_SHORT).show();
                                Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Error occurred trying to login", t);
                            }
                        });
            }
        });

        goToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}