package com.sida.electivefinalproject.dao.mysql;

import com.sida.electivefinalproject.dao.AbstractDao;
import com.sida.electivefinalproject.dao.utils.ColumnName;
import com.sida.electivefinalproject.dao.CourseDAO;
import com.sida.electivefinalproject.dao.utils.SQLQueries;
import com.sida.electivefinalproject.entity.Specialization;
import com.sida.electivefinalproject.exception.DatabaseException;
import com.sida.electivefinalproject.entity.Course;
import com.sida.electivefinalproject.util.LoggerManager;
import org.slf4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlCourseDao extends AbstractDao implements CourseDAO {

    private static final Logger logger = LoggerManager.getLogger(MySqlCourseDao.class);

    @Override
    public List<Course> findAll() throws DatabaseException {
        List<Course> courses = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SQLQueries.SELECT_ALL_COURSES);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Course course = extractCourse(rs);
                courses.add(course);
            }
        } catch (SQLException e) {
            logger.error("Cannot find all courses");
            throw new DatabaseException("Cannot find all courses", e);
        }
        return courses;
    }

    @Override
    public Course findById(int id) throws DatabaseException {
        Course course = new Course();
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SQLQueries.SELECT_COURSE_BY_ID)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                course = extractCourse(rs);
            }
        } catch (SQLException e) {
            logger.error("Cannot find course by id = " + id);
            throw new DatabaseException("Cannot find course by id = " + id, e);
        }
        return course;
    }

    private Course extractCourse(ResultSet rs) throws SQLException {
        return new Course(
                rs.getInt(ColumnName.COURSE_ID),
                rs.getString(ColumnName.COURSE_TITLE),
                rs.getString(ColumnName.COURSE_DESCRIPTION),
                Specialization.getName(rs.getInt(ColumnName.COURSE_SPECIALIZATION_ID)),
                rs.getInt(ColumnName.COURSE_DURATION),
                rs.getInt(ColumnName.COURSE_TEACHER_ID)
        );
    }


    @Override
    public boolean insert(Course course) throws DatabaseException {
        boolean inserted;
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SQLQueries.INSERT_NEW_COURSE, Statement.RETURN_GENERATED_KEYS)) {
            con.setAutoCommit(false);
            prepareCourse(course, stmt);
            inserted = stmt.executeUpdate() > 0;
            if (inserted) {
                con.commit();
            } else {
                con.rollback();
            }
            con.setAutoCommit(true);
        } catch (SQLException e) {
            logger.error("Cannot insert new course");
            throw new DatabaseException("Cannot insert new course", e);
        }
        return inserted;
    }

    private void prepareCourse(Course course, PreparedStatement stmt) throws SQLException {
        int i = 0;
        stmt.setString(++i, course.getTitle());
        stmt.setString(++i, course.getDescription());
        stmt.setInt(++i, course.getDurationInWeeks());
        stmt.setInt(++i, Specialization.getNumber(course.getSpecialization()));
        stmt.setInt(++i, course.getTeacher());
        if (course.getId() > 0) {
            stmt.setInt(++i, course.getId());
        }
    }

    @Override
    public boolean update(Course course) throws DatabaseException {
        boolean updated;
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SQLQueries.UPDATE_COURSE_BY_ID, Statement.RETURN_GENERATED_KEYS)) {
            con.setAutoCommit(false);
            prepareCourse(course, stmt);
            updated = stmt.executeUpdate() > 0;
            if (updated) {
                con.commit();
            } else {
                con.rollback();
            }
            con.setAutoCommit(true);
        } catch (SQLException e) {
            logger.error("Cannot update course");
            throw new DatabaseException("Cannot update course", e);
        }
        return updated;
    }

    @Override
    public boolean delete(int id) throws DatabaseException {
        boolean deleted;
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SQLQueries.DELETE_COURSE_BY_ID, Statement.RETURN_GENERATED_KEYS)) {
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
            logger.error("Cannot delete course by id = " + id);
            throw new DatabaseException("Cannot delete course by id = " + id, e);
        }
        return deleted;
    }

    @Override
    public List<Course> findAllCoursesBySpecialization(Specialization specialization) throws DatabaseException {
        int specId = Specialization.getNumber(specialization);
        List<Course> courses = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SQLQueries.SELECT_ALL_COURSES_BY_SPECIALIZATION)) {
            stmt.setInt(1, specId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Course course = extractCourse(rs);
                courses.add(course);
            }
        } catch (SQLException e) {
            logger.error("Cannot find courses by specialization");
            throw new DatabaseException("Cannot find courses by specialization", e);
        }
        return courses;
    }

    @Override
    public List<Course> findAllByTeacherId(int teacherId) throws DatabaseException {
        List<Course> courses = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SQLQueries.SELECT_ALL_COURSES_BY_TEACHER_ID)) {
            stmt.setInt(1, teacherId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Course course = extractCourse(rs);
                courses.add(course);
            }
        } catch (SQLException e) {
            logger.error("Cannot find all courses by teacher id");
            throw new DatabaseException("Cannot find all courses by teacher id", e);
        }
        return courses;
    }
}
