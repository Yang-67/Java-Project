package com.example.ojtest.entity;

public class titleInfo {
    private int id;
    private String title;
    private String content;
    private String solution;

    public titleInfo() {
    }

    public titleInfo(String title, String ss, String result) {
        this.title=title;
        this.content=ss;
        this.solution=result;
    }
}
