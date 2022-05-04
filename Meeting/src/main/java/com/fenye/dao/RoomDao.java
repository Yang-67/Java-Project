package com.fenye.dao;

import com.fenye.JavaBean.MeetingRoom;
import com.fenye.entity.MeetingOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoomDao {
    public List<MeetingRoom> searchRoom(String starttime, String endtime) {
        List<MeetingRoom> list = new ArrayList<MeetingRoom>();
        Connection connection=null;
        MeetingRoom meeting_room=null;
        try{
            connection = new DBClass().getConn();
            PreparedStatement preparedstatement = connection.prepareStatement("SELECT * FROM meeting_room WHERE room_id NOT IN ( SELECT room_id FROM meeting_order WHERE meeting_start between '"+starttime+"' and '"+endtime+"' or meeting_end between '"+starttime+"' and '"+endtime+"')");
//            preparedstatement.setString(1,starttime);
//            preparedstatement.setString(2,endtime);
//            preparedstatement.setString(3,starttime);
//            preparedstatement.setString(4,endtime);
//            System.out.println(starttime+endtime);
            ResultSet rs = preparedstatement.executeQuery();
            ResultSet resultSet = preparedstatement.executeQuery();
            while(resultSet.next()) {
                System.out.println(1);
                System.out.println(resultSet.getInt("room_id"));
                System.out.println(resultSet.getString("room_name"));
                meeting_room = new MeetingRoom();
                meeting_room.setRoom_id(resultSet.getInt("room_id"));
                meeting_room.setRoom_name(resultSet.getString("room_name"));
                list.add(meeting_room);
            }
            connection.close();
            preparedstatement.close();
            rs.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
