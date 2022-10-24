package com.sida.electivefinalproject.dao;


import com.sida.electivefinalproject.entity.BaseEntity;
import com.sida.electivefinalproject.exception.DatabaseException;

import java.util.List;

public interface Dao<T extends BaseEntity> {
    /**
     * Insert T element to the DataBase
     *
     * @param element element to insert
     * @return true - inserted successfully
     * false - did not insert
     */
    boolean insert(T element) throws DatabaseException;

    /**
     * Find element by id
     *
     * @param id id of the element to find
     * @return element
     */
    T findById(int id) throws DatabaseException;

    /**
     * Get all T elements from Database in List
     *
     * @return List of received elements
     */
    List<T> findAll() throws DatabaseException;

    /**
     * Replace element in Database with one another
     *
     * @param element changed the Database element but with the same id
     * @return the Database element (old version)
     */
    boolean update(T element) throws DatabaseException;

    /**
     * Delete the element in Database
     *
     * @param id id of element to delete
     * @return <code>true</code> if removed successfully;
     * <code>false</code> if element wasn't removed: couldn't find the element in Database or got error.
     */
    boolean delete(int id) throws DatabaseException;
}
