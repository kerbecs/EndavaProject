package com.app.code;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.beanvalidation.BeanValidationEventListener;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@SuppressWarnings(value = "unchecked")
public class HibernateClass {
    private static SessionFactory sessionFactory;
    private static Session session;
    public static void addNewUser(User user, Customer customer){
        sessionFactory = new Configuration().configure().addAnnotatedClass(User.class).addAnnotatedClass(Customer.class).buildSessionFactory();
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.save(customer);
            session.save(user);
            session.getTransaction().commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    public static boolean searchUser(User user){
        sessionFactory = new Configuration().configure().addAnnotatedClass(User.class).addAnnotatedClass(Customer.class).buildSessionFactory();
        boolean found=false;
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            String username = user.getUsername();
            String password = user.getPassword();

            Query query = session.createQuery("FROM User WHERE username = ?0 AND password = ?1").setParameter(0,username).setParameter(1,password);
            found = query.getSingleResult() != null;

            session.getTransaction().commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return found;
    }
    public static boolean verifyUsernameExist(UserRegistration user){
        sessionFactory = new Configuration().configure().addAnnotatedClass(User.class).addAnnotatedClass(Customer.class).buildSessionFactory();
        boolean found=false;
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            String username = user.getUsername();

            Query query = session.createQuery("FROM User WHERE username = ?0").setParameter(0,username);
            found = query.getSingleResult() != null;

            session.getTransaction().commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return found;
    }
    public static List<Product> getAllProducts(){
        sessionFactory = new Configuration().configure().addAnnotatedClass(Product.class).buildSessionFactory();
        List<Product> productList = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            Query query = session.createQuery("FROM Product");
            productList = query.getResultList();

            session.getTransaction().commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return productList;
    }
    public static Customer getCustomerDetail(User user){
        sessionFactory = new Configuration().configure().addAnnotatedClass(Customer.class).addAnnotatedClass(User.class).buildSessionFactory();
        Customer customer = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            Query query = session.createQuery("FROM User WHERE username=?0").setParameter(0,user.getUsername());
            user = (User) query.getSingleResult();
            customer = (Customer) session.createQuery("FROM Customer WHERE id=?0").setParameter(0,user.getCustomer().getId()).getSingleResult();

            session.getTransaction().commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return customer;
    }
    public static boolean updateProfile(UserRegistration userRegistration) {
        sessionFactory = new Configuration().configure().addAnnotatedClass(Customer.class).addAnnotatedClass(User.class).buildSessionFactory();
        User userFromDb = null;
        Customer customerFromDb;
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            Query query = session.createQuery("FROM User WHERE username=?0").setParameter(0,userRegistration.getUsername());
            userFromDb = (User) query.getSingleResult();
            customerFromDb = userFromDb.getCustomer();

            if(!userRegistration.getFirstName().equals(customerFromDb.getFirstName()) && userRegistration.getFirstName()!=null){
                 customerFromDb.setFirstName(userRegistration.getFirstName());
            }
            if(!userRegistration.getLastName().equals(customerFromDb.getLastName()) && userRegistration.getLastName()!=null){
                 customerFromDb.setLastName(userRegistration.getLastName());
            }
            if(!userRegistration.getPassword().equals(userFromDb.getPassword()) && userRegistration.getPassword()!=null){
                userFromDb.setPassword(userRegistration.getPassword());
            }
            if(!userRegistration.getEmail().equals(customerFromDb.getEmail()) && userRegistration.getEmail()!=null){
                customerFromDb.setEmail(userRegistration.getEmail());
            }
            if(!userRegistration.getAddress().equals(customerFromDb.getAddress()) && userRegistration.getAddress()!=null){
                customerFromDb.setAddress(userRegistration.getAddress());
            }
            if(!userRegistration.getLocation().equals(customerFromDb.getLocation()) && userRegistration.getLocation()!=null){
                customerFromDb.setLocation(userRegistration.getLocation());
            }
            if(!userRegistration.getJob().equals(customerFromDb.getJob()) && userRegistration.getJob()!=null){
                customerFromDb.setJob(userRegistration.getJob());
            }

            System.out.println(customerFromDb);
            session.save(customerFromDb);
            session.getTransaction().commit();
        }
        catch (Exception e){
            return false;
        }
        finally {
            session.close();
        }
        return true;
    }
    public static void addCommand(doCommand doCommand,User user){
        sessionFactory = new Configuration().configure().addAnnotatedClass(Customer.class).addAnnotatedClass(User.class).
                addAnnotatedClass(Product.class).addAnnotatedClass(Order.class).buildSessionFactory();
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            for(String s:doCommand.getProductList()){
                Query query = session.createQuery("FROM Product WHERE title=?0").setParameter(0,s);
                Product product = (Product) query.getSingleResult();

                Order order = new Order();
                System.out.println("Heeere"+user);
                user = (User) session.createQuery("FROM User WHERE username=?0").setParameter(0,user.getUsername()).getSingleResult();
                user.getCustomer().setOrders(user.getCustomer().getOrders()+1);

                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();

                order.setOrderTime(formatter.format(date));
                order.setPrice(product.getPrice());
                order.setProduct(product);
                order.setCustomer(user.getCustomer());

                session.save(order);
            }

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
