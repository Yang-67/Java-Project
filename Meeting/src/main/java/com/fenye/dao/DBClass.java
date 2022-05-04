package com.fenye.dao;

import java.sql.Connection;
import java.sql.DriverManager;
public class DBClass {
    public Connection getConn() throws Exception
    {
        Connection conn;
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/meeting","root","1249");
        return conn;
    }
}
