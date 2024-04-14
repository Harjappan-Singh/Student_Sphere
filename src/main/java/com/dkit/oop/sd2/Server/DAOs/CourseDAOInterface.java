package com.dkit.oop.sd2.Server.DAOs;

import com.dkit.oop.sd2.Server.DTOs.Course;
import com.dkit.oop.sd2.Server.Exceptions.DaoException;

import java.util.List;

public interface CourseDAOInterface
{
    public List<Course> findAllCourses() throws DaoException;

    public Course findCourseById(int id) throws DaoException;

    Course insertNewCourse(Course course) throws DaoException;

    void updateCourseById(Course course) throws DaoException;

}

