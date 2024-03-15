package com.cloudbees.demo.Model;
import lombok.*;
@Getter
@Setter
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String section;
    private String seat;

    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", section='" + section + '\'' +
                ", seat='" + seat + '\'' +
                '}';
    }
}
