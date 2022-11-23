package com.app.code;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class TestApp {
    public static void main(String[] args){
        SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(User.class).addAnnotatedClass(Customer.class).buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();

        Query query = session.createQuery("FROM User WHERE username = 'kerbecs' AND password = 'test123'");

        System.out.println(query.getSingleResult());

        session.getTransaction().commit();
        session.close();

    }

}
