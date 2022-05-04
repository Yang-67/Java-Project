package com.fenye.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class MeetingRoomDao {
    public boolean AddMeetingRoom(int room_id, String room_name, int room_count, int room_state, String room_declare)
    {
        boolean flag = false;
        Connection conn = null;
        try {
            conn = new DBClass().getConn();
//            insert into topic(id_user,title,content) values(?,?,?)
            String sql = "insert into meeting_room(room_id, room_name, room_count, room_state, room_declare) values(?,?,?,?,?) ";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, room_id);
            preparedStatement.setString(2, room_name);
            preparedStatement.setInt(3, room_count);
            preparedStatement.setInt(4, room_state);
            preparedStatement.setString(5, room_declare);
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                flag = true;
            } else {
                flag = false;
            }
            return flag;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
