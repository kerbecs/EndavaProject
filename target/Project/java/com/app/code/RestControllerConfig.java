package com.app.code;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestControllerConfig {
    private static final Logger logger = LoggerFactory.getLogger(RestControllerConfig.class);

    @GetMapping("/customers")
    public List<Customer> getCustomers(){
        List<Customer> customers = RestAPIMethods.getCustomers();
        if(customers==null)
            throw new ItemNotFoundException("No customers were found",HttpStatus.NOT_FOUND);
        return customers;
    }

    @GetMapping("/customers/{id}")
    public Customer customer(@PathVariable int id){
        Customer customer = RestAPIMethods.getCustomer(id);

        if(customer==null)
            throw new ItemNotFoundException("Can't find customer with id  "+id,HttpStatus.NOT_FOUND);
        return customer;
    }
    @GetMapping("/customers/{firstName}/{lastName}")
    public List<Customer> customerName(@PathVariable String firstName,@PathVariable String lastName){
        List<Customer> customers = RestAPIMethods.getCustomer(firstName,lastName);

        if(customers==null)
            throw new ItemNotFoundException("Can't find customer(s) named "+firstName+" "+lastName,HttpStatus.NOT_FOUND);
        return customers;
    }
    @GetMapping("/products")
    public List<Product> getProducts(){
        List<Product> productList = RestAPIMethods.getProducts();
        if(productList==null)
            throw new ItemNotFoundException("Can't find any products",HttpStatus.NOT_FOUND);
        return productList;
    }
    @GetMapping("/orders/{id}")
    public Order getOrder(@PathVariable int id){
     Order order = RestAPIMethods.getOrder(id);
     if(order==null)
         throw new ItemNotFoundException("Can't find order with id "+id,HttpStatus.NOT_FOUND);
     return order;
    }
    @GetMapping("/orders")
    public void getOrders(){
        throw new ItemNotFoundException("Sorry, but you can't do that", HttpStatus.NOT_ACCEPTABLE);
    }

    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody User user){
        user.setUser_id(0);
        if(!RestAPIMethods.addUser(user)){
            throw new ItemNotFoundException("Something is wrong with your data. Verify your data and try again", HttpStatus.BAD_REQUEST);
        }

        return user.getCustomer();
    }

}
