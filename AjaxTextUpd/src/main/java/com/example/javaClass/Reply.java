package com.example.javaClass;

public class Reply {
    private int user_id;
    private int title_id;
    private String user_name;
    private String reply_content;
    private String reply_rtime;

    public String getReply_rtime() {
        return reply_rtime;
    }

    public void setReply_rtime(String reply_rtime) {
        this.reply_rtime = reply_rtime;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getTitle_id() {
        return title_id;
    }

    public void setTitle_id(int title_id) {
        this.title_id = title_id;
    }

    public String getReply_content() {
        return reply_content;
    }

    public void setReply_content(String reply_content) {
        this.reply_content = reply_content;
    }



}
