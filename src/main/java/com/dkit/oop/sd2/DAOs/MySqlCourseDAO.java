package com.dkit.oop.sd2.DAOs;

import com.dkit.oop.sd2.DTOs.Course;
import com.dkit.oop.sd2.DTOs.Department;
import com.dkit.oop.sd2.Exceptions.DaoException;

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
}
