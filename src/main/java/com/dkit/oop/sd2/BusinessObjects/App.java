package com.dkit.oop.sd2.BusinessObjects;
import com.dkit.oop.sd2.DAOs.*;
import com.dkit.oop.sd2.DTOs.Course;
import com.dkit.oop.sd2.DTOs.Department;
import com.dkit.oop.sd2.DTOs.Module;
import com.dkit.oop.sd2.DTOs.Student;
import com.dkit.oop.sd2.Exceptions.DaoException;

import java.util.List;
import java.util.Scanner;

public class App
{
    private static StudentDaoInterface IStudentDao = new MySqlStudentDao();
    private static DepartmentDAOInterface IDepartmentDao = new MySqlDepartmentDAO();
    private static ModuleDAOInterface IModuleDao = new MySqlModuleDAO();

    private static CourseDAOInterface ICourseDao = new MySqlCourseDAO();
    public static void main(String[] args)
    {
        start();
    }
    public static void start(){
        int userInput = 0;
        do {
            System.out.println("1. Display options");
            System.out.println("2. Display by unique id options");
            System.out.println("3. Delete student by an id");
            System.out.println("4. Add a new student");
            System.out.println("5. Update an existing student by id");
            System.out.println("6. Filter students by their age");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            Scanner sc = new Scanner(System.in);
            userInput = sc.nextInt();

            switch (userInput){
                case 1:
                    displayOption();
                    break;
                case 2:
                    displayByIDOption();
                    break;
                case 3:
//                    deleteByIdOption();
                    break;
                case 4:
//                    insertStudentOption();
                    break;
                case 5:
//                    updateStudentOption();
                    break;
                case 6:
//                    findStudentUsingFilterOption();
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Enter a valid option");
            }
        } while (userInput !=7);

    }

    /**
     //
     //     * Author: Harjappan Singh
     //
     //     * Date: 5-April 2024
     //
     //     */
    public static void displayOption(){
        int userInput = 0;

        do {
            System.out.println("1. Display all students");
            System.out.println("2. Display all courses");
            System.out.println("3. Display all departments");
            System.out.println("4. Display all modules");
            System.out.println("5. Back");

            Scanner sc = new Scanner(System.in);
            userInput = sc.nextInt();

            switch (userInput) {
                case 1:
                    displayAllStudents();
                    break;
                case 2:
                    displayAllCourses();
                    break;
                case 3:
                    displayAllDepartments();
                    break;
                case 4:
                    displayAllModules();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Enter a valid option");
            }

        } while (userInput !=5);
    }

    /**
     //
     //     * Author: Harjappan Singh
     //
     //     * Date: 5-April 2024
     //
     //     */
    public static void displayAllStudents(){
        try
        {
            System.out.println("----------------ALL STUDENTS------------------------");
            List<Student> students = IStudentDao.findAllStudents();

            if( students.isEmpty() )
            {
                System.out.println("There are no students");
            }

            else {
                String studenListJSON = JSONConverter.studentsListToJson(students);
                for (Student st : students)
                    System.out.println("Student: " + st.toString());
            }
        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }
    }

    /**
     //
     //     * Author: Harjappan Singh
     //
     //     * Date: 5-April 2024
     //
     //     */

    public static void displayAllCourses(){
        try
        {
            System.out.println("----------------ALL Courses------------------------");
            List<Course> courses = ICourseDao.findAllCourses();

            if( courses.isEmpty() )
            {
                System.out.println("There are no courses");
            }

            else {
                for (Course cs : courses)
                    System.out.println("Course: " + cs.toString());
            }
        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }
    }

    /**
     //
     //     * Author: Harjappan Singh
     //
     //     * Date: 5-April 2024
     //
     //     */
    public static void displayAllDepartments(){
        try
        {
            System.out.println("----------------ALL Departments------------------------");
            List<Department> departments = IDepartmentDao.findAllDepartments();

            if( departments.isEmpty() )
            {
                System.out.println("There are no departments");
            }

            else {
                for (Department dt : departments)
                    System.out.println("Department: " + dt.toString());
            }
        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }
    }

