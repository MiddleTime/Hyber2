
package com.mycompany.app;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.mycompany.models.Car;
import com.mycompany.models.User;

import java.util.List;
public class Application {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/test");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        configuration.addResource("User.hbm.xml");
        configuration.addAnnotatedClass(Car.class);
        configuration.setProperty("hibernate.show_sql", "true");
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        Session session = sessionFactory.openSession();

        User user = session.createQuery("from User user where user.id = 1", User.class).getSingleResult();

        session.beginTransaction();
        session.save(new User("Mini", "Max", 99));
        session.getTransaction().commit();
        System.out.println(user);

        List<Car> car = session.createQuery("from Car car", Car.class).getResultList();

        int i = 0;
        session.close();

    }
}
