package com.example.laundrymanagementsystem.model;

public class VendorList {

    private String vendorId;
    private String vendorName;
    private String vendorEmail;

    public VendorList(String vendorId, String vendorName, String vendorEmail) {
        this.vendorId = vendorId;
        this.vendorName = vendorName;
        this.vendorEmail = vendorEmail;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorEmail() {
        return vendorEmail;
    }

    public void setVendorEmail(String vendorEmail) {
        this.vendorEmail = vendorEmail;
    }
}
