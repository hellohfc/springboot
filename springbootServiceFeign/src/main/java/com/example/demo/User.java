package com.example.demo;

/**
 * Created by HUFUCHUN on 2017/10/15.
 */
public class User {
    private String username;
    private Integer age;

    public User(){

    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public User(String username, Integer age){
        this.username = username;
        this.age=age;
    }


}

