package com.example.shopdrop.models;

public class User {
    private int userId;
    private String email;
    private String password;
    private String name;
    private String contactNumber;
    private String role;



    public User(int userId, String email, String password, String name, String contactNumber, boolean enabled) {
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.name = name;
        this.role = "ROLE_USER";
        this.contactNumber = contactNumber;
    }


    public User() {
        this.role = "ROLE_USER";
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setRole(String role) {
        this.role = role;
    }



    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getRole() {
        return role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    //@Override
    //public String toString() {
      //  return this.userName + "/" + this.password;
    //}

}
