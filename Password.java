/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.oopassignment;



import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Password {

    public static class PBKDF2Strategy {

        private final int saltLength;
        private final int iterations;
        private final int keyLength;
        private final String algorithm;

        public PBKDF2Strategy(int saltLength, int iterations, int keyLength, String algorithm) {
            this.saltLength = saltLength;
            this.iterations = iterations;
            this.keyLength = keyLength;
            this.algorithm = algorithm;
        }

        public String hash(String password) {
            if (password == null || password.isEmpty()) {
                throw new IllegalArgumentException("Password cannot be null or empty.");
            }
            byte[] salt = GenerateSalt();
            byte[] hash = GenerateHash(password, salt);

            return Base64.getEncoder().encodeToString(salt) + ":" + Base64.getEncoder().encodeToString(hash);
        }

        public boolean verify(String password, String storedHash) {
            if (password == null || password.isEmpty() || storedHash == null || storedHash.isEmpty()) {
                throw new IllegalArgumentException("Incorrect input.");
            }

            String[] parts = storedHash.split(":");
            if (parts.length != 2) {
                throw new IllegalArgumentException("Please enter a valid stored hash format.");
            }

            byte[] salt = Base64.getDecoder().decode(parts[0]);
            byte[] expectedHash = Base64.getDecoder().decode(parts[1]);
            byte[] actualHash = GenerateHash(password, salt);

            return MessageDigest.isEqual(expectedHash, actualHash);
        }

        private byte[] GenerateSalt() {
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[saltLength];
            random.nextBytes(salt);
            return salt;
        }

        private byte[] GenerateHash(String password, byte[] salt) {
            try {
                PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, keyLength);
                SecretKeyFactory factory = SecretKeyFactory.getInstance(algorithm);
                return factory.generateSecret(spec).getEncoded();
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                throw new RuntimeException("Error while hashing the password.", e);
            }
        }
    }
}
