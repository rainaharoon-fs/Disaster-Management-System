/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.example.oopassignment;

import java.util.ArrayList;
import java.util.Scanner;

public class ComExampleOopassignment {
    public static void main(String[] args) {
        ArrayList<Disaster> disasters = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nWelcome to this App!");
            System.out.print("Enter your role (admin/user/exit): ");
            String role = sc.nextLine();

            if (role.equalsIgnoreCase("admin")) {
                
                
                if (!Authentic.authenticate()) {
                    System.out.println("Oops something went wrong. Access denied.");
                    continue;
                }
                
                
                
               
                Admin admin = new Admin(role);
                if (admin.menu()) {disasters = admin.getDisasters();continue;}
            } else if (role.equalsIgnoreCase("user")) {
                NormalUser user = new NormalUser(role,disasters);
                if (user.menu()) continue;
            } else if (role.equalsIgnoreCase("exit")) {
                System.out.println("we hope to see you again!");
                break;
            } else {
                System.out.println("Invalid role. Try again.");
            }
        }
        }
    }
// Disaster class to store disaster information
class Disaster {
    private String name;
    private String location;
    private String description;

    public Disaster(String name, String location, String description) {
        this.name = name;
        this.location = location;
        this.description = description;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void print() {
        System.out.println("Disaster: " + name + ", Location: " + location + ", Description: " + description);
    }
}

// Admin class
class Admin{
    private ArrayList<Disaster> disasters;
    private String username;

    public Admin(String username) {
        this.username = username;
        disasters = new ArrayList<>();
    }
    public ArrayList<Disaster> getDisasters() {
        return disasters;
    }
    public void addDisaster() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter disaster name: ");
        String name = sc.nextLine();
        System.out.print("Enter location: ");
        String location = sc.nextLine();
        System.out.print("Enter description: ");
        String description = sc.nextLine();
        disasters.add(new Disaster(name, location, description));
        System.out.println("Disaster added successfully.");
    }
    public void updateDisaster() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter disaster name to update: ");
        String name = sc.nextLine();
        for (Disaster disaster : disasters) {
            if (disaster.getName().equalsIgnoreCase(name)) {
                System.out.print("Enter new location: ");
                disaster.setLocation(sc.nextLine());
                System.out.print("Enter new description: ");
                disaster.setDescription(sc.nextLine());
                System.out.println("Disaster updated successfully.");
                return;
            }
        }
        //System.out.println("Disaster not found.");
    }
    public void deleteDisaster() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter disaster name to delete: ");
        String name = sc.nextLine();
        disasters.removeIf(disaster -> disaster.getName().equalsIgnoreCase(name));
        System.out.println("Disaster deleted successfully.");
    }
    public void viewDisasters() {
        if (disasters.isEmpty()) {
            System.out.println("No disasters available.");
        } else {
            for (Disaster disaster : disasters) {
                disaster.print();           
            }
        }
    }
    public boolean menu() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Add Disaster");
            System.out.println("2. Update Disaster");
            System.out.println("3. Delete Disaster");
            System.out.println("4. View Disasters");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume newline
            switch (choice) {
                case 1 -> addDisaster();
                case 2 -> updateDisaster();
                case 3 -> deleteDisaster();
                case 4 -> viewDisasters();
                case 5 -> {
                    System.out.println("Returning to Login Menu...");
                    return true;
                }                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (true);
    }
}
// NormalUser class
class NormalUser {
    private ArrayList<Disaster> disasters;
        private String username;

    public NormalUser(String username, ArrayList<Disaster> disasters) {
        this.username = username;
        this.disasters = disasters;
    }
    public void viewDisasters() {
        if (disasters.isEmpty()) {
            System.out.println("No disasters available.");
        } else {
            for (Disaster disaster : disasters) {
                disaster.print();
            }
        }
    }
    public void searchDisaster() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter disaster name to search: ");
        String name = sc.nextLine();
        boolean found = false;
        for (Disaster disaster : disasters) {
            if (disaster.getName().equalsIgnoreCase(name)) {
                disaster.print();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Disaster not found.");
        }
    }
    public boolean menu() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nNormal User Menu:");
            System.out.println("1. View Disasters");
            System.out.println("2. Search Disaster");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume newline
            switch (choice) {
                case 1 -> viewDisasters();
                case 2 -> searchDisaster();
                case 3 -> {
                    System.out.println("Returning to Login Menu...");
                    return true;
                }                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (true);
    }
}
