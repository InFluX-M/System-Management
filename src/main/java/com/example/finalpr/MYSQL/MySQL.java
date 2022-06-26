package com.example.finalpr.MYSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQL {

    static public Boolean executeSQL(String sqlCMD){

        try {

            String URL = "jdbc:mysql://localhost/finalproject";
            String userName = "root";
            String password = "1382";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connector = DriverManager.getConnection(URL, userName, password);

            Statement statement = connector.prepareStatement(sqlCMD);
            statement.execute(sqlCMD);

            connector.close();
            return true;

        }catch (Exception e){
            return false;
        }

    }

    static public ResultSet executeQuery(String sqlCMD){

        try {

            String URL = "jdbc:mysql://localhost/finalproject";
            String userName = "root";
            String password = "1382";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connector = DriverManager.getConnection(URL, userName, password);

            Statement statement = connector.prepareStatement(sqlCMD);

            return statement.executeQuery(sqlCMD);

        }catch (Exception e){
            return null;
        }

    }

}
