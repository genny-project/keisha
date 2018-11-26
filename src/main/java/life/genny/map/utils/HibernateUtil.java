package life.genny.map.utils;

import java.util.Optional;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

@SuppressWarnings("unused")
public class HibernateUtil {

    private static final SessionFactory SESSION_FACTORY ;
    private static final String FULL_MYSQL_URL;
    private static final String MYSQL_USER;
    private static final String MYSQL_PASSWORD;

    static {

        Optional<String> fullMysqlUrl =  Optional.ofNullable(System.getenv("FULL_MYSQL_URL"));
        Optional<String> mysqlUser =  Optional.ofNullable(System.getenv("MYSQL_USER"));
        Optional<String> mysqlPassword =  Optional.ofNullable(System.getenv("MYSQL_PASSWORD"));

        FULL_MYSQL_URL = fullMysqlUrl.orElse("jdbc:mysql://localhost:3310/gennydb");
        MYSQL_USER = mysqlUser.orElse("genny");
        MYSQL_PASSWORD = mysqlPassword.orElse("password");

        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.url", FULL_MYSQL_URL);
        configuration.setProperty("hibernate.connection.username", MYSQL_USER);
        configuration.setProperty("hibernate.connection.password", MYSQL_PASSWORD);
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");

        SESSION_FACTORY = configuration.configure().buildSessionFactory();

    }
    private HibernateUtil() {
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration().configure();
            Properties properties = configuration.getProperties();
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(properties).build();
            return configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
}
