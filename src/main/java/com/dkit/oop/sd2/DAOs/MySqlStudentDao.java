package com.dkit.oop.sd2.DAOs;

import com.dkit.oop.sd2.DTOs.Student;
import com.dkit.oop.sd2.Exceptions.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlStudentDao extends MySqlDao implements StudentDaoInterface{

    /**

     * Author: Conor Gilbert

     * Date: 7-Mar 2024

     */
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
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                Date birthDate = resultSet.getDate("birth_date");
//                Date birthDate = Date.parse(resultSet.getString("birth_date"));
                String studentEmail = resultSet.getString("student_email");
                String studentPhone = resultSet.getString("student_phone");
                String address = resultSet.getString("address");
                String courseFullName = resultSet.getString("course_full_name");
                String courseStatus = resultSet.getString("course_status");
                boolean hasPaidFullFee = resultSet.getBoolean("has_paid_full_fee");
                String classGroup = resultSet.getString("class_group");
                int graduationYear = resultSet.getInt("graduation_year");
                double currentGPA = resultSet.getInt("current_gpa");

                Student s = new Student(id, firstName,lastName, birthDate, studentEmail, studentPhone, address,courseFullName, courseStatus, hasPaidFullFee,classGroup, graduationYear, currentGPA);
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



    /**

     * Author: Harjappan Singh

     * Date: 6-Mar 2024

     */
    @Override
    public Student findStudentById(int id) throws DaoException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Student student = null;
        try
        {
            connection = this.getConnection();

            String query = "SELECT * FROM STUDENTS WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                Date birthDate = resultSet.getDate("birth_date");
                String studentEmail = resultSet.getString("student_email");
                String studentPhone = resultSet.getString("student_phone");
                String address = resultSet.getString("address");
                String courseFullName = resultSet.getString("course_full_name");
                String courseStatus = resultSet.getString("course_status");
                boolean hasPaidFullFee = resultSet.getBoolean("has_paid_full_fee");
                String classGroup = resultSet.getString("class_group");
                int graduationYear = resultSet.getInt("graduation_year");
                double currentGPA = resultSet.getInt("current_gpa");

                student = new Student(id, firstName,lastName, birthDate, studentEmail, studentPhone, address,courseFullName, courseStatus, hasPaidFullFee,classGroup, graduationYear, currentGPA);
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

    /**

     * Author: Conor Gilbert

     * Date: 7-Mar 2024

     */
    @Override
    public int deleteStudentById(int id) throws DaoException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int delStudent;

        try
        {
            connection = this.getConnection();
            String query = "DELETE  FROM STUDENTS WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            delStudent = preparedStatement.executeUpdate();

        }catch(SQLException e)
        {
            throw new DaoException("deleteStudentByIdResultSet() " + e.getMessage());
        }finally
        {
            try
            {

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
                throw new DaoException("deleteStudentById() " + e.getMessage());
            }
        }

        return delStudent;
    }


    /**

     * Author: Meghana Rathnam

     * Date: 7-Mar 2024

     */
    @Override
    public void insertNewStudent(Student student) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.getConnection();


            String query = "INSERT INTO STUDENTS(id,first_name, last_name, birth_date, student_email, student_phone, address, course_full_name, course_status, has_paid_full_fee, class_group, graduation_year, current_gpa) " +
                    "VALUES (null,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(query);


            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setDate(3, student.getBirthDate());
            preparedStatement.setString(4, student.getStudentEmail());
            preparedStatement.setString(5, student.getStudentPhone());
            preparedStatement.setString(6, student.getAddress());
            preparedStatement.setString(7, student.getCourseFullName());
            preparedStatement.setString(8, student.getCourseStatus());
            preparedStatement.setBoolean(9, student.isHasPaidFullFee());
            preparedStatement.setString(10, student.getClassGroup());
            preparedStatement.setInt(11, student.getGraduationYear());
            preparedStatement.setDouble(12, student.getCurrentGPA());


            int rowCount = preparedStatement.executeUpdate();
            System.out.println(rowCount + " row(s) affected");
        } catch (SQLException e) {
            throw new DaoException("insertNewStudent() " + e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("insertNewStudent() " + e.getMessage());
            }
        }
    }

}
