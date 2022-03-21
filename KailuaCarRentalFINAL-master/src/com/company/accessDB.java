package com.company;
import java.sql.*;

public class accessDB {


    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        try {
            String dbURL = "jdbc:mysql://kailuacarrental.mysql.database.azure.com:3306/kailua_car_rental";
            String user = "kailua@kailuacarrental";
            String pass = "Gruppe4#";

            connection = DriverManager.getConnection(dbURL, user, pass);
            if (connection != null) {
                DatabaseMetaData dm = connection.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        new accessDB().run();
    }

    public void run()throws SQLException{
        Controller controller = new Controller();
        controller.selectDatabase();
    }
}
