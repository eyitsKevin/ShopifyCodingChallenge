package com.example.shopifycoding.ShopifyCodingChallenge.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbConnection {

    // returns a Connector Object containing the connection, the resultset and the statement
    public static Connector get(String query){
        Connector conn = null;
        try {
            Connection con = connect();
            // Database Connection
            Statement stmt = con.createStatement();
            // SQL Query to search
            ResultSet rs = stmt.executeQuery(query);

            conn = new Connector(rs, con, stmt);
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return conn;
    }

    public static Connection connect(){
        Connection connection = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:hsqldb:file:K:/My Workspace/ShopifyCodingChallenge/src/main/java/com/example/shopifycoding/ShopifyCodingChallenge/connection\\marketplacedb/", "user", "useruser");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }
}
