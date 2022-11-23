package com.app.code;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "user_detail")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private int user_id;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Size(min = 5,message = "At least 5 characters are required")
    @Column(name="username")
    private String username;

    @Size(min = 6,message = "At least 6 characters are required")
    @Column(name = "password")
    private String password;

    public User(){ }

    public User(Customer customer) {
        this.customer = customer;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
