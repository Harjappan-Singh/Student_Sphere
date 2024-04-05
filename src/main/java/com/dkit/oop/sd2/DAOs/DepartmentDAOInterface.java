package com.dkit.oop.sd2.DAOs;

import com.dkit.oop.sd2.DTOs.Department;
import com.dkit.oop.sd2.Exceptions.DaoException;

import java.util.List;

public interface DepartmentDAOInterface
{
    public List<Department> findAllDepartments() throws DaoException;
}

