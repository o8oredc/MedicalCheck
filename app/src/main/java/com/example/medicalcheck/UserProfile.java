package com.example.medicalcheck;

public class UserProfile {

    private String username;
    private String photo;

    public UserProfile(String username, String photo) {
        this.username = username;
        this.photo = photo;
    }

    public UserProfile() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
