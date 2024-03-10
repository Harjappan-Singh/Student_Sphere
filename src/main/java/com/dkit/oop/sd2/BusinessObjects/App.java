package com.dkit.oop.sd2.BusinessObjects;
import com.dkit.oop.sd2.DAOs.MySqlStudentDao;
import com.dkit.oop.sd2.DAOs.StudentDaoInterface;
import com.dkit.oop.sd2.DTOs.Student;
import com.dkit.oop.sd2.Exceptions.DaoException;
import java.util.List;
import java.util.Scanner;

public class App
{
    private static StudentDaoInterface IStudentDao = new MySqlStudentDao();
    public static void main(String[] args)
    {
        start();
    }
    public static void start(){
        int userInput = 0;
        do {
            System.out.println("1. Display all students");
            System.out.println("2. Display student by an id");
            System.out.println("3. Delete student by an id");
            System.out.println("4. Add a new student");
            System.out.println("5. Exit");

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
                    deleteByIdOption();
                    break;
                case 4:
                    insertStudentOption();
                    break;
                case 5:
                    System.out.println("ThankYou! Exiting...");
                    break;
                default:
                    System.out.println("Enter a valid option");
            }
        } while (userInput !=5);

    }

    public static void displayAllOption(){
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
        try
        {
            int id;
            System.out.println("Please enter the student id: ");
            Scanner kbr = new Scanner(System.in);
            id = kbr.nextInt();
            Student student = IStudentDao.findStudentById(id);

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
    public static void deleteByIdOption()
    {
        try
        {
            int userId;
            System.out.println("Please enter the user id: ");
            Scanner kbr = new Scanner(System.in);
            userId = kbr.nextInt();
            Student student = IStudentDao.findStudentById(userId);
            int deletedStudent = IStudentDao.deleteStudentById(userId);

            if(deletedStudent>0)
            {
                System.out.println("Student has been deleted:  "+ student);
            }
            else
            {
                System.out.println("Could not find student");
            }
        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }
    }

    public static void insertStudentOption(){
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("Enter student details : ");

            System.out.print("First Name: ");
            String firstName = sc.next();

            sc.nextLine();

            System.out.print("Last Name: ");
            String lastName = sc.next();

            sc.nextLine();

            System.out.print("Birth Date (YYYY-MM-DD): ");
            String birthDate = sc.nextLine();

            System.out.print("Email: ");
            String email = sc.next();

            System.out.print("Phone: ");
            String phone = sc.next();

            sc.nextLine();

            System.out.print("Address: ");
            String address = sc.nextLine();

            System.out.print("Course Full Name: ");
            String courseFullName = sc.nextLine();

            System.out.print("Course Status: ");
            String courseStatus = sc.nextLine();

            System.out.print("Has Paid Full Fee (true/false): ");
            boolean hasPaidFullFee = sc.nextBoolean();

            sc.nextLine();

            System.out.print("Class Group: ");
            String classGroup = sc.nextLine();

            System.out.print("Graduation Year: ");
            int graduationYear = sc.nextInt();

            System.out.print("Current GPA: ");
            double currentGPA = sc.nextDouble();

            Student newStudent = new Student(firstName, lastName, birthDate, email, phone, address, courseFullName, courseStatus, hasPaidFullFee, classGroup, graduationYear, currentGPA);

            IStudentDao.insertNewStudent(newStudent);
            System.out.println("New student added successfully!");
        } catch (DaoException e) {
            System.out.println("Error adding new student: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
