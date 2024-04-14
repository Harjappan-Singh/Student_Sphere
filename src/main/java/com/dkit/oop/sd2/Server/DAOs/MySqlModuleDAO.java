package com.dkit.oop.sd2.Server.DAOs;

import com.dkit.oop.sd2.Server.DTOs.Module;
import com.dkit.oop.sd2.Server.Exceptions.DaoException;

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

    /**
     * //
     * //     * Author: Meghana Rathnam
     * //
     * //     * Date: 9-April 2024
     * //
     * //
     *
     * @return
     */
    @Override
    public Module insertNewModule(Module module) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.getConnection();

            String query = "INSERT INTO Modules (module_name, credits) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, module.getModuleName());
            preparedStatement.setInt(2, module.getCredits());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating module failed, no rows affected.");
            }

            String idQuery = "SELECT module_id FROM Modules WHERE module_name = ? ORDER BY module_id DESC LIMIT 1";
            preparedStatement = connection.prepareStatement(idQuery);
            preparedStatement.setString(1, module.getModuleName());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                module.setModuleID(resultSet.getInt("module_id"));
            } else {
                throw new DaoException("Failed to fetch module ID after insertion.");
            }

            return module;
        } catch (SQLException e) {
            throw new DaoException("insertNewModule() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("insertNewModule() finally " + e.getMessage());
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
    public void updateModuleById(Module module) throws DaoException {
        try (Connection connection = this.getConnection()) {
            String query = "UPDATE Modules SET module_name = ?, credits = ? WHERE module_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, module.getModuleName());
                preparedStatement.setInt(2, module.getCredits());
                preparedStatement.setInt(3, module.getModuleID());

                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows == 0) {
                    throw new DaoException("Updating module failed, no rows affected.");
                }
            }
        } catch (SQLException e) {
            throw new DaoException("updateModuleById() " + e.getMessage());
        }
    }
}
