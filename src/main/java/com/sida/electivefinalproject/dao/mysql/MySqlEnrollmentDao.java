package com.sida.electivefinalproject.dao.mysql;

import com.sida.electivefinalproject.dao.AbstractDao;
import com.sida.electivefinalproject.dao.EnrollmentDAO;
import com.sida.electivefinalproject.dao.utils.ColumnName;
import com.sida.electivefinalproject.dao.utils.SQLQueries;
import com.sida.electivefinalproject.entity.*;
import com.sida.electivefinalproject.exception.DatabaseException;
import com.sida.electivefinalproject.util.LoggerManager;
import org.slf4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MySqlEnrollmentDao extends AbstractDao implements EnrollmentDAO {
    private static final Logger logger = LoggerManager.getLogger(MySqlEnrollmentDao.class);

    @Override
    public boolean insert(Enrollment enrollment) throws DatabaseException {
        boolean inserted;
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SQLQueries.INSERT_NEW_ENROLLMENT, Statement.RETURN_GENERATED_KEYS)) {
            con.setAutoCommit(false);
            System.out.println(enrollment);
            prepareEnrollment(enrollment, stmt);
            inserted = stmt.executeUpdate() > 0;
            if (inserted) {
                con.commit();
            } else {
                con.rollback();
            }
            con.setAutoCommit(true);
        } catch (SQLException e) {
            logger.error("Cannot insert new enrollment");
            throw new DatabaseException("Cannot insert new enrollment", e);
        }
        return inserted;
    }

    private void prepareEnrollment(Enrollment enrollment, PreparedStatement stmt) throws SQLException {
        int i = 0;
        stmt.setInt(++i, enrollment.getCourseId());
        stmt.setInt(++i, enrollment.getStudentId());
        stmt.setDate(++i, enrollment.getEnrollmentDate());
        stmt.setDate(++i, enrollment.getStartDate());
        stmt.setDate(++i, enrollment.getEndDate());
        stmt.setInt(++i, ProgressStatus.getNumber(enrollment.getProgressStatus()));
    }

    @Override
    public Enrollment findById(int id) throws DatabaseException {
        Enrollment enrollment = new Enrollment();
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SQLQueries.SELECT_ENROLLMENT_BY_ID)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                enrollment = extractEnrollment(rs);
            }
        } catch (SQLException e) {
            logger.error("Cannot find enrollment by id = " + id);
            throw new DatabaseException("Cannot find enrollment by id = " + id, e);
        }
        return enrollment;
    }

    private Enrollment extractEnrollment(ResultSet rs) throws SQLException {
        return new Enrollment(
                rs.getInt(ColumnName.ENROLLMENT_ID),
                rs.getInt(ColumnName.ENROLLMENT_COURSE_ID),
                rs.getInt(ColumnName.ENROLLMENT_STUDENT_ID),
                rs.getDate(ColumnName.ENROLLMENT_DATE),
                rs.getDate(ColumnName.ENROLLMENT_START_DATE),
                rs.getDate(ColumnName.ENROLLMENT_END_DATE),
                ProgressStatus.getName(rs.getInt(ColumnName.ENROLLMENT_PROGRESS_STATUS_ID))
        );
    }

    @Override
    public List<Enrollment> findAll() throws DatabaseException {
        List<Enrollment> enrollments = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SQLQueries.SELECT_ALL_ENROLLMENTS);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Enrollment enrollment = extractEnrollment(rs);
                enrollments.add(enrollment);
            }
        } catch (SQLException e) {
            logger.error("Cannot find all enrollments");
            throw new DatabaseException("Cannot find all enrollments", e);
        }
        return enrollments;
    }

    @Override
    public boolean update(Enrollment element) throws DatabaseException {
        return false;
    }

    @Override
    public boolean delete(int id) throws DatabaseException {
        boolean deleted;
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SQLQueries.DELETE_ENROLLMENT_BY_ID, Statement.RETURN_GENERATED_KEYS)) {
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
            logger.error("Cannot delete enrollment by id = " + id);
            throw new DatabaseException("Cannot delete enrollment by id = " + id, e);
        }
        return deleted;
    }


    @Override
    public List<Integer> findAllCoursesIdsByUserId(int userId) throws DatabaseException {
        List<Integer> ids = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SQLQueries.SELECT_ENROLLED_COURSES_IDS_BY_STUDENT_ID)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt(ColumnName.ENROLLMENT_COURSE_ID);
                ids.add(id);
            }
        } catch (SQLException e) {
            logger.error("Cannot find all courses ids by student id = " + userId);
            throw new DatabaseException("Cannot find all courses ids by student id = " + userId, e);
        }
        return ids;
    }

    @Override
    public List<Integer> findAllStudentsIdsEnrolledToCourse(int courseId) throws DatabaseException {
        List<Integer> ids = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SQLQueries.SELECT_STUDENTS_ENROLLED_TO_COURSE)) {
            stmt.setInt(1, courseId);
            System.out.println(stmt);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt(ColumnName.ENROLLMENT_STUDENT_ID);
                ids.add(id);
            }
        } catch (SQLException e) {
            logger.error("Cannot find all students ids by course id = " + courseId);
            throw new DatabaseException("Cannot find all students ids by course id = " + courseId, e);
        }
        return ids;
    }

    @Override
    public boolean deleteByStudentIdAndCourseId(int studentId, int courseId) throws DatabaseException {
        boolean deleted;
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SQLQueries.DELETE_ENROLLMENT_BY_STUDENT_ID_AND_COURSE_ID, Statement.RETURN_GENERATED_KEYS)) {
            con.setAutoCommit(false);
            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);
            deleted = stmt.executeUpdate() > 0;
            if (deleted) {
                con.commit();
            } else {
                con.rollback();
            }
            con.setAutoCommit(true);
        } catch (SQLException e) {
            logger.error("Cannot delete enrollment");
            throw new DatabaseException("Cannot delete enrollment", e);
        }
        return deleted;
    }

    @Override
    public boolean updateCourseStart(int studentId, int courseId) throws DatabaseException {
        boolean updated;
        System.out.println("SSSS");
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SQLQueries.UPDATE_ENROLLMENT_AS_STARTED, Statement.RETURN_GENERATED_KEYS)) {
            con.setAutoCommit(false);
            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);
            System.out.println(stmt);
            updated = stmt.executeUpdate() > 0;
            if (updated) {
                con.commit();
            } else {
                con.rollback();
            }
            con.setAutoCommit(true);
        } catch (SQLException e) {
            logger.error("Cannot update an enrollment as started");
            throw new DatabaseException("Cannot update an enrollment as started", e);
        }
        return updated;
    }

    @Override
    public Enrollment findByStudentIdAndCourseId(int studentId, int courseId) throws DatabaseException {
        Enrollment enrollment = new Enrollment();
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SQLQueries.SELECT_ENROLLMENT_BY_STUDENT_ID_AND_COURSE_ID)) {
            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                enrollment = extractEnrollment(rs);
            }
        } catch (SQLException e) {
            logger.error("Cannot find enrollment");
            throw new DatabaseException("Cannot find enrollment", e);
        }
        return enrollment;
    }

    @Override
    public List<Integer> findCompletedCoursesIdsByUserId(int userId) throws DatabaseException {
        List<Integer> ids = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SQLQueries.SELECT_COMPLETED_COURSES_IDS_BY_STUDENT_ID)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt(ColumnName.ENROLLMENT_COURSE_ID);
                ids.add(id);
            }
        } catch (SQLException e) {
            logger.error("Cannot find completed courses ids by student id = " + userId);
            throw new DatabaseException("Cannot find completed courses ids by student id = " + userId, e);
        }
        return ids;
    }
}
