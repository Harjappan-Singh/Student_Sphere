package com.dkit.oop.sd2.DAOs;

import com.dkit.oop.sd2.DTOs.Student;
import com.dkit.oop.sd2.Exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlStudentDao extends MySqlDao implements StudentDaoInterface{

    @Override
    public List<Student> findAllStudents() throws DaoException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Student> studentList = new ArrayList<>();

        try
        {
            connection = this.getConnection();

            String query = "SELECT * FROM students";
            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                int studentId = resultSet.getInt("student_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String birthDate = resultSet.getString("birth_date");
                String studentEmail = resultSet.getString("student_email");
                String studentPhone = resultSet.getString("student_phone");
                String address = resultSet.getString("address");
                String courseFullName = resultSet.getString("course_full_name");
                String courseStatus = resultSet.getString("course_status");
                boolean hasPaidFullFee = resultSet.getBoolean("has_paid_full_fee");
                String classGroup = resultSet.getString("class_group");
                int graduationYear = resultSet.getInt("graduation_year");
                double currentGPA = resultSet.getInt("current_gpa");

                Student s = new Student(studentId, firstName,lastName, birthDate, studentEmail, studentPhone, address,courseFullName, courseStatus, hasPaidFullFee,classGroup, graduationYear, currentGPA);
                studentList.add(s);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findAllStudentsResultSet() " + e.getMessage());
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
                throw new DaoException("findAllStudents() " + e.getMessage());
            }
        }
        return studentList;
    }

    @Override
    public Student findStudentById(int studentId) throws DaoException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Student student = null;
        try
        {
            connection = this.getConnection();

            String query = "SELECT * FROM STUDENTS WHERE STUDENT_ID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentId);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String birthDate = resultSet.getString("birth_date");
                String studentEmail = resultSet.getString("student_email");
                String studentPhone = resultSet.getString("student_phone");
                String address = resultSet.getString("address");
                String courseFullName = resultSet.getString("course_full_name");
                String courseStatus = resultSet.getString("course_status");
                boolean hasPaidFullFee = resultSet.getBoolean("has_paid_full_fee");
                String classGroup = resultSet.getString("class_group");
                int graduationYear = resultSet.getInt("graduation_year");
                double currentGPA = resultSet.getInt("current_gpa");

                student = new Student(studentId, firstName,lastName, birthDate, studentEmail, studentPhone, address,courseFullName, courseStatus, hasPaidFullFee,classGroup, graduationYear, currentGPA);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findStudentByIdResultSet() " + e.getMessage());
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
                throw new DaoException("findStudentById() " + e.getMessage());
            }
        }
        return student;
    }

}
