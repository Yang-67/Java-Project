package com.fenye.JavaBean;

public class Meeting {
    private String meeting_id;
    private String room_id;
    private String meeting_name;
    private String meeting_subscriber;

    public String getMeeting_id() {
        return meeting_id;
    }

    public void setMeeting_id(String meeting_id) {
        this.meeting_id = meeting_id;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getMeeting_name() {
        return meeting_name;
    }

    public void setMeeting_name(String meeting_name) {
        this.meeting_name = meeting_name;
    }

    public String getMeeting_subscriber() {
        return meeting_subscriber;
    }

    public void setMeeting_subscriber(String meeting_subscriber) {
        this.meeting_subscriber = meeting_subscriber;
    }

    public String getMeeting_start() {
        return meeting_start;
    }

    public void setMeeting_start(String meeting_start) {
        this.meeting_start = meeting_start;
    }

    public String getMeeting_end() {
        return meeting_end;
    }

    public void setMeeting_end(String meeting_end) {
        this.meeting_end = meeting_end;
    }

    public String getMeeting_order_time() {
        return meeting_order_time;
    }

    public void setMeeting_order_time(String meeting_order_time) {
        this.meeting_order_time = meeting_order_time;
    }


    public String getMeeting_describe() {
        return meeting_describe;
    }

    public void setMeeting_describe(String meeting_describe) {
        this.meeting_describe = meeting_describe;
    }


    public String getMeeting_cancel_res() {
        return meeting_cancel_res;
    }

    public void setMeeting_cancel_res(String meeting_cancel_res) {
        this.meeting_cancel_res = meeting_cancel_res;
    }

    private String meeting_start;
    private String meeting_end;
    private String meeting_order_time;

    public int getMeeting_count() {
        return meeting_count;
    }

    public void setMeeting_count(int meeting_count) {
        this.meeting_count = meeting_count;
    }

    private int meeting_count;
    private String meeting_describe;

    public int getMeeting_state() {
        return meeting_state;
    }

    public void setMeeting_state(int meeting_state) {
        this.meeting_state = meeting_state;
    }

    private int meeting_state;
    private String meeting_cancel_res;
}
