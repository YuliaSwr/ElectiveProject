package com.sida.electivefinalproject.dao.mysql;

import com.sida.electivefinalproject.dao.AbstractDao;
import com.sida.electivefinalproject.dao.utils.ColumnName;
import com.sida.electivefinalproject.dao.utils.SQLQueries;
import com.sida.electivefinalproject.dao.UserDao;
import com.sida.electivefinalproject.entity.*;
import com.sida.electivefinalproject.exception.DatabaseException;
import com.sida.electivefinalproject.util.LoggerManager;
import org.slf4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlUserDao extends AbstractDao implements UserDao {
    private static final Logger logger = LoggerManager.getLogger(MySqlCourseDao.class);

    @Override
    public List<User> findAll() throws DatabaseException {
        List<User> users = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SQLQueries.SELECT_ALL_USERS);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                User user = extractUser(rs);
                users.add(user);
            }
        } catch (SQLException e) {
            logger.error("Cannot find all users");
            throw new DatabaseException("Cannot find all users", e);
        }
        return users;
    }

    @Override
    public List<User> findAllByType(UserType userType) throws DatabaseException {
        List<User> users = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SQLQueries.SELECT_ALL_USERS_BY_TYPE)) {
            stmt.setString(1, userType.toString());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                User user = extractUser(rs);
                users.add(user);
            }
        } catch (SQLException e) {
            logger.error("Cannot find all users by type " + userType.toString());
            throw new DatabaseException("Cannot find all users by type " + userType, e);
        }
        return users;
    }

    @Override
    public void unblockById(int id) throws DatabaseException {
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SQLQueries.UNBLOCK_USER_BY_ID)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Cannot unblock user by id " + id);
            throw new DatabaseException("Cannot unblock user by id " + id, e);
        }
    }

    @Override
    public User findByEmail(String email) throws DatabaseException {
        User user = null;
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SQLQueries.SELECT_USER_BY_EMAIL)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                user = extractUser(rs);
            }
        } catch (SQLException e) {
            logger.error("Cannot find user by email " + email);
            throw new DatabaseException("Cannot find user by email " + email, e);
        }
        return user;
    }

    private User extractUser(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt(ColumnName.USER_ID),
                rs.getString(ColumnName.USER_FIRSTNAME),
                rs.getString(ColumnName.USER_LASTNAME),
                rs.getString(ColumnName.USER_GENDER),
                rs.getString(ColumnName.USER_EMAIL),
                rs.getString(ColumnName.USER_PASSWORD),
                UserType.valueOf(rs.getString(ColumnName.USER_TYPE)),
                AccountStatus.valueOf(rs.getString(ColumnName.USER_ACCOUNT_STATUS)),
                rs.getString(ColumnName.USER_PHOTO)
        );
    }

    @Override
    public boolean insert(User user) throws DatabaseException {
        boolean inserted;
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SQLQueries.INSERT_NEW_USER, Statement.RETURN_GENERATED_KEYS)) {
            con.setAutoCommit(false);
            prepareUser(user, stmt);
            inserted = stmt.executeUpdate() > 0;
            if (inserted) {
                con.commit();
            } else {
                con.rollback();
            }
            con.setAutoCommit(true);
        } catch (SQLException e) {
            logger.error("Cannot insert new user");
            throw new DatabaseException("Cannot insert new user", e);
        }
        return inserted;
    }

    private void prepareUser(User user, PreparedStatement stmt) throws SQLException {
        int i = 0;
        stmt.setString(++i, user.getFirstName());
        stmt.setString(++i, user.getLastName());
        stmt.setString(++i, user.getGender());
        stmt.setString(++i, user.getEmail());
        stmt.setString(++i, user.getPassword());
        stmt.setString(++i, user.getUserType().toString());
        stmt.setString(++i, user.getAccountStatus().toString());
        stmt.setString(++i, user.getPhoto());
        if (user.getId() > 0) {
            stmt.setInt(++i, user.getId());
        }
    }

    @Override
    public User findById(int id) throws DatabaseException {
        User user = null;
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SQLQueries.SELECT_USER_BY_ID)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                user = extractUser(rs);
            }
        } catch (SQLException e) {
            logger.error("Cannot find user by id " + id);
            throw new DatabaseException("Cannot find user by id " + id, e);
        }
        return user;
    }

    @Override
    public boolean delete(int id) throws DatabaseException {
        boolean deleted;
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SQLQueries.DELETE_USER_BY_ID, Statement.RETURN_GENERATED_KEYS)) {
            con.setAutoCommit(false);
            stmt.setInt(1, id);
            deleted = stmt.executeUpdate() > 0;
            if (deleted) {
                con.commit();
            } else {
                con.rollback();
            }
            con.setAutoCommit(true);
        } catch (SQLException e) {
            logger.error("Cannot delete user by id = " + id);
            throw new DatabaseException("Cannot delete user by id = " + id, e);
        }
        return deleted;
    }

    @Override
    public boolean update(User user) throws DatabaseException {
        boolean updated;
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SQLQueries.UPDATE_USER_BY_ID, Statement.RETURN_GENERATED_KEYS)) {
            con.setAutoCommit(false);
            prepareUser(user, stmt);
            updated = stmt.executeUpdate() > 0;
            if (updated) {
                con.commit();
            } else {
                con.rollback();
            }
            con.setAutoCommit(true);
        } catch (SQLException e) {
            logger.error("Cannot update user by id " + user.getId());
            throw new DatabaseException("Cannot update user by id " + user.getId(), e);
        }
        return updated;
    }
}
