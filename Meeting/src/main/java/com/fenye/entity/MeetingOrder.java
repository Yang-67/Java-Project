package com.fenye.entity;

public class MeetingOrder {
    public int meeting_id;//会议ID
    public int room_id;//门牌号
    public String room_name;//会议室名称
    public String meeting_name;//会议名称
    public String meeting_subscriber;//预定者
    public String meeting_start;//会议开始时间
    public String meeting_end;//会议结束时间
    public String meeting_order_time;//会议预定时间
    public int meeting_count;//预计参会人数
    public String meeting_describe;//会议说明
    public String meeting_cancel_res;//取消原因

    public String getMeeting_cancel_res() {
        return meeting_cancel_res;
    }

    public void setMeeting_cancel_res(String meeting_cancel_res) {
        this.meeting_cancel_res = meeting_cancel_res;
    }

    public int getMeeting_id() {
        return meeting_id;
    }

    public void setMeeting_id(int meeting_id) {
        this.meeting_id = meeting_id;
    }

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

    public int getMeeting_count() {
        return meeting_count;
    }

    public void setMeeting_count(int meeting_count) {
        this.meeting_count = meeting_count;
    }

    public String getMeeting_describe() {
        return meeting_describe;
    }

    public void setMeeting_describe(String meeting_describe) {
        this.meeting_describe = meeting_describe;
    }
}
