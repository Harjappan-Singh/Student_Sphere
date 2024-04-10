package com.dkit.oop.sd2.DAOs;

import com.dkit.oop.sd2.DTOs.Department;
import com.dkit.oop.sd2.DTOs.Module;
import com.dkit.oop.sd2.Exceptions.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlDepartmentDAO extends MySqlDao implements DepartmentDAOInterface {
    @Override
    public List<Department> findAllDepartments() throws DaoException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Department> departmentList = new ArrayList<>();

        try
        {
            connection = this.getConnection();

            String query = "SELECT * FROM Departments";
            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                int departmentID = resultSet.getInt("department_id");
                String departmentName = resultSet.getString("department_name");

                Department d = new Department(departmentID, departmentName);
                departmentList.add(d);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findAllDepartmentResultSet() " + e.getMessage());
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
                throw new DaoException("findAllDepartment() " + e.getMessage());
            }
        }
        return departmentList;
    }

    @Override
    public Department findDepartmentById(int id) throws DaoException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Department dp = null;
        try
        {
            connection = this.getConnection();

            String query = "SELECT * FROM Departments WHERE department_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                String departmentName = resultSet.getString("department_name");

                dp = new Department(id, departmentName);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findDepartmentByIdResultSet() " + e.getMessage());
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
                throw new DaoException("findDepartmentById() " + e.getMessage());
            }
        }
        return dp;
    }
    /**
     //
     //     * Author: Meghana Rathnam
     //
     //     * Date: 9-April 2024
     //
     //     */

    @Override
    public void insertNewDepartment(Department department) throws DaoException {
        try (Connection connection = this.getConnection()) {
            String query = "INSERT INTO Departments (department_name) VALUES (?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, department.getDepartmentName());

                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows == 0) {
                    throw new DaoException("Creating department failed, no rows affected.");
                }
            }
        } catch (SQLException e) {
            throw new DaoException("insertNewDepartment() " + e.getMessage());
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
    public void updateDepartmentById(Department department) throws DaoException {
        try (Connection connection = this.getConnection()) {
            String query = "UPDATE Departments SET department_name = ? WHERE department_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, department.getDepartmentName());
                preparedStatement.setInt(2, department.getDepartmentID());

                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows == 0) {
                    throw new DaoException("Updating department failed, no rows affected.");
                }
            }
        } catch (SQLException e) {
            throw new DaoException("updateDepartmentById() " + e.getMessage());
        }
    }
}
