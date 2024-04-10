package com.dkit.oop.sd2.Server.DAOs;

import com.dkit.oop.sd2.Server.DTOs.Student;
import com.dkit.oop.sd2.Server.Exceptions.DaoException;

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
                int graduationYear = resultSet.getInt("graduation_year");
                boolean hasPaidFullFee = resultSet.getBoolean("has_paid_full_fee");
                double currentGPA = resultSet.getInt("current_gpa");
                int courseId = resultSet.getInt("course_id");

                Student s = new Student(id, firstName,lastName, birthDate, studentEmail, studentPhone, address, graduationYear,hasPaidFullFee, currentGPA, courseId);
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
//
//    /**
//
//     * Author: Harjappan Singh
//
//     * Date: 6-Mar 2024
//
//     */
    @Override
    public Student findStudentById(int id) throws DaoException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Student s = null;
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
//                Date birthDate = Date.parse(resultSet.getString("birth_date"));
                String studentEmail = resultSet.getString("student_email");
                String studentPhone = resultSet.getString("student_phone");
                String address = resultSet.getString("address");
                int graduationYear = resultSet.getInt("graduation_year");
                boolean hasPaidFullFee = resultSet.getBoolean("has_paid_full_fee");
                double currentGPA = resultSet.getInt("current_gpa");
                int courseId = resultSet.getInt("course_id");

                 s = new Student(id, firstName,lastName, birthDate, studentEmail, studentPhone, address, graduationYear,hasPaidFullFee, currentGPA, courseId);
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
        return s;
    }
//
//    /**
//
//     * Author: Conor Gilbert
//
//     * Date: 7-Mar 2024
//
//     */
//    @Override
//    public int deleteStudentById(int id) throws DaoException
//    {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        int affectedRows;
//
//        try
//            {
//                connection = this.getConnection();
//                String query = "DELETE  FROM STUDENTS WHERE id = ?";
//                preparedStatement = connection.prepareStatement(query);
//                preparedStatement.setInt(1, id);
//                affectedRows = preparedStatement.executeUpdate();
//                if (affectedRows == 0) {
//                    throw new DaoException("Delete student failed, no rows affected.");
//                }
//
//        } catch (SQLException e) {
//            throw new DaoException("deleteStudentByIdResultSet() " + e.getMessage());
//        } finally {
//                try {
//
//                    if (preparedStatement != null) {
//                        preparedStatement.close();
//                    }
//                    if (connection != null) {
//                        freeConnection(connection);
//                    }
//                } catch (SQLException e) {
//                    throw new DaoException("deleteStudentById() " + e.getMessage());
//                }
//            }
//
//            return affectedRows;
//        }
//
//
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


            String query = "INSERT INTO STUDENTS(id,first_name, last_name, birth_date, student_email, student_phone, address,  graduation_year,has_paid_full_fee, current_gpa,course_id) " +
                    "VALUES (null,?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(query);


            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setDate(3, student.getBirthDate());
            preparedStatement.setString(4, student.getStudentEmail());
            preparedStatement.setString(5, student.getStudentPhone());
            preparedStatement.setString(6, student.getAddress());
            preparedStatement.setInt(7, student.getGraduationYear());
            preparedStatement.setBoolean(8, student.isHasPaidFullFee());
            preparedStatement.setDouble(9, student.getCurrentGPA());
            preparedStatement.setInt(10, student.getCourseId());


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

    /**
     * Author: Meghana Rathnam
     * Date: 15-Mar 2024
     */
    @Override
    public void updateStudentById(int id, Student student) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.getConnection();

            String query = "UPDATE STUDENTS SET first_name = ?, last_name = ?, birth_date = ?, student_email = ?, student_phone = ?, " +
                    "address = ?, graduation_year = ?,  has_paid_full_fee = ?,   current_gpa = ? , course_id = ? " +
                    "WHERE id = ?";


            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setDate(3, student.getBirthDate());
            preparedStatement.setString(4, student.getStudentEmail());
            preparedStatement.setString(5, student.getStudentPhone());
            preparedStatement.setString(6, student.getAddress());
            preparedStatement.setInt(7, student.getGraduationYear());
            preparedStatement.setBoolean(8, student.isHasPaidFullFee());
            preparedStatement.setDouble(9, student.getCurrentGPA());
            preparedStatement.setInt(10, student.getCourseId());
            preparedStatement.setInt(11, student.getId());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new DaoException("Updating student failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DaoException("updateStudent() " + e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("updateStudent() finally " + e.getMessage());
            }
        }
    }
//    /**
//     * Author: Conor Gilbert
//     * Date: 15-Mar 2024
//     */
//    @Override
//    public List<Student> findStudentUsingFilter(int age) throws DaoException {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        List<Student> filteredStudentList = new ArrayList<>();
//        ResultSet resultSet = null;
//        try {
//            connection = this.getConnection();
//            String query = "SELECT * FROM STUDENTS WHERE TIMESTAMPDIFF(YEAR, birth_date, CURDATE()) = ?";
//            preparedStatement = connection.prepareStatement(query);
//            preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setInt(1, age);
//            resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                int id = resultSet.getInt("id");
//                String firstName = resultSet.getString("first_name");
//                String lastName = resultSet.getString("last_name");
//                Date birthDate = resultSet.getDate("birth_date");
//                String studentEmail = resultSet.getString("student_email");
//                String studentPhone = resultSet.getString("student_phone");
//                String address = resultSet.getString("address");
//                String courseFullName = resultSet.getString("course_full_name");
//                String courseStatus = resultSet.getString("course_status");
//                boolean hasPaidFullFee = resultSet.getBoolean("has_paid_full_fee");
//                String classGroup = resultSet.getString("class_group");
//                int graduationYear = resultSet.getInt("graduation_year");
//                double currentGPA = resultSet.getInt("current_gpa");
//
//                Student s = new Student(id, firstName, lastName, birthDate, studentEmail, studentPhone, address, courseFullName, courseStatus, hasPaidFullFee, classGroup, graduationYear, currentGPA);
//                filteredStudentList.add(s);
//            }
//        } catch (SQLException e) {
//            throw new DaoException("findStudentUsingFilter() " + e.getMessage());
//        } finally {
//            try {
//                if (preparedStatement != null) {
//                    preparedStatement.close();
//                }
//                if (connection != null) {
//                    freeConnection(connection);
//                }
//            } catch (SQLException e) {
//                throw new DaoException("findStudentUsingFilter() finally " + e.getMessage());
//            }
//        }
//        return filteredStudentList;
//    }
}
