/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.oopassignment;



import java.util.Scanner;

public class Authentic{

    private static final String ADMIN_PASSWORD_HASH;

    static {
        
        Password.PBKDF2Strategy strategy =
                new Password.PBKDF2Strategy(16, 100000, 256, "PBKDF2WithHmacSHA256");
        ADMIN_PASSWORD_HASH = strategy.hash("password111");
    }

    /**
     *
     * @return
     */
    public static boolean authenticate() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter admin password: ");
        String inputPassword = sc.nextLine();

        Password.PBKDF2Strategy strategy =
                new Password.PBKDF2Strategy(16, 100000, 256, "PBKDF2WithHmacSHA256");

        return strategy.verify(inputPassword, ADMIN_PASSWORD_HASH);
    }
}
