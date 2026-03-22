package model;

import java.io.Serializable;

// This class holds basic info about who's using the app
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }

    // quick check while testing
    @Override
    public String toString() {
        return "User[" + name + " | " + email + "]";
    }
}