package com.volunteer.persistencia.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String  URL= "jdbc:mysql://uj82vcwncpptt0rq:p8sKeNf9BCp2tFuNJVgM@bdiikrdpryb4n5u0pvd5-mysql.services.clever-cloud.com:3306/bdiikrdpryb4n5u0pvd5";
    private static final String  USER= "uj82vcwncpptt0rq";
    private static final String  PASSWORD= "p8sKeNf9BCp2tFuNJVgM";

    private DatabaseConnection() {}

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

