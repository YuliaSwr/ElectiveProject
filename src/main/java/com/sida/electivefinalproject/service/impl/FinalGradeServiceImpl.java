package com.sida.electivefinalproject.service.impl;

import com.sida.electivefinalproject.dao.FinalGradeDAO;
import com.sida.electivefinalproject.dao.UserDao;
import com.sida.electivefinalproject.dao.factory.DaoFactoryDeliver;
import com.sida.electivefinalproject.dao.factory.MySQLFactory;
import com.sida.electivefinalproject.entity.FinalGrade;
import com.sida.electivefinalproject.entity.UserType;
import com.sida.electivefinalproject.service.FinalGradeService;

import java.sql.SQLException;
import java.util.List;

public class FinalGradeServiceImpl implements FinalGradeService {
    @Override
    public List<FinalGrade> findAll() {
        try (MySQLFactory factory = DaoFactoryDeliver.getInstance().createMySQLFactory()) {
            FinalGradeDAO finalGradeDao = factory.getFinalGradeDAO();
            return finalGradeDao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public FinalGrade findById(int id) {
        try (MySQLFactory factory = DaoFactoryDeliver.getInstance().createMySQLFactory()) {
            FinalGradeDAO finalGradeDao = factory.getFinalGradeDAO();
            return finalGradeDao.findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void addFinalGrade(FinalGrade finalGrade) {
        try (MySQLFactory factory = DaoFactoryDeliver.getInstance().createMySQLFactory()) {
            FinalGradeDAO finalGradeDao = factory.getFinalGradeDAO();
            finalGradeDao.insert(finalGrade);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteFinalGradeById(int id) {
        try (MySQLFactory factory = DaoFactoryDeliver.getInstance().createMySQLFactory()) {
            FinalGradeDAO finalGradeDao = factory.getFinalGradeDAO();
            finalGradeDao.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateFinalGrade(FinalGrade finalGrade) {
        try (MySQLFactory factory = DaoFactoryDeliver.getInstance().createMySQLFactory()) {
            FinalGradeDAO finalGradeDao = factory.getFinalGradeDAO();
            finalGradeDao.update(finalGrade);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public FinalGrade findByStudentIdAndCourseId(int studentId, int courseId) {
        try (MySQLFactory factory = DaoFactoryDeliver.getInstance().createMySQLFactory()) {
            FinalGradeDAO finalGradeDao = factory.getFinalGradeDAO();
            return finalGradeDao.findByStudentIdAndCourseId(studentId, courseId);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<FinalGrade> findByStudentId(int studentId) {
        try (MySQLFactory factory = DaoFactoryDeliver.getInstance().createMySQLFactory()) {
            FinalGradeDAO finalGradeDao = factory.getFinalGradeDAO();
            return finalGradeDao.findByStudentId(studentId);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
