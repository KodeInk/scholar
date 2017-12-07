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

    public enum DBModule {
        SC_BACK, SC_ENGINE
    };

    private String host = null;
    private String username = null;
    private String password = null;
    private String default_db = null;


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

    public EntityManagerFactory getFactory(DBModule dBModule, String database) {

        EntityManagerFactory factory = null;

        if (factories.containsKey(database)) {
            LOG.log(Level.INFO, "Re Using Existing Database ");
            factory = factories.get(database);
        } else {
            try {

                factory = createFactory(dBModule, database);
            } catch (Exception e) {
                LOG.log(Level.SEVERE, "Factory Could Not Be Created");
                e.printStackTrace();
            }
        }

        return factory;

    }

    public EntityManagerFactory createFactory(DBModule dBModule, String database) {
        EntityManagerFactory emf = null;
        Map<String, String> properties = new HashMap<>();

        properties.put("hibernate.connection.url", "jdbc:mysql://" + getHost(dBModule) + "/" + database);
        properties.put("hibernate.connection.username", getUsername(dBModule));
        properties.put("hibernate.connection.password", getPassword(dBModule));
        properties.put("hibernate.ejb.entitymanager_factory_name", getDatabase(dBModule));
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

    private String getDatabase(DBModule dBModule) {
        return "scholar-backoffice";
    }

    public String getHost(DBModule dBModule) {
        host = System.getProperty("SC_CLIENT_DB_HOST");
        return host;
    }

    public String getUsername(DBModule dBModule) {
        username = System.getProperty("SC_CLIENT_DB_USER");
        return username;
    }

    public String getPassword(DBModule dBModule) {
        password = System.getProperty("SC_CLIENT_DB_PASSWORD");
        return password;
    }

    public String getDefault_db() {
        default_db = "scholar-tenants";
        return default_db;
    }

}
