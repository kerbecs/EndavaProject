package com.app.code;

public class CustomerError {
    private String firstName;
    private String lastName;
    private int orders;

    public CustomerError(String firstName, String lastName, int orders) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.orders = orders;
    }

    public CustomerError() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }
}
