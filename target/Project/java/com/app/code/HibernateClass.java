package com.app.code;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.beanvalidation.BeanValidationEventListener;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@SuppressWarnings(value = "unchecked")
public class HibernateClass {
    private static SessionFactory sessionFactory;
    private static Session session;

    private final static Logger logger = LoggerFactory.getLogger(HibernateClass.class);
    private static AnnotationConfigApplicationContext config = new AnnotationConfigApplicationContext(appContext.class);
    public static void addNewUser(User user, Customer customer){
        sessionFactory = new Configuration().configure().addAnnotatedClass(User.class).addAnnotatedClass(Customer.class).buildSessionFactory();
        logger.info("A sessionFactory was successfully created.");
        try {
            session = sessionFactory.getCurrentSession();
            logger.info("Session was successfully created.");
            session.beginTransaction();
            logger.info("Transaction for session was successfully started");
            session.save(customer);
            session.save(user);
            session.getTransaction().commit();
            logger.info("Session was successfully committed");

        }
        catch (Exception e){
            logger.info(e.getMessage());
            e.printStackTrace();
        }
        finally {
            session.close();
            logger.info("Transaction for session  was successfully closed");
        }
    }

    public static boolean searchUser(User user){
        sessionFactory = new Configuration().configure().addAnnotatedClass(User.class).addAnnotatedClass(Customer.class).buildSessionFactory();
        logger.info("A sessionFactory was successfully created.");
        boolean found=false;
        try {
            session = sessionFactory.getCurrentSession();
            logger.info("Session  was successfully created.");
            session.beginTransaction();
            logger.info("Transaction for session was successfully started");

            String username = user.getUsername();
            String password = user.getPassword();

            Query query = session.createQuery("FROM User WHERE username = ?0 AND password = ?1").setParameter(0,username).setParameter(1,password);
            found = query.getSingleResult() != null;

            session.getTransaction().commit();
            logger.info("Session was successfully committed");
        }
        catch (Exception e){
            logger.info(e.getMessage());
            e.printStackTrace();
        }
        finally {
            session.close();
            logger.info("Transaction for session with tenant id "+session.getTenantIdentifier()+" was successfully closed");

        }
        return found;
    }
    public static boolean verifyUsernameExist(UserRegistration user){
        sessionFactory = new Configuration().configure().addAnnotatedClass(User.class).addAnnotatedClass(Customer.class).buildSessionFactory();
        logger.info("A sessionFactory was successfully created.");

        boolean found=false;
        try {
            session = sessionFactory.getCurrentSession();
            logger.info("Session was successfully created.");
            session.beginTransaction();
            logger.info("Transaction for session was successfully started");

            String username = user.getUsername();

            Query query = session.createQuery("FROM User WHERE username = ?0").setParameter(0,username);
            found = query.getSingleResult() != null;

            session.getTransaction().commit();
            logger.info("Session was successfully committed");
        }
        catch (Exception e){
            logger.info(e.getMessage());
            e.printStackTrace();
        }
        finally {
            session.close();
            logger.info("Transaction for session was successfully closed");

        }
        return found;
    }
    public static List<Product> getAllProducts(){
        sessionFactory = new Configuration().configure().addAnnotatedClass(Product.class).buildSessionFactory();
        logger.info("A sessionFactory was successfully created.");

        List<Product> productList = null;
        try {
            session = sessionFactory.getCurrentSession();
            logger.info("Session was successfully created.");
            session.beginTransaction();
            logger.info("Transaction for session was successfully started");

            Query query = session.createQuery("FROM Product");
            productList = query.getResultList();

            session.getTransaction().commit();
            logger.info("Session with tenant id "+session.getTenantIdentifier()+" was successfully committed");
        }
        catch (Exception e){
            logger.info(e.getMessage());
            e.printStackTrace();
        }
        finally {
            session.close();
            logger.info("Transaction for session was successfully closed");
        }
        return productList;
    }
    public static Customer getCustomerDetail(User user){
        sessionFactory = new Configuration().configure().addAnnotatedClass(Customer.class).addAnnotatedClass(User.class).buildSessionFactory();
        logger.info("A sessionFactory was successfully created.");

        Customer customer = null;
        try {
            session = sessionFactory.getCurrentSession();
            logger.info("Session was successfully created.");
            session.beginTransaction();
            logger.info("Transaction for session was successfully started");

            Query query = session.createQuery("FROM User WHERE username=?0").setParameter(0,user.getUsername());
            user = (User) query.getSingleResult();
            customer = (Customer) session.createQuery("FROM Customer WHERE id=?0").setParameter(0,user.getCustomer().getId()).getSingleResult();

            session.getTransaction().commit();
            logger.info("Session was successfully committed");
        }
        catch (Exception e){
            logger.info(e.getMessage());
            e.printStackTrace();
        }
        finally {
            session.close();
            logger.info("Transaction for session was successfully closed");
        }

        return customer;
    }
    public static boolean updateProfile(UserRegistration userRegistration) {
        sessionFactory = new Configuration().configure().addAnnotatedClass(Customer.class).addAnnotatedClass(User.class).buildSessionFactory();
        logger.info("A sessionFactory was successfully created.");

        User userFromDb = null;
        Customer customerFromDb;
        try {
            session = sessionFactory.getCurrentSession();
            logger.info("Session was successfully created.");
            session.beginTransaction();
            logger.info("Transaction for session was successfully started");

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
            logger.info("Session was successfully committed");
        }
        catch (Exception e){
            logger.info(e.getMessage());
            return false;
        }
        finally {
            session.close();
            logger.info("Transaction for session was successfully closed");
        }

        return true;
    }
    public static void addCommand(doCommand doCommand,User user){
        sessionFactory = new Configuration().configure().addAnnotatedClass(Customer.class).addAnnotatedClass(User.class).
                addAnnotatedClass(Product.class).addAnnotatedClass(Order.class).buildSessionFactory();
        logger.info("A sessionFactory was successfully created.");

        try {
            session = sessionFactory.getCurrentSession();
            logger.info("Session was successfully created.");
            session.beginTransaction();
            logger.info("Transaction for session was successfully started");

            for(String s:doCommand.getProductList()){
                Query query = session.createQuery("FROM Product WHERE title=?0").setParameter(0,s);
                Product product = (Product) query.getSingleResult();

                Order order = config.getBean("order",Order.class);
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
            logger.info("Session was successfully committed");
        }
        catch (Exception e){
            logger.info(e.getMessage());
            e.printStackTrace();
        }
        finally {
            session.close();
            logger.info("Transaction for session was successfully closed");

        }
    }
}
