package com.dkit.oop.sd2;

import static org.junit.Assert.*;
import com.dkit.oop.sd2.DAOs.MySqlStudentDao;
import com.dkit.oop.sd2.DAOs.StudentDaoInterface;
import com.dkit.oop.sd2.DTOs.Student;
import com.dkit.oop.sd2.Exceptions.DaoException;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    StudentDaoInterface IStudentDao = new MySqlStudentDao();
    /**
     * Rigorous Test :-)
     */
    @Test
    public void findStudentByIdTest(){
        try{
            System.out.println("Find student by ID test");


            Student s = IStudentDao.findStudentById(5);

            assertEquals(s.getStudentId(), 5);

        }catch (DaoException e){
            e.printStackTrace();
        }
    }
}
