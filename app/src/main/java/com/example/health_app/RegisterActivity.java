package com.example.health_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.health_app.model.User;
import com.example.health_app.retrofit.RetrofitService;
import com.example.health_app.retrofit.UserApi;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initializeComponents();
    }

    private void initializeComponents() {
        TextInputEditText usr_text = findViewById(R.id.username_register_textField);
        TextInputEditText pss_text = findViewById(R.id.password_register_textField);
        EditText eml_text = findViewById(R.id.email_register_textField);
        //EditText dte_text = findViewById(R.id.birth_register_textField);
        TextInputEditText wgh_text = findViewById(R.id.weight_register_textField);
        TextInputEditText hgh_text = findViewById(R.id.height_register_textField);
        TextInputEditText cdf_text = findViewById(R.id.calorieDeficit_register_textField);
        MaterialButton register = findViewById(R.id.register_btn);

        RetrofitService retrofitService = new RetrofitService();
        UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);

        register.setOnClickListener(view -> {
            String username = usr_text.getText().toString();
            String password = pss_text.getText().toString();
            String email = eml_text.getText().toString();
            //String[] strDte = dte_text.getText().toString().split("-");
            //Date birthday = new Date((Integer.parseInt(strDte[0]) - 1900), Integer.parseInt(strDte[1]), Integer.parseInt(strDte[2]));
            int weight = Integer.parseInt(wgh_text.getText().toString());
            int height = Integer.parseInt(hgh_text.getText().toString());
            int calorieDeficit = Integer.parseInt(cdf_text.getText().toString());

            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            //user.setBirthday(birthday);
            user.setWeight(weight);
            user.setHeight(height);
            user.setCalorieDeficit(calorieDeficit);

            userApi.save(user)
                    .enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            Toast.makeText(RegisterActivity.this, "Naujas naudotojas užregistruotas", Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(i);
                            finish();
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Toast.makeText(RegisterActivity.this, "Registracija nepavyko", Toast.LENGTH_SHORT).show();
                            Logger.getLogger(RegisterActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
                        }
                    });
        });
    }
}
