package com.app.code;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id")
    private int id;

    @Column(name="first_name")
    @Size(min = 2,max = 20,message = "Enter a first name between 2 and 20")
    private String firstName;

    @Column(name = "last_name")
    @Size(min = 2,max = 20,message = "Enter a last name between 2 and 20")
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name = "location")
    private String location;

    @Column(name="address")
    private String address;

    @Column(name="job")
    private String job;

    @Column(name="orders")
    private int orders;


    public Customer() { }

    public Customer(String firstName, String lastName, String email, String location, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.location = location;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", location='" + location + '\'' +
                ", address='" + address + '\'' +
                ", job='" + job + '\'' +
                ", orders=" + orders +
                '}';
    }

}
