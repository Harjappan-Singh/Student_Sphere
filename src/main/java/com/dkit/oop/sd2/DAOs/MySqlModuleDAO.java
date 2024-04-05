package com.dkit.oop.sd2.DAOs;

import com.dkit.oop.sd2.DTOs.Department;
import com.dkit.oop.sd2.DTOs.Module;
import com.dkit.oop.sd2.Exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

}
