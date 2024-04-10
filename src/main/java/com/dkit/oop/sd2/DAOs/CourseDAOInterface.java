package com.dkit.oop.sd2.DAOs;

import com.dkit.oop.sd2.DTOs.Course;
import com.dkit.oop.sd2.DTOs.Department;
import com.dkit.oop.sd2.Exceptions.DaoException;

import java.util.List;

public interface CourseDAOInterface
{
    public List<Course> findAllCourses() throws DaoException;

    public Course findCourseById(int id) throws DaoException;

    void insertNewCourse(Course course) throws DaoException;
    void updateCourseById(Course course) throws DaoException;

}

