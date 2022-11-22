package com.app.code;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

public class TestApp {
    public static void main(String[] args) throws SQLException, ParseException {
        SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Customer.class).
                addAnnotatedClass(User.class).addAnnotatedClass(Order.class).
                addAnnotatedClass(Product.class).buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        User user = new User("kerbecs","test123");
        Customer customer = new Customer("Eduard","Mititiuc","edikutsu2002@mail.ru",
                "Chisinau","Strada Viilor 5A");
        user.setCustomer(customer);
        Product product = new Product("Pizza","Mozarela, Sale",8,340);
        String timestamp = String.valueOf(new Date());
        System.out.println(timestamp);

        Order order = new Order(timestamp,customer,product.getPrice(),product);
System.out.println(timestamp);


        try {
        session.beginTransaction();
        session.save(customer);
        session.save(user);
        session.save(product);
        session.save(order);

        session.getTransaction().commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

}
