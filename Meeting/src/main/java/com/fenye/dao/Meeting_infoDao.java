package com.fenye.dao;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Meeting_infoDao {
    private static String meeting_subscriber1;

    public static String getMeeting_subscriber() {
        return meeting_subscriber1;
    }

    public static void setMeeting_subscriber(String meeting_subscriber) {
        Meeting_infoDao.meeting_subscriber1 = meeting_subscriber;
    }

    public boolean AddNewMeetingOrder(int room_id, String meeting_room_name, String meeting_name, String meeting_subscriber, String meeting_start, String meeting_end, int meeting_count)
    {
        boolean flag = false;
        Connection conn = null;
        try
        {
            conn = new DBClass().getConn();
            String sql = "insert into meeting_order(room_id,meeting_name,meeting_subscriber,meeting_start, meeting_end, meeting_order_time,meeting_count,meeting_describe,meeting_state,room_name) values(?,?,?,?,?,now(),?,'无',1,?)";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
//            preparedStatement.setInt(1, meeting_id);
            preparedStatement.setInt(1, room_id);
            preparedStatement.setString(2, meeting_name);
            preparedStatement.setString(3, meeting_subscriber1);
            preparedStatement.setString(4, meeting_start);
            preparedStatement.setString(5, meeting_end);
            preparedStatement.setString(7, meeting_room_name);
            preparedStatement.setInt(6, meeting_count);
//            preparedStatement.setInt(10, meeting_state);
//            preparedStatement.setString(11, meeting_cancel_res);
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                flag = true;
            } else {
                flag = false;
            }



        } catch (Exception e) {
            e.printStackTrace();
        }


        return flag;
    }

    public int AddMeetingPeople(int user_id)
    {
        int flag = 0;
        Connection conn = null;
        try {
            conn = new DBClass().getConn();
            String sql = "select count(*) from meeting_order";
            String sql1 = "insert into meeting_info(meeting_id, user_id) values(?,?)";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                preparedStatement1.setInt(1,rs.getInt(1));
                preparedStatement1.setInt(2,user_id);
                int i = preparedStatement1.executeUpdate();
                if (i > 0) {
                    flag = 1;
                }
                else {
                    flag = 0;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }
}
