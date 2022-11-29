package com.app.code;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.parameters.P;

import java.util.List;

public class RestAPIMethods {
    private static final Logger logger = LoggerFactory.getLogger(RestAPIMethods.class);
    public static List<Customer> getCustomers(){
        SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(User.class).addAnnotatedClass(Customer.class).buildSessionFactory();
        logger.info("A sessionFactory was successfully created.");
        Session session = sessionFactory.getCurrentSession();
        logger.info("Session was successfully created.");


        session.beginTransaction();
        logger.info("Transaction for session was successfully started");

        Query query = session.createQuery("SELECT firstName,lastName,email,job,orders FROM Customer ORDER BY firstName,lastName");

        List<Customer> customers = query.getResultList();
        session.getTransaction().commit();
        logger.info("Session was successfully committed");
        session.close();
        logger.info("Transaction for session was successfully closed");

        return customers;
    }
    public static Customer getCustomer(int id){
        SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(User.class).addAnnotatedClass(Customer.class).buildSessionFactory();
        logger.info("A sessionFactory was successfully created.");
        Session session = sessionFactory.getCurrentSession();
        logger.info("Session was successfully created.");

        session.beginTransaction();

        Customer customer = session.get(Customer.class,id);
        session.getTransaction().commit();
        logger.info("Session was successfully committed");
        session.close();
        logger.info("Transaction for session was successfully closed");

        return customer;
    }
    public static List<Customer> getCustomer(String firstName, String lastName){
        SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(User.class).addAnnotatedClass(Customer.class).buildSessionFactory();
        logger.info("A sessionFactory was successfully created.");

        Session session = sessionFactory.getCurrentSession();
        logger.info("Session was successfully created.");

        session.beginTransaction();
        logger.info("Transaction for session was successfully started");


        Query query = session.createQuery("FROM Customer WHERE firstName = ?0 AND lastName = ?1").setParameter(0,firstName).setParameter(1,lastName);
        List<Customer> customers = query.getResultList();
        session.getTransaction().commit();
        logger.info("Session was successfully committed");
        session.close();
        logger.info("Transaction was successfully closed");

        return customers;
    }
    public static List<Product> getProducts(){
        SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Product.class).buildSessionFactory();
        logger.info("A sessionFactory was successfully created.");

        Session session = sessionFactory.getCurrentSession();
        logger.info("Session was successfully created.");

        session.beginTransaction();

        Query query = session.createQuery("FROM Product");
        List<Product> products = query.getResultList();
        session.getTransaction().commit();
        logger.info("Session was successfully committed");
        session.close();
        logger.info("Transaction for session was successfully closed");

        return products;
    }
    public static Order getOrder(int id){
        SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Product.class).addAnnotatedClass(User.class).addAnnotatedClass(Order.class).addAnnotatedClass(Customer.class).buildSessionFactory();
        logger.info("A sessionFactory was successfully created.");

        Session session = sessionFactory.getCurrentSession();
        logger.info("Session was successfully created.");

        session.beginTransaction();
        logger.info("Transaction for session was successfully started");

        Order order = session.get(Order.class,id);

        session.getTransaction().commit();
        logger.info("Session was successfully committed");


        session.close();
        logger.info("Transaction for session was successfully closed");

        return order;
    }
    public static boolean addUser(User user){
        SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Product.class).addAnnotatedClass(User.class).addAnnotatedClass(Order.class).addAnnotatedClass(Customer.class).buildSessionFactory();
        logger.info("A sessionFactory was successfully created.");

        Session session = sessionFactory.getCurrentSession();
        logger.info("Session was successfully created.");

        try {
            session.beginTransaction();
            logger.info("Transaction for session was successfully started");

            session.saveOrUpdate(user);
            session.getTransaction().commit();
            logger.info("Session was successfully committed");

        }
        catch (Exception e){
            logger.info(e.getMessage());
            e.printStackTrace();
            return false;
        }
        finally {
            session.close();
            logger.info("Transaction for session was successfully closed");
        }
        return true;
    }

}
