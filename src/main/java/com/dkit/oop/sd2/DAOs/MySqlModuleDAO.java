package com.dkit.oop.sd2.DAOs;

import com.dkit.oop.sd2.DTOs.Department;
import com.dkit.oop.sd2.DTOs.Module;
import com.dkit.oop.sd2.DTOs.Student;
import com.dkit.oop.sd2.Exceptions.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlModuleDAO extends MySqlDao implements ModuleDAOInterface {
    @Override
    public List<Module> findAllModules() throws DaoException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Module> moduleList = new ArrayList<>();

        try
        {
            connection = this.getConnection();

            String query = "SELECT * FROM Modules";
            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {

                int moduleID = resultSet.getInt("module_id");
                String moduleName = resultSet.getString("module_name");
                int credits = resultSet.getInt("credits");

                Module m = new Module(moduleID, moduleName, credits);
                moduleList.add(m);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findAllModulesResultSet() " + e.getMessage());
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
                throw new DaoException("findAllModules() " + e.getMessage());
            }
        }
        return moduleList;
    }
    @Override
    public Module findModuleById(int id) throws DaoException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Module md = null;
        try
        {
            connection = this.getConnection();

            String query = "SELECT * FROM Modules WHERE module_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                String moduleName = resultSet.getString("module_name");
                int credits = resultSet.getInt("credits");

                md = new Module(id, moduleName, credits);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findModuleByIdResultSet() " + e.getMessage());
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
                throw new DaoException("findModuleById() " + e.getMessage());
            }
        }
        return md;
    }
}
