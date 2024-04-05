package com.dkit.oop.sd2.DAOs;

import com.dkit.oop.sd2.DTOs.Department;
import com.dkit.oop.sd2.DTOs.Module;
import com.dkit.oop.sd2.Exceptions.DaoException;

import java.util.List;

public interface ModuleDAOInterface
{
    public List<Module> findAllModules() throws DaoException;
}

