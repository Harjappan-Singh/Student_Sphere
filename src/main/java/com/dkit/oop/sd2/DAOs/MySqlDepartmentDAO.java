package com.dkit.oop.sd2.DAOs;

import com.dkit.oop.sd2.DTOs.Department;
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

}
