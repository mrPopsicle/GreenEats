package com.example.greeneats;

import com.example.greeneats.User;

import java.util.HashMap;
import java.util.Map;

abstract class UserService {
    protected Map<String, User> users;
    protected static final String DELIMITER = ",";

    public UserService() {
        users = new HashMap<>();
    }

    protected abstract void loadUsers();
    protected abstract boolean saveUsers();

    public boolean addUser(User newUser) {
        if (users.containsKey(newUser.getUsername().toLowerCase())) {
            return false;
        }
        users.put(newUser.getUsername().toLowerCase(), newUser);
        return saveUsers();
    }

    public boolean validateUser(String username, String password) {
        User user = users.get(username.toLowerCase());
        return user != null && user.getPassword().equals(password);
    }
}
