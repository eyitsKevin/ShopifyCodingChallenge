package com.example.shopifycoding.ShopifyCodingChallenge.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connector {

    private ResultSet resultSet;
    private Connection connection;
    private Statement statement;

    public Connector(ResultSet resultSet, Connection connection, Statement statement){
        this.resultSet = resultSet;
        this.connection = connection;
        this.statement = statement;
    }

    // Getters
    public Connection getConnection(){
        return this.connection;
    }
    public ResultSet getResultSet(){
        return this.resultSet;
    }
    public Statement getStatement() { return this.statement; }


    // closes all connection
    // mainly used in other classes after having done their queries
    public void close(){
        try{
            this.statement.close();
            this.connection.close();
            this.resultSet.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
