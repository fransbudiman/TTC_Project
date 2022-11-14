package com.example.ttcsc;

public class User implements Comparable {
    private String username;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Override
    public int compareTo(Object o) {
        User other = (User) o;
        return this.username.compareTo(other.username);
    }
}
