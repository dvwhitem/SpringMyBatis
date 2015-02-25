package com.home.springmybatis.domain;

/**
 * Created by go1095 on 2/25/15.
 */
public class Hobby {
    private String hobbyId;

    public String getHobbyId() {
        return hobbyId;
    }

    public void setHobbyId(String hobbyId) {
        this.hobbyId = hobbyId;
    }

    @Override
    public String toString() {
        return "Hobby{" +
                "hobbyId='" + hobbyId + '\'' +
                '}';
    }
}
