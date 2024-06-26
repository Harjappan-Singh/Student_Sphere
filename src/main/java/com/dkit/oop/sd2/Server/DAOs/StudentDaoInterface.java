package com.dkit.oop.sd2.Server.DAOs;

import com.dkit.oop.sd2.Server.DTOs.Student;
import com.dkit.oop.sd2.Server.Exceptions.DaoException;

import java.util.List;

public interface StudentDaoInterface
{
    public List<Student> findAllStudents() throws DaoException;
    public Student findStudentById(int id) throws DaoException;

    public Student insertNewStudent(Student student) throws DaoException;
    public void updateStudentById(int id, Student student) throws DaoException;
    public List<Student> findStudentUsingFilter(int age) throws DaoException;
    public int deleteStudentById(int id) throws DaoException;

}