    /**
     //
     //     * Author: Harjappan Singh
     //
     //     * Date: 5-April 2024
     //
     //     */
    public static void displayAllModules(){
        try
        {
            System.out.println("----------------ALL Modules------------------------");
            List<Module> modules = IModuleDao.findAllModules();

            if( modules.isEmpty() )
            {
                System.out.println("There are no modules");
            }

            else {
                for (Module md : modules)
                    System.out.println("Student: " + md.toString());
            }
        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }
    }

    /**
     //
     //     * Author: Harjappan Singh
     //
     //     * Date: 5-April 2024
     //
     //     */
    public static void displayByIDOption(){
        int userInput = 0;

        do {
            System.out.println("1. Display specific student by an id");
            System.out.println("2. Display specific course by an id");
            System.out.println("3. Display specific department by an id");
            System.out.println("4. Display specific module by an id");
            System.out.println("5. Back");

            Scanner sc = new Scanner(System.in);
            userInput = sc.nextInt();

            switch (userInput) {
                case 1:
                    displayStudentByID();
                    break;
                case 2:
                    displayCourseByID();
                    break;
                case 3:
                    displayDepartmentByID();
                    break;
                case 4:
                    displayModuleByID();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Enter a valid option");
            }

        } while (userInput !=5);
    }

