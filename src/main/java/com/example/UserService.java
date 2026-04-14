package com.example;

import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UserService {

    private final Map<String, String> verificationCodes = new HashMap<>();

    public boolean authenticate(String username, String password) {
        // Lógica de autenticación
        return true; // Por ahora retorna true
    }

    public boolean registerUser(String name, String lastName, String email, String password) {
        // Lógica de registro
        return true;
    }

    public String generateVerificationCode(String email) {
        String code = UUID.randomUUID().toString().substring(0, 6);
        verificationCodes.put(email, code);
        // Aquí enviarías el código por email
        return code;
    }

    public boolean verifyCode(String email, String code) {
        return verificationCodes.containsKey(email) && verificationCodes.get(email).equals(code);
    }

    public boolean updatePassword(String email, String newPassword) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("src/main/resources/loginData"));

            if (lines.size() >= 3) {
                String existingEmail = lines.get(2);

                if (existingEmail.equals(email)) {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/loginData"));
                    writer.write(lines.get(0));
                    writer.newLine();
                    writer.write(newPassword);
                    writer.newLine();
                    writer.write(existingEmail);
                    writer.close();
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}