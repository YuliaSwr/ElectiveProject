package com.sida.electivefinalproject.dao;

import java.sql.Connection;

public abstract class AbstractDao {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public AbstractDao() {
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
