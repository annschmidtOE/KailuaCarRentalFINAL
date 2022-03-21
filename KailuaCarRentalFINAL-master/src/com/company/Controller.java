package com.company;

import java.sql.*;
import java.util.Scanner;

public class Controller {

    private final UI ui = new UI();

    public void selectDatabase() throws SQLException{
        Scanner scan = new Scanner(System.in);
        ui.selectDatabase();
        while (scan.hasNextInt()) {
            int choice = scan.nextInt();
            ui.selectDatabase();
            switch (choice) {
                case 1: cars();
                case 2: customer();
                case 3: rentalContracts();
                default:
                    System.out.println("invalid input \ntry again"); } } }

    public void rentalContracts()throws SQLException{
        Scanner scan = new Scanner(System.in);
        RentalClass rentalClass = new RentalClass();
        ui.rentalDeals();
        while (scan.hasNextInt()) {
            int choice = scan.nextInt();
            switch (choice) {
                case 1: rentalClass.printtRentalContracts(); ui.rentalDeals(); break;
                case 2: rentalClass.addRentalContract(); ui.rentalDeals(); break;
                case 3: rentalClass.updateRentalContract(); ui.rentalDeals();break;
                case 4: rentalClass.deleteRentalContract(); ui.rentalDeals(); break;
                case 5: rentalClass.customerDetails(); ui.rentalDeals(); break;
                case 6: rentalClass.carDetails();ui.rentalDeals();  break;
                case 9: selectDatabase();
                default:
                    System.out.println("invalid input\n try again"); } } }

    public void customer() throws SQLException{
        Scanner scan = new Scanner(System.in);
        CustomerClass customerClass = new CustomerClass();
        ui.customers();
        while (scan.hasNextInt()) {
            int choice = scan.nextInt();
            switch (choice) {
                case 1: customerClass.selectCustomers(); ui.customers(); break;
                case 2: customerClass.addCustomer(); ui.customers();break;
                case 3: customerClass.updateCustomer(); ui.customers();break;
                case 4: customerClass.deleteCustomer(); ui.customers();break;
                case 9: selectDatabase();
                default:
                    System.out.println("invalid input\n try again");
            } } }

    public void cars()throws SQLException {
        Scanner scan = new Scanner(System.in);
        CarClass carClass = new CarClass();
        ui.cars();
        while (scan.hasNextInt()) {
            int choice = scan.nextInt();
            switch (choice) {
                case 1: carClass.printCars();  ui.cars(); break;
                case 2: carClass.addCar(); ui.cars(); break;
                case 3: carClass.updateCar(); ui.cars();break;
                case 4: carClass.deleteCar(); ui.cars(); break;
                case 9: selectDatabase();
                default:
                    System.out.println("invalid input\n try again");
            } } }


    }


