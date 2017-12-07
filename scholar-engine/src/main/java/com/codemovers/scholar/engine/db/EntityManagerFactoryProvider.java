package com.codemovers.scholar.engine.db;

import com.codemovers.scholar.engine.db.controllers.SchoolDataJpaController;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 * Created by Mover on 4/27/2017.
 */
public class EntityManagerFactoryProvider {

    private static EntityManagerFactoryProvider instance;
    private final Map<String, EntityManagerFactory> factories;

    private static final Logger LOG = Logger.getLogger(EntityManagerFactoryProvider.class.getName());

    private EntityManagerFactoryProvider() {
        factories = new HashMap<>();
    }

    public static EntityManagerFactoryProvider getInstance() {
        if (instance == null) {
            instance = new EntityManagerFactoryProvider();
        }

        return instance;
    }

    public EntityManagerFactory getFactory(String school_name) {

        EntityManagerFactory factory = null;
        String database = getDatabase(school_name);

        if (factories.containsKey(database)) {
            LOG.log(Level.INFO, "Re Using Existing Database ");
            factory = factories.get(database);
        } else {
            try {

                factory = createFactory(database);
            } catch (Exception e) {
                LOG.log(Level.SEVERE, "Factory Could Not Be Created");
            }
        }

        return factory;

    }

    private String host = null;
    private String username = null;
    private String password = null;
    private String default_db = null;

    public EntityManagerFactory createFactory() {
        EntityManagerFactory emf = null;
        Map<String, String> properties = new HashMap<>();

        properties.put("hibernate.connection.url", "jdbc:mysql://" + getHost() + "/" + getDefault_db());
        properties.put("hibernate.connection.username", getUsername());
        properties.put("hibernate.connection.password", getPassword());
        properties.put("hibernate.ejb.entitymanager_factory_name", getDefault_db());
        try {
            // properties
            emf = Persistence.createEntityManagerFactory("scholar", properties);

        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Un Expected Error {0}", e.toString());
        }

        return emf;
    }

    public EntityManagerFactory createFactory(String db_name) {
        EntityManagerFactory emf = null;
        Map<String, String> properties = new HashMap<>();

        properties.put("hibernate.connection.url", "jdbc:mysql://" + getHost() + "/" + db_name);
        properties.put("hibernate.connection.username", getUsername());
        properties.put("hibernate.connection.password", getPassword());
        properties.put("hibernate.ejb.entitymanager_factory_name", db_name);
        try {
            // properties
            emf = Persistence.createEntityManagerFactory("scholar", properties);

        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Un Expected Error {0}", e.toString());
        }

        return emf;
    }

    public static void setInstance(EntityManagerFactoryProvider instance) {
        EntityManagerFactoryProvider.instance = instance;
    }

    public Map<String, EntityManagerFactory> getFactories() {
        return factories;
    }

    public static Logger getLOG() {
        return LOG;
    }

    private String getDatabase(String school_name) {

        switch (school_name) {
            case "DEFAULT":
                return "scholar-backoffice";
            default:
                EntityManager em = createFactory().createEntityManager();
                List<SchoolData> sds = SchoolDataJpaController.getInstance().findSchoolDataByName(school_name);
                if (sds != null) {
                    return sds.get(0).getExternalId();
                }
                return null;

        }

    }

    public String getHost() {
        host = System.getProperty("SC_CLIENT_DB_HOST");
        return host;
    }

    public String getUsername() {
        username = System.getProperty("SC_CLIENT_DB_USER");
        return username;
    }

    public String getPassword() {
        password = System.getProperty("SC_CLIENT_DB_PASSWORD");
        return password;
    }

    public String getDefault_db() {
        default_db = "scholar-tenants";
        return default_db;
    }

}
