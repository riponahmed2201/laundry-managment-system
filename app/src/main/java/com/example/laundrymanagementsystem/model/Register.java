package com.example.laundrymanagementsystem.model;

public class Register {

    private String fullName;
    private String email;
    private String address;
    private String roleName;
    private String phoneNumber;
    private String password;

    public Register(String fullName, String email, String address, String roleName, String phoneNumber, String password) {
        this.fullName = fullName;
        this.email = email;
        this.address = address;
        this.roleName = roleName;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
