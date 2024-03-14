package com.dkit.oop.sd2;

import static org.junit.Assert.*;
import com.dkit.oop.sd2.DAOs.MySqlStudentDao;
import com.dkit.oop.sd2.DAOs.StudentDaoInterface;
import com.dkit.oop.sd2.DTOs.Student;
import com.dkit.oop.sd2.Exceptions.DaoException;
import org.junit.Test;

import java.sql.Date;

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

            assertEquals(s.getid(), 5);

        }catch (DaoException e){
            e.printStackTrace();
        }
    }

    /**

     * Author: Harjappan Singh

     * Date: 13-Mar 2024

     */
    @Test
    public void studentToJsonTest(){
        System.out.println("Conver student to JSON string test");
        Date studentBDate = new Date(2002-04-21);
        Student s = new Student(1,"Darryl", "Noone", studentBDate, "darryl@student.dkit.ie", "353 864099119", "20 Oriel House, Dundalk, Louth", "BSc (Hons) Virtual Reality", "full-time", false, "VR2A", 2030, 3.5);
//        String studentJson = studentToJson(s );
    }
}
