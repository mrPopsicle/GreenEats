package com.example.greeneats.user;

public class UserSession {
    private static UserSession instance;
    private User currentUser;

    private UserSession() {}

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
            System.out.println("UserSession instance created."); // Check creation
        }
        return instance;
    }

    public void loginUser(User user) {
        this.currentUser = user;
        System.out.println("User logged in: " + user.getUsername()); //Check login
    }

    public void logout() {
        if (currentUser != null) {
            System.out.println("Logging out user: " + currentUser.getUsername()); //Check logout
        } else {
            System.out.println("No user is currently logged in."); // Optional
        }
        this.currentUser = null;
    }

    public User getCurrentUser() {
        if (currentUser != null) {
            System.out.println("Getting current user: " + currentUser.getUsername());
        } else {
            System.out.println("Getting current user: none");
        }
        return currentUser;
    }

    public String getCurrentUsername() {
        String username;
        if (currentUser != null) {
            username = currentUser.getUsername();
        } else {
            username = null;
        }

        return username;
    }

    public boolean isLoggedIn() {
        boolean loggedIn = currentUser != null;
        System.out.println("Is user logged in? " + loggedIn);
        return loggedIn;
    }
}
