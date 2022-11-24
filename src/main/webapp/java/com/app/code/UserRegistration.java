package com.app.code;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.*;
import javax.validation.constraints.*;

public class UserRegistration {

    @Size(min = 5,message = "At least 5 char required")
    private String username;
    @Size(min = 6,message = "At least 6 char required")
    private String password;

    private String password1;
    @Size(min = 6,message = "At least 6 char required")

    private String password2;

    @Size(min = 6,message = "At least 6 char required")
    private String firstName;

    @Size(min = 2,max = 20,message = "At least 2 char required or max 20")
    private String lastName;

    @Size(min = 5,max = 25,message = "Enter a valid mail")
    @Pattern(regexp = "^*@*.*")
    private String email;

    @Size(min = 2,max = 20,message = "Enter a valid location")
    private String location;

    @Size(min = 2,max = 20,message = "Enter a valid address")
    private String address;

    @Size(min = 2,message = "At least 2 char required")
    private String job;
    private int orders;

    public UserRegistration() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public boolean checkPassword(){
        return getPassword1().equals(getPassword2());
    }

    @Override
    public String toString() {
        return "UserRegistration{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", password1='" + password1 + '\'' +
                ", password2='" + password2 + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", location='" + location + '\'' +
                ", address='" + address + '\'' +
                ", job='" + job + '\'' +
                ", orders=" + orders +
                '}';
    }

    public static void createUser(UserRegistration userRegistration){
        AnnotationConfigApplicationContext config = new AnnotationConfigApplicationContext(appContext.class);
        Customer customer = config.getBean("customer", Customer.class);
        User user = config.getBean("user",User.class);

        customer.setAddress(userRegistration.getAddress());
        customer.setEmail(userRegistration.getEmail());
        customer.setFirstName(userRegistration.getFirstName());
        customer.setLastName(userRegistration.getLastName());
        customer.setLocation(userRegistration.getLocation());

        user.setCustomer(customer);
        user.setPassword(userRegistration.getPassword1());
        user.setUsername(userRegistration.getUsername());

        HibernateClass.addNewUser(user,customer);
    }

    public void copy(User user, Customer customer){
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.email = customer.getEmail();
        this.location = customer.getLocation();
        this.address = customer.getAddress();
        this.job = customer.getJob();
        this.orders = customer.getOrders();
    }

}
