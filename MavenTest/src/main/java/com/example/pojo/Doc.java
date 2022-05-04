package com.example.pojo;

public class Doc {
    private int doc_id;
    private int user_id;
    private String user_name;
    private String doc_name;
    private int doc_authority;

    public Doc() {
    }

    public Doc(int doc_id, int user_id, String doc_name, int doc_authority) {
        this.doc_id = doc_id;
        this.user_id = user_id;
        this.doc_name = doc_name;
        this.doc_authority = doc_authority;
    }

    public Doc(int user_id, String doc_name, int doc_authority) {
        this.user_id = user_id;
        this.doc_name = doc_name;
        this.doc_authority = doc_authority;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(int doc_id) {
        this.doc_id = doc_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getDoc_name() {
        return doc_name;
    }

    public void setDoc_name(String doc_name) {
        this.doc_name = doc_name;
    }

    public int getDoc_authority() {
        return doc_authority;
    }

    public void setDoc_authority(int doc_authority) {
        this.doc_authority = doc_authority;
    }

    @Override
    public String toString() {
        return "Doc{" +
                "doc_id=" + doc_id +
                ", user_id=" + user_id +
                ", doc_name='" + doc_name + '\'' +
                ", doc_authority=" + doc_authority +
                '}';
    }
}
