package com.dkit.oop.sd2.Server.DAOs;

import com.dkit.oop.sd2.Server.DTOs.Department;
import com.dkit.oop.sd2.Server.Exceptions.DaoException;

import java.util.List;

public interface DepartmentDAOInterface
{
    public List<Department> findAllDepartments() throws DaoException;

    public Department findDepartmentById(int id) throws DaoException;

    Department insertNewDepartment(Department department) throws DaoException;
    void updateDepartmentById(Department department) throws DaoException;

    int deleteDepartmentById(int id) throws DaoException;
    public List<Department> findDepartmentUsingFilter(int credit) throws DaoException;

}

