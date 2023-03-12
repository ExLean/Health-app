package com.example.health_app.retrofit;

import com.example.health_app.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserApi {

    @GET("/user/get-all")
    Call<List<User>> getAllUsers();

    @POST("user/login")
    Call<User> getUserByLoginData(@Body User user);

    @POST("/user/save")
    Call<User> save(@Body User user);
}
