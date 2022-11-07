package com.example.laundrymanagementsystem.model;

public class Order {
    public String orderId;
    public String vendorEmail;
    public String userEmail;
    public String paymentOption;
    public String garmentCategory;
    public String garmentQuantity;
    public String orderPlacement;
    public String status;

    public Order(String orderId, String vendorEmail, String userEmail, String paymentOption, String garmentCategory, String garmentQuantity, String orderPlacement, String status) {
        this.orderId = orderId;
        this.vendorEmail = vendorEmail;
        this.userEmail = userEmail;
        this.paymentOption = paymentOption;
        this.garmentCategory = garmentCategory;
        this.garmentQuantity = garmentQuantity;
        this.orderPlacement = orderPlacement;
        this.status = status;
    }

    public Order(String userEmail, String paymentOption, String garmentCategory, String garmentQuantity, String orderPlacement, String status) {
        this.vendorEmail = vendorEmail;
        this.userEmail = userEmail;
        this.paymentOption = paymentOption;
        this.garmentCategory = garmentCategory;
        this.garmentQuantity = garmentQuantity;
        this.orderPlacement = orderPlacement;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getVendorEmail() {
        return vendorEmail;
    }

    public void setVendorEmail(String vendorEmail) {
        this.vendorEmail = vendorEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPaymentOption() {
        return paymentOption;
    }

    public void setPaymentOption(String paymentOption) {
        this.paymentOption = paymentOption;
    }

    public String getGarmentCategory() {
        return garmentCategory;
    }

    public void setGarmentCategory(String garmentCategory) {
        this.garmentCategory = garmentCategory;
    }

    public String getGarmentQuantity() {
        return garmentQuantity;
    }

    public void setGarmentQuantity(String garmentQuantity) {
        this.garmentQuantity = garmentQuantity;
    }

    public String getOrderPlacement() {
        return orderPlacement;
    }

    public void setOrderPlacement(String orderPlacement) {
        this.orderPlacement = orderPlacement;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
