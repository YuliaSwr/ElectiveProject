package com.sida.electivefinalproject.dao.mysql;

import com.sida.electivefinalproject.dao.AbstractDao;
import com.sida.electivefinalproject.dao.FinalGradeDAO;
import com.sida.electivefinalproject.dao.utils.ColumnName;
import com.sida.electivefinalproject.dao.utils.SQLQueries;
import com.sida.electivefinalproject.entity.FinalGrade;
import com.sida.electivefinalproject.exception.DatabaseException;
import com.sida.electivefinalproject.util.LoggerManager;
import org.slf4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlFinalGradeDao extends AbstractDao implements FinalGradeDAO {
    private static final Logger logger = LoggerManager.getLogger(MySqlFinalGradeDao.class);

    @Override
    public boolean insert(FinalGrade finalGrade) throws DatabaseException {
        boolean inserted;
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SQLQueries.INSERT_NEW_FINAL_GRADE, Statement.RETURN_GENERATED_KEYS)) {
            con.setAutoCommit(false);
            prepareFinalGrade(finalGrade, stmt);
            inserted = stmt.executeUpdate() > 0;
            if (inserted) {
                con.commit();
            } else {
                con.rollback();
            }
            con.setAutoCommit(true);
        } catch (SQLException e) {
            logger.error("Cannot insert new final grade");
            throw new DatabaseException("Cannot insert new final grade", e);
        }
        return inserted;
    }

    private void prepareFinalGrade(FinalGrade finalGrade, PreparedStatement stmt) throws SQLException {
        int i = 0;
        stmt.setInt(++i, finalGrade.getCourseId());
        stmt.setInt(++i, finalGrade.getStudentId());
        stmt.setInt(++i, finalGrade.getGrade());
        if (finalGrade.getId() != 0) {
            stmt.setInt(++i, finalGrade.getId());
        }
    }

    @Override
    public FinalGrade findById(int id) throws DatabaseException {
        FinalGrade finalGrade = new FinalGrade();
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SQLQueries.SELECT_FINAL_GRADE_BY_ID);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                finalGrade = extractFinalGrade(rs);
            }
        } catch (SQLException e) {
            logger.error("Cannot find final grade by id");
            throw new DatabaseException("Cannot find final grade by id", e);
        }
        return finalGrade;
    }

    @Override
    public List<FinalGrade> findAll() throws DatabaseException {
        List<FinalGrade> finalGrades = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SQLQueries.SELECT_ALL_FINAL_GRADES);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                FinalGrade finalGrade = extractFinalGrade(rs);
                finalGrades.add(finalGrade);
            }
        } catch (SQLException e) {
            logger.error("Cannot find all final grades");
            throw new DatabaseException("Cannot find all final grades", e);
        }
        return finalGrades;
    }

    private FinalGrade extractFinalGrade(ResultSet rs) throws SQLException {
        return new FinalGrade(
                rs.getInt(ColumnName.FINAL_GRADE_ID),
                rs.getInt(ColumnName.FINAL_GRADE_COURSE_ID),
                rs.getInt(ColumnName.FINAL_GRADE_STUDENT_ID),
                rs.getInt(ColumnName.FINAL_GRADE_GRADE)
        );
    }

    @Override
    public boolean update(FinalGrade finalGrade) throws DatabaseException {
        boolean updated;
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SQLQueries.UPDATE_FINAL_GRADE_BY_ID, Statement.RETURN_GENERATED_KEYS)) {
            con.setAutoCommit(false);
            prepareFinalGrade(finalGrade, stmt);
            updated = stmt.executeUpdate() > 0;
            if (updated) {
                con.commit();
            } else {
                con.rollback();
            }
            con.setAutoCommit(true);
        } catch (SQLException e) {
            logger.error("Cannot update final grade");
            throw new DatabaseException("Cannot update final grade", e);
        }
        return updated;
    }

    @Override
    public boolean delete(int id) throws DatabaseException {
        boolean deleted;
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SQLQueries.DELETE_FINAL_GRADE_BY_ID, Statement.RETURN_GENERATED_KEYS)) {
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
            logger.error("Cannot delete final grade by id");
            throw new DatabaseException("Cannot delete final grade by id", e);
        }
        return deleted;
    }

    @Override
    public FinalGrade findByStudentIdAndCourseId(int studentId, int courseId) throws DatabaseException {
        FinalGrade finalGrade = new FinalGrade();
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SQLQueries.SELECT_FINAL_GRADE_BY_STUDENT_AND_COURSE_ID)) {
            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                finalGrade = extractFinalGrade(rs);
            }
        } catch (SQLException e) {
            logger.error("Cannot find final grade by student_id and course_id");
            throw new DatabaseException("Cannot find final grade by student_id and course_id", e);
        }
        return finalGrade;
    }

    @Override
    public List<FinalGrade> findByStudentId(int studentId) throws DatabaseException {
        List<FinalGrade> finalGrades = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SQLQueries.SELECT_FINAL_GRADE_BY_STUDENT)) {
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                FinalGrade finalGrade = extractFinalGrade(rs);
                finalGrades.add(finalGrade);
            }
        } catch (SQLException e) {
            logger.error("Cannot find all final grades by student_id");
            throw new DatabaseException("Cannot find all final grades by student_id", e);
        }
        return finalGrades;
    }
}
