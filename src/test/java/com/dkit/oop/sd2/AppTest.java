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


    /**
     * Author: Meghana Rathnam
     * Date: 15-Mar 2024
     */
    @Test
    public void insertNewStudentTest() {
        try {
            Student newStudent = new Student(
                    "Joseph",
                    "Murphy",
                    Date.valueOf("2000-01-01"),
                    "d00234321@student.dkit.ie",
                    "0876567546",
                    "123 Main St",
                    "Bsc in computing in Software Development",
                    "full-time",
                    true,
                    "SD2A",
                    2027,
                    4.5
            );
            IStudentDao.insertNewStudent(newStudent);
            assertTrue(true);
        } catch (DaoException e){
            e.printStackTrace();
        }
    }

    /**
     * Author: Meghana Rathnam
     * Date: 15-Mar 2024
     */
    @Test
    public void updateStudentByIdTest() {
        try {

            Student studentToUpdate = IStudentDao.findStudentById(1);
            String newFirstName = "Hannah";

            studentToUpdate.setFirstName(newFirstName);
            IStudentDao.updateStudentById(1, studentToUpdate);

            Student updatedStudent = IStudentDao.findStudentById(1);
            assertEquals("The firstName of the student should be updated.", newFirstName, updatedStudent.getFirstName());

        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
