package com.example.controller.entity;

public class adminDto {
    private String username;
    private String token;
    private String adminUrl;

//    public adminDto() {
//    }

    public adminDto(String username, String token, String adminUrl) {
        this.username = username;
        this.token = token;
        this.adminUrl=adminUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAdminUrl() {
        return adminUrl;
    }

    public void setAdminUrl(String adminUrl) {
        this.adminUrl = adminUrl;
    }
}


