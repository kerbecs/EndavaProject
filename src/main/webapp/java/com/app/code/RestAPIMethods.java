package com.app.code;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.security.core.parameters.P;

import java.util.List;

public class RestAPIMethods {
    public static List<Customer> getCustomers(){
        SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(User.class).addAnnotatedClass(Customer.class).buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();
        Query query = session.createQuery("SELECT firstName,lastName,email,job,orders FROM Customer ORDER BY firstName,lastName");

        List<Customer> customers = query.getResultList();
        session.getTransaction().commit();
        session.close();

         return customers;
    }
    public static Customer getCustomer(int id){
        SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(User.class).addAnnotatedClass(Customer.class).buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();

        Customer customer = session.get(Customer.class,id);
        session.getTransaction().commit();
        session.close();

        return customer;
    }
    public static List<Customer> getCustomer(String firstName, String lastName){
        SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(User.class).addAnnotatedClass(Customer.class).buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();

        Query query = session.createQuery("FROM Customer WHERE firstName = ?0 AND lastName = ?1").setParameter(0,firstName).setParameter(1,lastName);
        List<Customer> customers = query.getResultList();
        session.getTransaction().commit();
        session.close();

        return customers;
    }
    public static List<Product> getProducts(){
        SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Product.class).buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();

        Query query = session.createQuery("FROM Product");
        List<Product> products = query.getResultList();
        session.getTransaction().commit();
        session.close();

        return products;
    }
    public static Order getOrder(int id){
        SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Product.class).addAnnotatedClass(User.class).addAnnotatedClass(Order.class).addAnnotatedClass(Customer.class).buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();
        Order order = session.get(Order.class,id);

        session.beginTransaction();

        session.close();
        return order;
    }
    public static boolean addUser(User user){
        SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Product.class).addAnnotatedClass(User.class).addAnnotatedClass(Order.class).addAnnotatedClass(Customer.class).buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();
            session.saveOrUpdate(user);

            session.beginTransaction();
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        finally {
            session.close();

        }
        return true;
    }

}
