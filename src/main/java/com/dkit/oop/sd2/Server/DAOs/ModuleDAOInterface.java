package com.dkit.oop.sd2.Server.DAOs;

import com.dkit.oop.sd2.Server.DTOs.Module;
import com.dkit.oop.sd2.Server.Exceptions.DaoException;

import java.util.List;

public interface ModuleDAOInterface
{
    public List<Module> findAllModules() throws DaoException;

    public Module findModuleById(int id) throws DaoException;

    Module insertNewModule(Module module) throws DaoException;
    void updateModuleById(Module module) throws DaoException;

    int deleteModuleById(int id) throws DaoException;
    public List<Module> findModuleUsingFilter(int credit) throws DaoException;

}

