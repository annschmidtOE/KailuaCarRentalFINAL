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
                case 7: searchRentalContract();
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
                case 5: searchCustomer();
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
                case 5: carClass.searchByCarBrandd();
                case 9: selectDatabase();
                default:
                    System.out.println("invalid input\n try again");
            } } }

    public void searchRentalContract()throws SQLException{
        RentalClass rentalClass = new RentalClass();
        Scanner scan = new Scanner(System.in);
        ui.searchRental();
        while (scan.hasNext()) {
            int choice = scan.nextInt();
            switch (choice) {
                case 1: rentalClass.searchByID(); ui.searchRental(); break;
                case 2: rentalClass.searchByCustomerID(); ui.searchRental(); break;
                case 3: rentalClass.searchByCarID(); ui.searchRental(); break;
                case 9: Controller controller = new Controller(); controller.rentalContracts(); break;
                default: System.out.println("invalid input");
            }} }

    public void searchCustomer()throws SQLException{
        CustomerClass customerClass = new CustomerClass();
        Scanner scan = new Scanner(System.in);
        ui.searchCustomer();
        while (scan.hasNext()) {
            int choice = scan.nextInt();
            switch (choice) {
                case 1: customerClass.searchCustomerById(); ui.searchCustomer();break;
                case 2: customerClass.searchCustomerByFirstName(); ui.searchCustomer();break;
                case 3: customerClass.searchCustomerByLastName(); ui.searchCustomer();break;
                case 4: customerClass.searchCustomerByCity(); ui.searchCustomer();break;
                case 5: customerClass.searchCustomerByZip(); ui.searchCustomer();break;
                case 9: customer();
            }
        }
    }

    public void searchCars()throws SQLException{
        CarClass carClass = new CarClass();
        Scanner scan = new Scanner(System.in);
        ui.searchCustomer();
        while (scan.hasNext()) {
            int choice = scan.nextInt();
            switch (choice) {
                case 1: carClass.searchByModel();
                case 2: carClass.searchByCarBrandd();
                case 3:
                case 4:
                case 5:
                case 9: cars();
            }
        }
    }

    }


