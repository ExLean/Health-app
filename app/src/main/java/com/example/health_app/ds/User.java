package com.example.health_app.ds;

import java.util.Date;

public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private int weight;
    private int height;
    private Date birthday;
    private String calorieDeficit;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCalorieDeficit() {
        return calorieDeficit;
    }

    public void setCalorieDeficit(String calorieDeficit) {
        this.calorieDeficit = calorieDeficit;
    }
}
