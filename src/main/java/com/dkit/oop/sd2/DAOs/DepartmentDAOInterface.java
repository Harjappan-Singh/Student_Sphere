package com.dkit.oop.sd2.DAOs;

import com.dkit.oop.sd2.DTOs.Department;
import com.dkit.oop.sd2.DTOs.Module;
import com.dkit.oop.sd2.Exceptions.DaoException;

import java.util.List;

public interface DepartmentDAOInterface
{
    public List<Department> findAllDepartments() throws DaoException;

    public Department findDepartmentById(int id) throws DaoException;

}

