package com.example.orderhibfx.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {
    static private Connection con = null;

    static {
        //TODO: Cambiar el usuario y contraseña si es necesario
        String url = "jdbc:mysql://localhost:3306/breakfast";
        String user = "newuser";
        String password = "newuser";
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Conexion Satisfactoria");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static Connection getConnection() {
        return con;
    }
}
