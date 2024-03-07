package com.dkit.oop.sd2.BusinessObjects;
import com.dkit.oop.sd2.DAOs.MySqlStudentDao;
import com.dkit.oop.sd2.DAOs.StudentDaoInterface;
import com.dkit.oop.sd2.DTOs.Student;
import com.dkit.oop.sd2.Exceptions.DaoException;
import java.util.List;
import java.util.Scanner;

public class App
{
    public static void main(String[] args)
    {
        menu();
    }
    public static void menu(){
        int userInput = 0;
        do {
            System.out.println("1. Display all students");
            System.out.println("2. Display student by an id");
            System.out.println("3. Exit");

            System.out.print("Choose an option: ");

            Scanner sc = new Scanner(System.in);
            userInput = sc.nextInt();

            switch (userInput){
                case 1:
                    displayAllOption();
                    break;
                case 2:
                    displayByIDOption();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Enter a valid option");
            }
        } while (userInput !=3);

    }

    public static void displayAllOption(){
        StudentDaoInterface IStudentDao = new MySqlStudentDao();
        try
        {
            System.out.println("----------------ALL STUDENTS------------------------");
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

    public static void displayByIDOption(){
        StudentDaoInterface IStudentDao = new MySqlStudentDao();
        try
        {
            int userId;
            System.out.println("Please enter the user id: ");
            Scanner kbr = new Scanner(System.in);
            userId = kbr.nextInt();
            Student student = IStudentDao.findStudentById(userId);

            if( student != null )
                System.out.println("Student found: " + student);
            else
                System.out.println("Student with that student ID not found");
        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }
    }
}
