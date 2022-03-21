package net.codejava.jdbc;
import java.sql.*;


public class Test {

    static final String DATABASE_URL = "jdbc:mysql://localhost/kailua_car_rental";


    public static void main(String[] args) throws SQLException {

        Connection conn = null;

        try {

            String dbURL = "jdbc:mysql://kailuacarrental.mysql.database.azure.com:3306/kailua_car_rental";
            String user = "kailua@kailuacarrental";
            String pass = "Gruppe4#";

            conn = DriverManager.getConnection(dbURL, user, pass);
            if (conn != null) {
                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
         new Test().rentalDataCar();
        new Test().rentalDataCustomer();
    }



    public void selectCustomers() throws SQLException {
        Connection con = DriverManager.getConnection(DATABASE_URL, "root", "Gdk94xjs#");
        Statement statement = con.createStatement();
        ResultSet resultSet;

        resultSet = statement.executeQuery("SELECT * FROM customer");
        while (resultSet.next()) {
            String customerID = resultSet.getString("customer_id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String zipCode = resultSet.getString("zip_code");
            String city = resultSet.getString("city");
            String mobile = resultSet.getString("mobile_phone");
            String phone = resultSet.getString("phone");
            String email = resultSet.getString("email");
            String driverLicenceNumber = resultSet.getString("driver_licence_number");
            String driverSince = resultSet.getString("driver_since_date");

            System.out.println(firstName + " " + lastName + " " + zipCode + " " + city + " " +
                    mobile + " " + phone + " " + email + " " + driverLicenceNumber + " " + driverSince);
        }
        con.close();
    }

    public void rentalDataCustomer() throws SQLException {
        Connection con = DriverManager.getConnection(DATABASE_URL, "root", "Gdk94xjs#");
        Statement statement = con.createStatement();
        ResultSet resultSet;
        resultSet = statement.executeQuery("SELECT customer_id, first_name, last_name," +
                "zip_code, city, mobile_phone, driver_licence_number\n" +
                "FROM customer ");
        while (resultSet.next()) {
            String customerID = resultSet.getString("customer_id");
            System.out.println(customerID);
        }
    }

    public void rentalDataCar() throws SQLException {
        Connection con = DriverManager.getConnection(DATABASE_URL, "root", "Gdk94xjs#");
        Statement statement = con.createStatement();
        ResultSet resultSet;
        resultSet = statement.executeQuery("SELECT \n" +
                "car_id, brand, model, fuel, registration_number, km_driven\n" +
                "FROM cars ");
        while (resultSet.next()) {
            //CAR
            String brand = resultSet.getString("brand");
            String carID = resultSet.getString("car_id");
            String model = resultSet.getString("model");
            String fuel = resultSet.getString("fuel");
            String registrationNumber = resultSet.getString("registration_number");
            String kmDriven = resultSet.getString("km_driven");
            String rentalDataCar = (carID + " "+brand + " " + model+" "+fuel+" "+registrationNumber+" "+kmDriven);
            System.out.println(rentalDataCar);
        }
        con.close();
    }

}






