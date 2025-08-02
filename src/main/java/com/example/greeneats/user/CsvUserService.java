package com.example.greeneats.user;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class CsvUserService extends UserService {
    private static final String EXTERNAL_CSV_FILE = "data/users_external.csv";
    private static final String DELIMITER = ",";

    private final Path externalFilePath;

    public CsvUserService() {
        externalFilePath = Paths.get(EXTERNAL_CSV_FILE);
        loadUsers();
    }

    @Override
    protected void loadUsers() {
        users.clear();

        if (Files.exists(externalFilePath)) {
            try (BufferedReader br = Files.newBufferedReader(externalFilePath)) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] userData = line.split(DELIMITER);
                    if (userData.length >= 4) {
                        User user = new User(
                                userData[0].trim(),
                                userData[1].trim(),
                                userData[2].trim(),
                                userData[3].trim()
                        );
                        users.put(user.getUsername().toLowerCase(), user);
                    }
                }
                System.out.println("Loaded " + users.size() + " users from external file.");
                return;
            } catch (IOException e) {
                System.err.println("Error reading external users file: " + e.getMessage());
            }
        }


    }


    @Override
    protected boolean saveUsers() {
        try (BufferedWriter bw = Files.newBufferedWriter(externalFilePath)) {
            writeUsersToFile(bw);
            System.out.println("Saved users to external file: " + externalFilePath);
            return true;
        } catch (IOException e) {
            System.err.println("Error saving users: " + e.getMessage());
            return false;
        }
    }

    private void writeUsersToFile(BufferedWriter bw) throws IOException {
        for (User user : users.values()) {
            String line = String.join(DELIMITER,
                    user.getUsername(),
                    user.getPassword(),
                    user.getFirstName(),
                    user.getLastName());
            bw.write(line);
            bw.newLine();
        }
    }

    private void initializeDefaultUsersFile() {
        System.out.println("Creating default users...");
        users.put("admin", new User("admin", "admin123", "Admin", "User"));
        users.put("user", new User("user", "password", "Regular", "User"));
        saveUsers();
    }
}
