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
    public Course insertNewCourse(Course course) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
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
            }   String idQuery = "SELECT course_id FROM COURSES WHERE course_code = ? ORDER BY course_id DESC LIMIT 1";
            preparedStatement = connection.prepareStatement(idQuery);
            preparedStatement.setString(1, course.getCourseCode());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                course.setCourseID(resultSet.getInt("course_id"));
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
        return course;
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
    /**
     //
     //     * Author: Conor Gilbert
     //
     //     * Date: 11-April 2024
     //
     //     */
    @Override
    public int deleteCourseById(int id) throws DaoException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int affectedRows;

        try
        {
            connection = this.getConnection();
            String query = "DELETE FROM Courses WHERE course_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new DaoException("Delete Course failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DaoException("deleteCourseByIdResultSet() " + e.getMessage()); } finally {
            try {

                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("deleteCourseById() " + e.getMessage());
            }
        }

        return affectedRows;
    }
    /**
     //
     //     * Author: Conor Gilbert
     //
     //     * Date: 11-April 2024
     //
     //     */
    @Override
    public List<Course> findCourseUsingFilter(int credit) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Course> filteredCourseList = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            connection = this.getConnection();
            String query = "SELECT * FROM Courses WHERE credits = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, credit);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int courseID = resultSet.getInt("course_id");
                String courseName = resultSet.getString("course_name");
                String courseCode = resultSet.getString("course_code");
                int departmentID = resultSet.getInt("department_id");
                int credits = resultSet.getInt("credits");
                String level = resultSet.getString("level");

                Course c = new Course(courseID, courseName, courseCode, departmentID, credits, level);
                filteredCourseList.add(c);
            }
        } catch (SQLException e) {
            throw new DaoException("findCourseUsingFilter() " + e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("findCourseUsingFilter() finally " + e.getMessage());
            }
        }
        return filteredCourseList;
    }
}
