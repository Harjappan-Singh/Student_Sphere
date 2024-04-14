package com.dkit.oop.sd2.Client;

import com.dkit.oop.sd2.BusinessObjects.JSONConverter;
import com.dkit.oop.sd2.Protocol.Protocol_Constants;
import com.dkit.oop.sd2.Server.DTOs.Course;
import com.dkit.oop.sd2.Server.DTOs.Department;
import com.dkit.oop.sd2.Server.DTOs.Module;
import com.dkit.oop.sd2.Server.DTOs.Student;
import com.dkit.oop.sd2.Server.Exceptions.DaoException;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Date;
import java.util.Scanner;

/**
 //
 //     * Author: Harjappan Singh
 //
 //     * Date: 10-April 2024
 //
 //     */
public class Client {
    private static Gson gson = new Gson();
    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }

    public void start() {

        try (   // create socket to connect to the server
                Socket socket = new Socket("localhost", 8881);
                // get the socket's input and output streams, and wrap them in writer and readers
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            System.out.println("Client message: The Client is running and has connected to the server");
            //ask user to enter a command
            String userRequest = displayMenu();

            while(true) {
                // send the command to the server on the socket
                out.println(userRequest);      // write the request to socket along with a newline terminator (which is required)
                // out.flush();                      // flushing buffer NOT necessary as auto flush is set to true

                // process the answer returned by the server
                //
                    if(userRequest.startsWith(Protocol_Constants.DISPLAY_STUDENT_BY_ID)){
                        String userString = in.readLine(); // (blocks) waits for response from server, then input string terminated by a newline character ("\n")
                        System.out.println("----Client message: Response from server after \"Display Student By ID - \" request----");
                        Gson gsonParser = new Gson();
                        Student student = gsonParser.fromJson(userString, Student.class);
                        System.out.println(student);
                    } else if (userRequest.startsWith(Protocol_Constants.DISPLAY_COURSE_BY_ID)) {
                        String userString = in.readLine();
                        System.out.println("----Client message: Response from server after \"Display Course By ID  - \" request----");
                        Gson gsonParser = new Gson();
                        Course cs = gsonParser.fromJson(userString, Course.class);
                        System.out.println(cs);
                    } else if (userRequest.startsWith(Protocol_Constants.DISPLAY_DEPARTMENT_BY_ID)) {
                        String userString = in.readLine();
                        System.out.println("----Client message: Response from server after \"Display Department By ID - \" request----");
                        Gson gsonParser = new Gson();
                        Department dt = gsonParser.fromJson(userString, Department.class);
                        System.out.println(dt);
                    } else if (userRequest.startsWith(Protocol_Constants.DISPLAY_MODULE_BY_ID)) {
                        String userString = in.readLine();
                        System.out.println("----Client message: Response from server after \"Display Module By ID - \" request----");
                        Gson gsonParser = new Gson();
                        Module md = gsonParser.fromJson(userString, Module.class);
                        System.out.println(md);
                    }
                    else if (userRequest.startsWith(Protocol_Constants.ADD_STUDENT)) {
                        String response = in.readLine();
                        System.out.println("----Client message: Response from server after \"Add Student\" request----");
                        if (response != null && response.startsWith("{")) {
                            try {
                                Gson gsonParser = new Gson();
                                Student newStudent = gsonParser.fromJson(response, Student.class);
                                System.out.println("-----------------------New Student Added Successfully---------------");
                                System.out.println("Added Student: " + newStudent);
                            } catch (Exception e) {
                                System.out.println("Failed to parse response: " + e.getMessage());
                            }
                        } else {
                            System.out.println("Server response: " + response);
                        }
                    }
                    else if (userRequest.startsWith(Protocol_Constants.ADD_COURSE)) {
                        String response = in.readLine();
                        System.out.println("----Client message: Response from server after \"Add Course\" request----");
                        if (response != null && response.startsWith("{")) {
                            try {
                                Gson gsonParser = new Gson();
                                Course newCourse = gsonParser.fromJson(response, Course.class);
                                System.out.println("-----------------------New Course Added Successfully---------------");
                                System.out.println("Added Course: " + newCourse);
                            } catch (Exception e) {
                                System.out.println("Failed to parse response: " + e.getMessage());
                            }
                        } else {
                            System.out.println("Server response: " + response);
                        }
                    }
                    else if (userRequest.startsWith(Protocol_Constants.ADD_DEPARTMENT)) {
                        String response = in.readLine();
                        System.out.println("----Client message: Response from server after \"Add Department\" request----");
                        if (response != null && response.startsWith("{")) {
                            try {
                                Gson gsonParser = new Gson();
                                Department newDepartment = gsonParser.fromJson(response, Department.class);
                                System.out.println("-----------------------New Department Added Successfully---------------");
                                System.out.println("Added Department: " + newDepartment);
                            } catch (Exception e) {
                                System.out.println("Failed to parse response: " + e.getMessage());
                            }
                        } else {
                            System.out.println("Server response: " + response);
                        }
                    }
                    else if (userRequest.startsWith(Protocol_Constants.ADD_MODULE)) {
                        String response = in.readLine();
                        System.out.println("----Client message: Response from server after \"Add Module\" request----");
                        if (response != null && response.startsWith("{")) {
                            try {
                                Gson gsonParser = new Gson();
                                Module newModule = gsonParser.fromJson(response, Module.class);
                                System.out.println("-----------------------New Module Added Successfully---------------");
                                System.out.println("Added Module: " + newModule);
                            } catch (Exception e) {
                                System.out.println("Failed to parse response: " + e.getMessage());
                            }
                        } else {
                            System.out.println("Server response: " + response);
                        }
                    }
                    else if (userRequest.startsWith("quit")) // if the user has entered the "quit" command
                {
                    String response = in.readLine();   // wait for response -
                    System.out.println("Client message: Response from server: \"" + response + "\"");
                    break;  // break out of while loop, client will exit.
                }
                else {
                    System.out.println("Command unknown. Try again.");
                }
                userRequest = displayMenu();
            }
        } catch (IOException e) {
            System.out.println("Client message: IOException: " + e);
        }
        // sockets and streams are closed automatically due to try-with-resources
        // so no finally block required here.

        System.out.println("Exiting client, but server may still be running.");
    }
    /**
     //
     //     * Author: Harjappan Singh
     //
     //     * Date: 5-April 2024
     //
     //     */
    public static String displayMenu(){
        int userInput = 0;
        String query ="";

        do {
//            System.out.println("1. Display options");
            System.out.println("2. Display by unique id options");
//            System.out.println("3. Delete student by an id");
            System.out.println("4. Add new entity");
//            System.out.println("5. Update Options");
//            System.out.println("6. Filter students by their age");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            Scanner sc = new Scanner(System.in);
            userInput = sc.nextInt();


            switch (userInput){
                case 1:
//                    displayOption();
                    break;
                case 2:
                    query = displayByIDOption();
                    return query;
                case 3:
//                    deleteByIdOption();
                    break;
                case 4:
                    query = addEntityOptions();
                    return query;
                case 5:
//                    updateOptions();
                    break;
                case 6:
//                    findStudentUsingFilterOption();
                    break;
                case 7:
                    query = "quit";
                    break;
                default:
                    System.out.println("Enter a valid option");
            }
        } while (userInput !=7);
        return query;
    }

    /**
     //
     //     * Author: Harjappan Singh
     //
     //     * Date: 5-April 2024
     //
     //     */
    public static String displayByIDOption(){
        int userInput = 0;
        String query ="";


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
                    query = displayStudentByID();
                    return query;
                case 2:
                    query = displayCourseByID();
                    return query;
                case 3:
                    query = displayDepartmentByID();
                    return query;
                case 4:
                    query = displayModuleByID();
                    return query;
                case 5:
                    break;
                default:
                    System.out.println("Enter a valid option");
            }

        } while (userInput !=5);
        return query;
    }

    /**
     //
     //     * Author: Harjappan Singh
     //
     //     * Date: 10-April 2024
     //
     //     */

    public static String displayStudentByID(){
        int id;
        System.out.println("Please enter the student id: ");
        Scanner kbr = new Scanner(System.in);
        id = kbr.nextInt();
        return Protocol_Constants.DISPLAY_STUDENT_BY_ID + id;
    }

    public static String displayCourseByID(){
        int id;
        System.out.println("Please enter the course id: ");
        Scanner kbr = new Scanner(System.in);
        id = kbr.nextInt();
        return Protocol_Constants.DISPLAY_COURSE_BY_ID+ id;
    }

    public static String displayDepartmentByID(){
        int id;
        System.out.println("Please enter the Department id: ");
        Scanner kbr = new Scanner(System.in);
        id = kbr.nextInt();
        return Protocol_Constants.DISPLAY_DEPARTMENT_BY_ID+ id;
    }
    public static String displayModuleByID(){
        int id;
        System.out.println("Please enter the module id: ");
        Scanner kbr = new Scanner(System.in);
        id = kbr.nextInt();
        return Protocol_Constants.DISPLAY_MODULE_BY_ID+ id;

    }
    /**
     //     //
     //     //     * Author: Meghana Rathnam
     //     //
     //     //     * Date: 9-April 2024
     //     //
     //     //     */

    public static String addEntityOptions(){
        int userInput = 0;
        String query ="";

        do {
            System.out.println("1. Add a new Student");
            System.out.println("2. Add a new Course");
            System.out.println("3. Add a new Department");
            System.out.println("4. Add a new Module");
            System.out.println("5. Back");

            Scanner sc = new Scanner(System.in);
            userInput = sc.nextInt();

            switch (userInput) {
                case 1:
                    query = insertStudentOption();
                    return query;
                case 2:
                    query = insertNewCourseOption();
                    return query;
                case 3:
                    query = insertNewDepartmentOption();
                    return query;
                case 4:
                    query = insertNewModuleOption();
                    return query;
                case 5:
                    break;
                default:
                    System.out.println("Enter a valid option");
            }

        } while (userInput !=5);
        return query;
    }

    /**
     //     //
     //     //     * Author: Meghana Rathnam
     //     //
     //     //     * Date: 12-April 2024
     //     //
     //     //     */
    public static String insertStudentOption() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter student details:");

        try {
            System.out.print("First Name: ");
            String firstName = sc.next();
            sc.nextLine();

            System.out.print("Last Name: ");
            String lastName = sc.next();
            sc.nextLine();

            System.out.print("Birth Date (YYYY-MM-DD): ");
            Date birthDate = Date.valueOf(sc.nextLine());

            System.out.print("Email: ");
            String email = sc.next();

            System.out.print("Phone: ");
            String phone = sc.next();
            sc.nextLine();

            System.out.print("Address: ");
            String address = sc.nextLine();

            System.out.print("Graduation Year: ");
            int graduationYear = sc.nextInt();

            System.out.print("Has Paid Full Fee (true/false): ");
            boolean hasPaidFullFee = sc.nextBoolean();

            System.out.print("Current GPA: ");
            double currentGPA = sc.nextDouble();

            System.out.print("Course ID: ");
            int courseId = sc.nextInt();

            Student newStudent = new Student(0, firstName, lastName, birthDate, email, phone, address, graduationYear, hasPaidFullFee, currentGPA, courseId);
            String studentJson = gson.toJson(newStudent);
            return Protocol_Constants.ADD_STUDENT + studentJson;
        } catch (Exception e) {
            System.out.println("Error preparing student data: " + e.getMessage());
            return null;
        }
    }
    /**
     //     //
     //     //     * Author: Meghana Rathnam
     //     //
     //     //     * Date: 12-April 2024
     //     //
     //     //     */
    public static String insertNewCourseOption() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter course details:");

        try {
            System.out.print("Course Name: ");
            String courseName = sc.nextLine();

            System.out.print("Course Code: ");
            String courseCode = sc.nextLine();

            System.out.print("Department ID: ");
            int departmentId = sc.nextInt();

            System.out.print("Credits: ");
            int credits = sc.nextInt();
            sc.nextLine();

            System.out.print("Level: ");
            String level = sc.nextLine();

            Course newCourse = new Course(0, courseName, courseCode, departmentId, credits, level);
            String courseJson = gson.toJson(newCourse);
            return Protocol_Constants.ADD_COURSE + courseJson;
        } catch (Exception e) {
            System.out.println("Error preparing course data: " + e.getMessage());
            return null;
        }
    }

    /**
     //     //
     //     //     * Author: Meghana Rathnam
     //     //
     //     //     * Date: 12-April 2024
     //     //
     //     //     */
    public static String insertNewDepartmentOption() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter department details:");

        try {
            System.out.print("Department Name: ");
            String departmentName = sc.nextLine();

            Department newDepartment = new Department(0, departmentName);
            String departmentJson = gson.toJson(newDepartment);
            return Protocol_Constants.ADD_DEPARTMENT + departmentJson;
        } catch (Exception e) {
            System.out.println("Error preparing department data: " + e.getMessage());
            return null;
        }
    }
    /**
     //     //
     //     //     * Author: Meghana Rathnam
     //     //
     //     //     * Date: 12-April 2024
     //     //
     //     //     */
    public static String insertNewModuleOption() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter module details:");

        try {
            System.out.print("Module Name: ");
            String moduleName = sc.nextLine();

            System.out.print("Credits: ");
            int credits = sc.nextInt();

            Module newModule = new Module(0, moduleName, credits);
            String moduleJson = gson.toJson(newModule);
            return Protocol_Constants.ADD_MODULE + moduleJson;
        } catch (Exception e) {
            System.out.println("Error preparing module data: " + e.getMessage());
            return null;
        }
    }

}

