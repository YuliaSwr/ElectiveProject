package com.sida.electivefinalproject.service.impl;

import com.sida.electivefinalproject.dao.EnrollmentDAO;
import com.sida.electivefinalproject.dao.factory.DaoFactoryDeliver;
import com.sida.electivefinalproject.dao.factory.MySQLFactory;
import com.sida.electivefinalproject.entity.Enrollment;
import com.sida.electivefinalproject.service.EnrollmentService;

import java.util.List;

public class EnrollmentServiceImpl implements EnrollmentService {
    @Override
    public void addEnrollment(Enrollment enrollment) {
        try (MySQLFactory factory = DaoFactoryDeliver.getInstance().createMySQLFactory()){
            EnrollmentDAO enrollmentDAO = factory.getEnrollmentDAO();
            enrollmentDAO.insert(enrollment);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Integer> getAllCoursesIdsByUser(int userId) {
        try (MySQLFactory factory = DaoFactoryDeliver.getInstance().createMySQLFactory()){
            EnrollmentDAO enrollmentDAO = factory.getEnrollmentDAO();
            return enrollmentDAO.findAllCoursesIdsByUserId(userId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Integer> getCompletedCoursesIdsByUser(int userId) {
        try (MySQLFactory factory = DaoFactoryDeliver.getInstance().createMySQLFactory()){
            EnrollmentDAO enrollmentDAO = factory.getEnrollmentDAO();
            return enrollmentDAO.findCompletedCoursesIdsByUserId(userId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Integer> getAllStudentsIdsByCourse(int courseId) {
        try (MySQLFactory factory = DaoFactoryDeliver.getInstance().createMySQLFactory()){
            EnrollmentDAO enrollmentDAO = factory.getEnrollmentDAO();
            return enrollmentDAO.findAllStudentsIdsEnrolledToCourse(courseId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteEnrollmentByStudentIdAndCourseId(int studentId, int courseId) {
        try (MySQLFactory factory = DaoFactoryDeliver.getInstance().createMySQLFactory()){
            EnrollmentDAO enrollmentDAO = factory.getEnrollmentDAO();
            enrollmentDAO.deleteByStudentIdAndCourseId(studentId, courseId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void startCourse(int studentId, int courseId) {
        try (MySQLFactory factory = DaoFactoryDeliver.getInstance().createMySQLFactory()){
            EnrollmentDAO enrollmentDAO = factory.getEnrollmentDAO();
            enrollmentDAO.updateCourseStart(studentId, courseId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Enrollment findByStudentAndCourse(int studentId, int courseId) {
        try (MySQLFactory factory = DaoFactoryDeliver.getInstance().createMySQLFactory()){
            EnrollmentDAO enrollmentDAO = factory.getEnrollmentDAO();
            return enrollmentDAO.findByStudentIdAndCourseId(studentId, courseId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
