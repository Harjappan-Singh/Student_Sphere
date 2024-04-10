package com.dkit.oop.sd2.Server.DAOs;

import com.dkit.oop.sd2.Server.DTOs.Department;
import com.dkit.oop.sd2.Server.Exceptions.DaoException;

import java.util.List;

public interface DepartmentDAOInterface
{
    public List<Department> findAllDepartments() throws DaoException;

    public Department findDepartmentById(int id) throws DaoException;

    void insertNewDepartment(Department department) throws DaoException;
    void updateDepartmentById(Department department) throws DaoException;

}

