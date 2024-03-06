package com.dkit.oop.sd2.BusinessObjects;
import com.dkit.oop.sd2.DAOs.MySqlStudentDao;
import com.dkit.oop.sd2.DAOs.StudentDaoInterface;
import com.dkit.oop.sd2.DTOs.Student;
import com.dkit.oop.sd2.Exceptions.DaoException;
import java.util.List;

public class App
{
    public static void main(String[] args)
    {
        StudentDaoInterface IStudentDao = new MySqlStudentDao();
        try
        {
            System.out.println("\nCall findAllStudents()");
            List<Student> students = IStudentDao.findAllStudents();

            if( students.isEmpty() )
                System.out.println("There are no students");
            else {
                for (Student st : students)
                    System.out.println("Student: " + st.toString());
            }
        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }
    }
}
