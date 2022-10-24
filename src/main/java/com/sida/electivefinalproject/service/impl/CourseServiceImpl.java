package com.sida.electivefinalproject.service.impl;

import com.sida.electivefinalproject.dao.CourseDAO;
import com.sida.electivefinalproject.dao.factory.DaoFactoryDeliver;
import com.sida.electivefinalproject.dao.factory.MySQLFactory;
import com.sida.electivefinalproject.entity.Specialization;
import com.sida.electivefinalproject.exception.DatabaseException;
import com.sida.electivefinalproject.entity.Course;
import com.sida.electivefinalproject.service.CourseService;

import java.sql.SQLException;
import java.util.List;

public class CourseServiceImpl implements CourseService {
    @Override
    public List<Course> findAll() {
        try (MySQLFactory factory = DaoFactoryDeliver.getInstance().createMySQLFactory()){
            CourseDAO courseDao = factory.getCourseDAO();
            return courseDao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Course> findAllByTeacherId(int teacherId) {
        try (MySQLFactory factory = DaoFactoryDeliver.getInstance().createMySQLFactory()){
            CourseDAO courseDao = factory.getCourseDAO();
            return courseDao.findAllByTeacherId(teacherId);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void addCourse(Course course) {
        try (MySQLFactory factory = DaoFactoryDeliver.getInstance().createMySQLFactory()){
            CourseDAO courseDao = factory.getCourseDAO();
            courseDao.insert(course);
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteCourse(int courseId) {
        try (MySQLFactory factory = DaoFactoryDeliver.getInstance().createMySQLFactory()){
            CourseDAO courseDao = factory.getCourseDAO();
            courseDao.delete(courseId);
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateCourse(Course course) {
        try (MySQLFactory factory = DaoFactoryDeliver.getInstance().createMySQLFactory()){
            CourseDAO courseDao = factory.getCourseDAO();
            courseDao.update(course);
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Course findById(int id) {
        try (MySQLFactory factory = DaoFactoryDeliver.getInstance().createMySQLFactory()){
            CourseDAO courseDao = factory.getCourseDAO();
            return courseDao.findById(id);
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Course> findAllBySpecialization(Specialization specialization) {
        try (MySQLFactory factory = DaoFactoryDeliver.getInstance().createMySQLFactory()){
            CourseDAO courseDao = factory.getCourseDAO();
            return courseDao.findAllCoursesBySpecialization(specialization);
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
