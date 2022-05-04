package com.fenye.JavaBean;

public class MeetingRoom {
    private int room_id;
    private String room_name;
    private int room_count;
    private int room_state;

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public int getRoom_count() {
        return room_count;
    }

    public void setRoom_count(int room_count) {
        this.room_count = room_count;
    }

    public int getRoom_state() {
        return room_state;
    }

    public void setRoom_state(int room_state) {
        this.room_state = room_state;
    }

    public String getRoom_declare() {
        return room_declare;
    }

    public void setRoom_declare(String room_declare) {
        this.room_declare = room_declare;
    }

    private String room_declare;

}
