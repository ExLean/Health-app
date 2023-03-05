package com.example.health_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    String url = "http://192.168.8.107/login_user.php";

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
                String username = username_field.getText().toString();
                String password = password_field.getText().toString();

                new LoginUser().execute(username, password);
            }
        });
    }

    public class LoginUser extends AsyncTaskExecutorService<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            String user_name = strings[0];
            String user_pass = strings[1];

            OkHttpClient client = new OkHttpClient();
            RequestBody formBody = new FormBody.Builder()
                    .add("user_name", user_name)
                    .add("user_password", user_pass)
                    .build();

            Request request = new Request.Builder()
                    .url(url)
                    .post(formBody)
                    .build();

            Response response;
            try{
                response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    String result = response.body().string();
                    if (result.equalsIgnoreCase("login")) {
                        Intent i = new Intent(MainActivity.this, Dashboard.class);
                        i.putExtra("username", user_name);
                        i.putExtra("password", user_pass);
                        startActivity(i);
                        finish();
                    } else {
                        showToast("Wrong Username or password");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
        }
    }

    public void showToast(final String Text) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, Text, Toast.LENGTH_LONG).show();
            }
        });
    }
}