    /**
     //
     //     * Author: Harjappan Singh
     //
     //     * Date: 5-April 2024
     //
     //     */
    public static void displayStudentByID(){
        try
        {
            int id;
            System.out.println("Please enter the student id: ");
            Scanner kbr = new Scanner(System.in);
            id = kbr.nextInt();
            Student student = IStudentDao.findStudentById(id);

            if( student != null ) {

                String studentJSON = JSONConverter.studentToJson(student);
                System.out.println("Student found: " + student);
            }
            else
                System.out.println("Student with that student ID not found");
        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }
    }
    /**
     //
     //     * Author: Harjappan Singh
     //
     //     * Date: 5-April 2024
     //
     //     */
    public static void displayCourseByID(){
        try
        {
            int id;
            System.out.println("Please enter the course id: ");
            Scanner kbr = new Scanner(System.in);
            id = kbr.nextInt();
            Course cs = ICourseDao.findCourseById(id);

            if( cs != null ) {

                System.out.println("Course found: " + cs);
            }
            else
                System.out.println("Course with that Course ID not found");
        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }
    }
    /**
     //
     //     * Author: Harjappan Singh
     //
     //     * Date: 5-April 2024
     //
     //     */
    public static void displayDepartmentByID(){
        try
        {
            int id;
            System.out.println("Please enter the department id: ");
            Scanner kbr = new Scanner(System.in);
            id = kbr.nextInt();
            Department dp = IDepartmentDao.findDepartmentById(id);

            if( dp != null ) {

                System.out.println("Department found: " + dp);
            }
            else
                System.out.println("Department with that department ID not found");
        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }
    }
    /**
     //
     //     * Author: Harjappan Singh
     //
     //     * Date: 5-April 2024
     //
     //     */
    public static void displayModuleByID(){
        try
        {
            int id;
            System.out.println("Please enter the module id: ");
            Scanner kbr = new Scanner(System.in);
            id = kbr.nextInt();
            Module md = IModuleDao.findModuleById(id);

            if( md != null ) {

                System.out.println("Module found: " + md);
            }
            else
                System.out.println("Module with that module ID not found");
        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }
    }
//    public static void deleteByIdOption()
//    {
//        try
//        {
//            int userId;
//            System.out.println("Please enter the user id: ");
//            Scanner kbr = new Scanner(System.in);
//            userId = kbr.nextInt();
//            Student student = IStudentDao.findStudentById(userId);
//            int deletedStudent = IStudentDao.deleteStudentById(userId);
//
//            if(deletedStudent>0)
//            {
//                System.out.println("Student has been deleted:  "+ student);
//            }
//            else
//            {
//                System.out.println("Could not find student");
//            }
//        }
//        catch( DaoException e )
//        {
//            e.printStackTrace();
//        }
//    }
//
//    public static void insertStudentOption(){
//        Scanner sc = new Scanner(System.in);
//
//        try {
//            System.out.println("Enter student details : ");
//
//            System.out.print("First Name: ");
//            String firstName = sc.next();
//
//            sc.nextLine();
//
//            System.out.print("Last Name: ");
//            String lastName = sc.next();
//
//            sc.nextLine();
//
//            System.out.print("Birth Date (YYYY-MM-DD): ");
//            Date birthDate = Date.valueOf(sc.nextLine());
//
//            System.out.print("Email: ");
//            String email = sc.next();
//
//            System.out.print("Phone: ");
//            String phone = sc.next();
//
//            sc.nextLine();
//
//            System.out.print("Address: ");
//            String address = sc.nextLine();
//
//            System.out.print("Course Full Name: ");
//            String courseFullName = sc.nextLine();
//
//            System.out.print("Course Status: ");
//            String courseStatus = sc.nextLine();
//
//            System.out.print("Has Paid Full Fee (true/false): ");
//            boolean hasPaidFullFee = sc.nextBoolean();
//
//            sc.nextLine();
//
//            System.out.print("Class Group: ");
//            String classGroup = sc.nextLine();
//
//            System.out.print("Graduation Year: ");
//            int graduationYear = sc.nextInt();
//
//            System.out.print("Current GPA: ");
//            double currentGPA = sc.nextDouble();
//
//            Student newStudent = new Student(firstName, lastName, birthDate, email, phone, address, courseFullName, courseStatus, hasPaidFullFee, classGroup, graduationYear, currentGPA);
//
//            IStudentDao.insertNewStudent(newStudent);
//            System.out.println("New student added successfully!");
//        } catch (DaoException e) {
//            System.out.println("Error adding new student: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//
//    /**
//     * Author: Meghana Rathnam
//     * Date: 15-Mar 2024
//     */
//    public static void updateStudentOption() {
//        Scanner sc = new Scanner(System.in);
//
//        try {
//            System.out.println("Enter the ID of the student you wish to update: ");
//            int id = sc.nextInt();
//            sc.nextLine();
//
//            Student studentToUpdate = IStudentDao.findStudentById(id);
//            if (studentToUpdate == null) {
//                System.out.println("Student with ID " + id + " not found.");
//                return;
//            }
//
//            System.out.println("Enter 'skip' for any field you do not wish to update.");
//
//            System.out.print("First Name [" + studentToUpdate.getFirstName() + "]: ");
//            String firstName = sc.nextLine();
//            if (!"skip".equalsIgnoreCase(firstName.trim())) {
//                studentToUpdate.setFirstName(firstName);
//            }
//
//            System.out.print("Last Name [" + studentToUpdate.getLastName() + "]: ");
//            String lastName = sc.nextLine();
//            if (!"skip".equalsIgnoreCase(lastName.trim())) {
//                studentToUpdate.setLastName(lastName);
//            }
//
//            System.out.print("Birth Date (YYYY-MM-DD) [" + studentToUpdate.getBirthDate() + "]: ");
//            String birthDateStr = sc.nextLine();
//            if (!"skip".equalsIgnoreCase(birthDateStr.trim())) {
//                try {
//                    Date birthDate = Date.valueOf(birthDateStr);
//                    studentToUpdate.setBirthDate(birthDate);
//                } catch (IllegalArgumentException e) {
//                    System.out.println("Invalid date format, field not updated.");
//                }
//            }
//
//            System.out.print("Email [" + studentToUpdate.getStudentEmail() + "]: ");
//            String email = sc.nextLine();
//            if (!"skip".equalsIgnoreCase(email.trim())) {
//                studentToUpdate.setStudentEmail(email);
//            }
//
//            System.out.print("Phone [" + studentToUpdate.getStudentPhone() + "]: ");
//            String phone = sc.nextLine();
//            if (!"skip".equalsIgnoreCase(phone.trim())) {
//                studentToUpdate.setStudentPhone(phone);
//            }
//
//            System.out.print("Address [" + studentToUpdate.getAddress() + "]: ");
//            String address = sc.nextLine();
//            if (!"skip".equalsIgnoreCase(address.trim())) {
//                studentToUpdate.setAddress(address);
//            }
//
//            System.out.print("Course Full Name [" + studentToUpdate.getCourseFullName() + "]: ");
//            String courseFullName = sc.nextLine();
//            if (!"skip".equalsIgnoreCase(courseFullName.trim())) {
//                studentToUpdate.setCourseFullName(courseFullName);
//            }
//
//            System.out.print("Course Status [" + studentToUpdate.getCourseStatus() + "]: ");
//            String courseStatus = sc.nextLine();
//            if (!"skip".equalsIgnoreCase(courseStatus.trim())) {
//                studentToUpdate.setCourseStatus(courseStatus);
//            }
//
//            System.out.print("Has Paid Full Fee (true/false) [" + studentToUpdate.isHasPaidFullFee() + "]: ");
//            String hasPaidFullFeeStr = sc.nextLine();
//            if (!"skip".equalsIgnoreCase(hasPaidFullFeeStr.trim())) {
//                boolean hasPaidFullFee = Boolean.parseBoolean(hasPaidFullFeeStr);
//                studentToUpdate.setHasPaidFullFee(hasPaidFullFee);
//            }
//
//            System.out.print("Class Group [" + studentToUpdate.getClassGroup() + "]: ");
//            String classGroup = sc.nextLine();
//            if (!"skip".equalsIgnoreCase(classGroup.trim())) {
//                studentToUpdate.setClassGroup(classGroup);
//            }
//
//            System.out.print("Graduation Year: ");
//            String graduationYearStr = sc.nextLine();
//            if (!"skip".equalsIgnoreCase(graduationYearStr.trim())) {
//                try {
//                    int graduationYear = Integer.parseInt(graduationYearStr);
//                    studentToUpdate.setGraduationYear(graduationYear);
//                } catch (NumberFormatException e) {
//                    System.out.println("Invalid number format, field not updated.");
//                }
//            }
//
//            System.out.print("Current GPA: ");
//            String currentGPAStr = sc.nextLine();
//            if (!"skip".equalsIgnoreCase(currentGPAStr.trim())) {
//                try {
//                    double currentGPA = Double.parseDouble(currentGPAStr);
//                    studentToUpdate.setCurrentGPA(currentGPA);
//                } catch (NumberFormatException e) {
//                    System.out.println("Invalid number format, field not updated.");
//                }
//            }
//
//            IStudentDao.updateStudentById(id, studentToUpdate);
//            System.out.println("Student updated successfully!");
//
//        } catch (DaoException e) {
//            System.out.println("Error updating student: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//    /**
//     * Author: Conor Gilbert
//     * Date: 15-Mar 2024
//     */
//    public static void findStudentUsingFilterOption ()
//    {
//        try
//        {
//            int age;
//            System.out.println("Enter an age you want to filter by: ");
//            Scanner kbr = new Scanner(System.in);
//            age = kbr.nextInt();
//            List<Student> students = IStudentDao.findStudentUsingFilter(age);
//            if(!students.isEmpty())
//            {
//                System.out.println("These are the students that are "+age+" years old");
//                System.out.printf("%-5s %-11s %-10s %-11s %-28s %-11s %-57s %-75s %-11s %-5s %-6s %-5s %-10s\n",
//                        "ID", "First Name", "Last Name", "Birth Date", "Email", "Phone", "Address", "Course Full Name",
//                        "Course Status", "Paid", "Group", "Grad Year", "GPA");
//                for(Student s : students)
//                {
//
//                    System.out.printf("%-5d %-11s %-10s %-11s %-28s %-11s %-57s %-75s %-11s %-5b %-6s %-5d %-10.2f\n",
//                            s.getid(), s.getFirstName(), s.getLastName(), s.getBirthDate(),
//                            s.getStudentEmail(), s.getStudentPhone(), s.getAddress(),
//                            s.getCourseFullName(), s.getCourseStatus(), s.isHasPaidFullFee(),
//                            s.getClassGroup(), s.getGraduationYear(), s.getCurrentGPA());
//                }
//            }
//            else
//            {
//                System.out.println("There are no students with this age");
//            }
//        }
//        catch( DaoException e )
//        {
//            e.printStackTrace();
//        }
//    }
}
