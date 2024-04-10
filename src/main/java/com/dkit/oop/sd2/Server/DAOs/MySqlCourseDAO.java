package com.dkit.oop.sd2.Server.DAOs;

import com.dkit.oop.sd2.Server.DTOs.Course;
import com.dkit.oop.sd2.Server.Exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlCourseDAO extends MySqlDao implements CourseDAOInterface {
    @Override
    public List<Course> findAllCourses() throws DaoException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Course> courseList = new ArrayList<>();

        try
        {
            connection = this.getConnection();

            String query = "SELECT * FROM Courses";
            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {

                int courseID = resultSet.getInt("course_id");
                String courseName = resultSet.getString("course_name");
                String courseCode = resultSet.getString("course_code");
                int departmentID = resultSet.getInt("department_id");
                int credits = resultSet.getInt("credits");
                String level = resultSet.getString("level");

                Course c = new Course(courseID, courseName, courseCode, departmentID, credits, level);
                courseList.add(c);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findAllCoursesResultSet() " + e.getMessage());
        } finally
        {
            try
            {
                if (resultSet != null)
                {
                    resultSet.close();
                }
                if (preparedStatement != null)
                {
                    preparedStatement.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                throw new DaoException("findAllCourses() " + e.getMessage());
            }
        }
        return courseList;
    }

    @Override
    public Course findCourseById(int id) throws DaoException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Course cs = null;
        try
        {
            connection = this.getConnection();

            String query = "SELECT * FROM Courses WHERE course_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                String courseName = resultSet.getString("course_name");
                String courseCode = resultSet.getString("course_code");
                int departmentID = resultSet.getInt("department_id");
                int credits = resultSet.getInt("credits");
                String level = resultSet.getString("level");

                cs = new Course(id, courseName, courseCode, departmentID, credits, level);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findCourseByIdResultSet() " + e.getMessage());
        }
        {
            try
            {
                if (resultSet != null)
                {
                    resultSet.close();
                }
                if (preparedStatement != null)
                {
                    preparedStatement.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                throw new DaoException("findCourseById() " + e.getMessage());
            }
        }
        return cs;
    }
    /**
     //
     //     * Author: Meghana Rathnam
     //
     //     * Date: 9-April 2024
     //
     //     */
    @Override
    public void insertNewCourse(Course course) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.getConnection();
            String query = "INSERT INTO Courses (course_name, course_code, department_id, credits, level) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, course.getCourseName());
            preparedStatement.setString(2, course.getCourseCode());
            preparedStatement.setInt(3, course.getDepartmentID());
            preparedStatement.setInt(4, course.getCredits());
            preparedStatement.setString(5, course.getLevel());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new DaoException("Creating course failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DaoException("insertNewCourse() " + e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("insertNewCourse() finally " + e.getMessage());
            }
        }
    }
    /**
     //
     //     * Author: Meghana Rathnam
     //
     //     * Date: 9-April 2024
     //
     //     */
    @Override
    public void updateCourseById(Course course) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.getConnection();
            String query = "UPDATE Courses SET course_name = ?, course_code = ?, department_id = ?, credits = ?, level = ? WHERE course_id = ?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, course.getCourseName());
            preparedStatement.setString(2, course.getCourseCode());
            preparedStatement.setInt(3, course.getDepartmentID());
            preparedStatement.setInt(4, course.getCredits());
            preparedStatement.setString(5, course.getLevel());
            preparedStatement.setInt(6, course.getCourseID());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new DaoException("Updating course failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DaoException("updateCourseById() " + e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("updateCourseById() finally " + e.getMessage());
            }
        }
    }
}
