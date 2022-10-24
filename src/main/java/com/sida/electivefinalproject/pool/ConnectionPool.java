package com.sida.electivefinalproject.pool;

import com.sida.electivefinalproject.exception.ConnectionException;
import com.sida.electivefinalproject.util.LoggerManager;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static final Logger logger = LoggerManager.getLogger(ConnectionPool.class);

    private static final String PROPERTIES_FILE = "/db.properties";
    private static final BasicDataSource ds = new BasicDataSource();

    private String driverClassName;
    private String user;
    private String password;
    private String url;
    private int minIdle;
    private int maxIdle;
    private long maxWait;

    private ConnectionPool() {
        init();
        logger.debug("Connection pool was created");
    }

    private static final class ConnectionPoolHolder {
        private static final ConnectionPool connectionPool = new ConnectionPool();
    }

    public static ConnectionPool getInstance() {
        logger.debug("ConnectionPool.getInstance() was called");
        return ConnectionPoolHolder.connectionPool;
    }

    public Connection getConnection() throws ConnectionException {
        Connection connection = null;
        try {
            connection = ds.getConnection();
            logger.debug("Connection from ConnectionPool was received");
        } catch (SQLException e) {
            logger.error("Can't get connection from connection pool", e);
            throw new ConnectionException("Can't get connection from connection pool");
        }
        return connection;
    }

    private void loadProperties() {
        Properties properties = getProperties();

        driverClassName = properties.getProperty("driverClassName");
        user = properties.getProperty("user");
        password = properties.getProperty("password");
        url = properties.getProperty("url");
        minIdle = Integer.parseInt(properties.getProperty("minIdle"));
        maxIdle = Integer.parseInt(properties.getProperty("maxIdle"));
        maxWait = Long.parseLong(properties.getProperty("maxWait"));
    }

    private Properties getProperties() {
        Properties properties = new Properties();
        try {
            properties.load(ConnectionPool.class.getResourceAsStream(PROPERTIES_FILE));
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Can't load properties file for Database", e);
        }
        return properties;
    }

    private void init() {
        loadProperties();

        ds.setDriverClassName(driverClassName);
        ds.setUsername(user);
        ds.setPassword(password);
        ds.setUrl(url);
        ds.setMinIdle(minIdle);
        ds.setMaxIdle(maxIdle);
        ds.setMaxWaitMillis(maxWait);
    }
}
