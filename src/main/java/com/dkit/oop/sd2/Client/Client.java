package com.dkit.oop.sd2.Client;

import com.dkit.oop.sd2.Protocol.Protocol_Constants;
import com.dkit.oop.sd2.Server.DTOs.Course;
import com.dkit.oop.sd2.Server.DTOs.Department;
import com.dkit.oop.sd2.Server.DTOs.Module;
import com.dkit.oop.sd2.Server.DTOs.Student;
import com.google.gson.Gson;
import java.io.*;
import java.net.Socket;
import java.sql.Date;
import java.util.Arrays;
import java.util.Scanner;

/**
 //
 //     * Main Author: Harjappan Singh
 //     * Contributors: Meghana Rathnam , Conor Gilbert
 //     * Date: 10-April 2024
 //
 //
 */
public class Client {
    private static Gson gson = new Gson();
    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;
    private static String[] imageList = null;

    private static Gson gsonParser = new Gson();
    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }

    public void start() {
        try ( // create socket to connect to the server
              Socket socket = new Socket("localhost", 8881);

              PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
              BufferedReader in = new BufferedReader(
                      new InputStreamReader(socket.getInputStream()));) {
            System.out.println(
                    "Client message: The Client is running and has connected to the server");
            // ask user to enter a command
            String userRequest = displayMenu();

            while (true) {
                // send the command to the server on the socket
                out.println(userRequest); // write the request to socket along with a
                // newline terminator (which is required)
                // out.flush();                      // flushing buffer NOT necessary as
                // auto flush is set to true

                // process the answer returned by the server
                //
                if (userRequest.startsWith(Protocol_Constants.DISPLAY_STUDENT_BY_ID)) {
                    String response =
                            in.readLine(); // (blocks) waits for response from server, then
                    // input string terminated by a newline character
                    // ("\n")
                    System.out.println(
                            "----Client message: Response from server after \"Display Student By ID - \" request----");
                    if(response != null && response.startsWith("{\"id")){
                        Student student = gsonParser.fromJson(response, Student.class);
                        System.out.println(student);
                    } else{
                        System.out.println(gsonParser.fromJson(response, String.class));
                    }
                } else if (userRequest.startsWith(Protocol_Constants.DISPLAY_COURSE_BY_ID)) {
                    String response = in.readLine();
                    System.out.println("----Client message: Response from server after \"Display Course By ID - \" request----");
                    if (response != null && response.startsWith("{\"courseID")) {
                        Course course = gsonParser.fromJson(response, Course.class);
                        System.out.println(course);
                    } else {
                        System.out.println(gsonParser.fromJson(response, String.class));
                    }
                } else if (userRequest.startsWith(Protocol_Constants.DISPLAY_DEPARTMENT_BY_ID)) {
                    String response = in.readLine();
                    System.out.println("----Client message: Response from server after \"Display Department By ID - \" request----");
                    if (response != null && response.startsWith("{\"departmentID")) {
                        Department department = gsonParser.fromJson(response, Department.class);
                        System.out.println(department);
                    } else {
                        System.out.println(gsonParser.fromJson(response, String.class));
                    }
                } else if (userRequest.startsWith(Protocol_Constants.DISPLAY_MODULE_BY_ID)) {
                    String response = in.readLine();
                    System.out.println("----Client message: Response from server after \"Display Module By ID - \" request----");
                    if (response != null && response.startsWith("{\"moduleID")) {
                        Module module = gsonParser.fromJson(response, Module.class);
                        System.out.println(module);
                    } else {
                        System.out.println(gsonParser.fromJson(response, String.class));
                    }
                } else if (userRequest.startsWith(
                        Protocol_Constants.DISPLAY_ALL_STUDENTS)) {
                    String response = in.readLine();
                    System.out.println(
                            "----Client message: Response from server after \"Display All Students\" request----");
                    if(response != null && response.startsWith("[{\"id")){
                        Student[] students = gsonParser.fromJson(response, Student[].class);
                        for (Student student : students) {
                            System.out.println(student);
                        }
                    } else{
                        System.out.println(gsonParser.fromJson(response, String.class));
                    }
                } else if (userRequest.startsWith(Protocol_Constants.DISPLAY_ALL_COURSES)) {
                    String response = in.readLine();
                    System.out.println("----Client message: Response from server after \"Display All Courses\" request----");
                    if (response != null && response.startsWith("[{\"courseID")) {
                        Course[] courses = gsonParser.fromJson(response, Course[].class);
                        for (Course course : courses) {
                            System.out.println(course);
                        }
                    } else {
                        System.out.println(gsonParser.fromJson(response, String.class));
                    }
                } else if (userRequest.startsWith(Protocol_Constants.DISPLAY_ALL_DEPARTMENTS)) {
                    String response = in.readLine();
                    System.out.println("----Client message: Response from server after \"Display All Departments\" request----");
                    if (response != null && response.startsWith("[{\"departmentID")) {
                        Department[] departments = gsonParser.fromJson(response, Department[].class);
                        for (Department department : departments) {
                            System.out.println(department);
                        }
                    } else {
                        System.out.println(gsonParser.fromJson(response, String.class));
                    }
                } else if (userRequest.startsWith(Protocol_Constants.DISPLAY_ALL_MODULES)) {
                    String response = in.readLine();
                    System.out.println("----Client message: Response from server after \"Display All Modules\" request----");
                    if (response != null && response.startsWith("[{\"moduleID")) {
                        Module[] modules = gsonParser.fromJson(response, Module[].class);
                        for (Module module : modules) {
                            System.out.println(module);
                        }
                    } else {
                        System.out.println(gsonParser.fromJson(response, String.class));
                    }
                } else if (userRequest.startsWith(Protocol_Constants.ADD_STUDENT)) {
                    String response = in.readLine();
                    System.out.println(
                            "----Client message: Response from server after \"Add Student\" request----");
                    if (response != null && response.startsWith("{")) {
                        try {
                            Gson gsonParser = new Gson();
                            Student newStudent = gsonParser.fromJson(response, Student.class);
                            System.out.println(
                                    "-----------------------New Student Added Successfully---------------");
                            System.out.println("Added Student: " + newStudent);
                        } catch (Exception e) {
                            System.out.println("Failed to parse response: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Server response: " + response);
                    }
                } else if (userRequest.startsWith(Protocol_Constants.ADD_COURSE)) {
                    String response = in.readLine();
                    System.out.println(
                            "----Client message: Response from server after \"Add Course\" request----");
                    if (response != null && response.startsWith("{")) {
                        try {
                            Gson gsonParser = new Gson();
                            Course newCourse = gsonParser.fromJson(response, Course.class);
                            System.out.println(
                                    "-----------------------New Course Added Successfully---------------");
                            System.out.println("Added Course: " + newCourse);
                        } catch (Exception e) {
                            System.out.println("Failed to parse response: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Server response: " + response);
                    }
                } else if (userRequest.startsWith(Protocol_Constants.ADD_DEPARTMENT)) {
                    String response = in.readLine();
                    System.out.println(
                            "----Client message: Response from server after \"Add Department\" request----");
                    if (response != null && response.startsWith("{")) {
                        try {
                            Gson gsonParser = new Gson();
                            Department newDepartment =
                                    gsonParser.fromJson(response, Department.class);
                            System.out.println(
                                    "-----------------------New Department Added Successfully---------------");
                            System.out.println("Added Department: " + newDepartment);
                        } catch (Exception e) {
                            System.out.println("Failed to parse response: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Server response: " + response);
                    }
                } else if (userRequest.startsWith(Protocol_Constants.ADD_MODULE)) {
                    String response = in.readLine();
                    System.out.println(
                            "----Client message: Response from server after \"Add Module\" request----");
                    if (response != null && response.startsWith("{")) {
                        try {
                            Gson gsonParser = new Gson();
                            Module newModule = gsonParser.fromJson(response, Module.class);
                            System.out.println(
                                    "-----------------------New Module Added Successfully---------------");
                            System.out.println("Added Module: " + newModule);
                        } catch (Exception e) {
                            System.out.println("Failed to parse response: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Server response: " + response);
                    }
                } else if (userRequest.startsWith(Protocol_Constants.GET_ALL_IMAGES)) {
                    String userString = in.readLine();
                    System.out.println(
                            "----Client message: Response from server after \"Get all Images\" request----");
                    Gson gsonParser = new Gson();
                    imageList = gsonParser.fromJson(userString, String[].class);
                    System.out.println(Arrays.toString(imageList));
                    System.out.println("Enter the img index you want to download: ");
                    Scanner sc = new Scanner(System.in);
                    int imgIndex = sc.nextInt();
                    out.println("GET_IMG_" + imgIndex);
                    dataInputStream = new DataInputStream(socket.getInputStream());
                    dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    try {
                        receiveFile("src/main/java/com/dkit/oop/sd2/Client/StudentImages/"
                                + imageList[imgIndex]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    dataInputStream.close();
                    dataOutputStream.close();
                } else if (userRequest.startsWith(
                        Protocol_Constants.DELETE_STUDENT_BY_ID)) {
                    String response = in.readLine();
                    System.out.println(
                            "----Client message: Response from server after \"Delete Student\" request----");
                    String message =
                            response.substring(response.indexOf("\"message\":\"") + 10,
                                    response.lastIndexOf("\""));
                    System.out.println(message);
                } else if (userRequest.startsWith(
                        Protocol_Constants.DELETE_COURSE_BY_ID)) {
                    String response = in.readLine();
                    System.out.println(
                            "----Client message: Response from server after \"Delete Course\" request----");
                    String message =
                            response.substring(response.indexOf("\"message\":\"") + 10,
                                    response.lastIndexOf("\""));
                    System.out.println(message);
                } else if (userRequest.startsWith(
                        Protocol_Constants.DELETE_DEPARTMENT_BY_ID)) {
                    String response = in.readLine();
                    System.out.println(
                            "----Client message: Response from server after \"Delete Department\" request----");
                    String message =
                            response.substring(response.indexOf("\"message\":\"") + 10,
                                    response.lastIndexOf("\""));
                    System.out.println(message);
                } else if (userRequest.startsWith(
                        Protocol_Constants.DELETE_MODULE_BY_ID)) {
                    String response = in.readLine();
                    System.out.println(
                            "----Client message: Response from server after \"Delete Module\" request----");
                    String message =
                            response.substring(response.indexOf("\"message\":\"") + 10,
                                    response.lastIndexOf("\""));
                    System.out.println(message);
                } else if (userRequest.startsWith(
                        "quit")) // if the user has entered the "quit" command
                {
                    String response = in.readLine(); // wait for response -
                    System.out.println(
                            "Client message: Response from server: \"" + response + "\"");
                    break; // break out of while loop, client will exit.
                } else {
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
     //
     */
    public static String displayMenu() {
        int userInput = 0;
        String query = "";

        do {
            System.out.println("1. Display entity by specific id");
            System.out.println("2. Display all entities");
            System.out.println("3. Add a new entity");
            System.out.println("4. Delete an entity");
            System.out.println("5. Display images");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            Scanner sc = new Scanner(System.in);
            userInput = sc.nextInt();

            switch (userInput) {
                case 1:
                    query = displayByIDOption();
                    return query;
                case 2:
                    query = displayAllOptions();
                    return query;
                case 3:
                    query = addEntityOptions();
                    return query;
                case 4:
                    query = deleteEntityOptions();
                    return query;
                case 5:
                    query = Protocol_Constants.GET_ALL_IMAGES;
                    return query;
                case 6:
                    query = "quit";
                    break;
                default:
                    System.out.println("Enter a valid option");
            }
        } while (userInput != 6);
        return query;
    }

    /**
     //
     //     * Author: Harjappan Singh
     //
     //     * Date: 5-April 2024
     //
     //
     */
    public static String displayByIDOption() {
        int userInput = 0;
        String query = "";

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
        } while (userInput != 5);
        return query;
    }

    /**
     //
     //     * Author: Harjappan Singh
     //
     //     * Date: 10-April 2024
     //
     //
     */

    public static String displayStudentByID() {
        int id;
        System.out.println("Please enter the student id: ");
        Scanner kbr = new Scanner(System.in);
        id = kbr.nextInt();
        return Protocol_Constants.DISPLAY_STUDENT_BY_ID + id;
    }

    public static String displayCourseByID() {
        int id;
        System.out.println("Please enter the course id: ");
        Scanner kbr = new Scanner(System.in);
        id = kbr.nextInt();
        return Protocol_Constants.DISPLAY_COURSE_BY_ID + id;
    }

    public static String displayDepartmentByID() {
        int id;
        System.out.println("Please enter the Department id: ");
        Scanner kbr = new Scanner(System.in);
        id = kbr.nextInt();
        return Protocol_Constants.DISPLAY_DEPARTMENT_BY_ID + id;
    }
    public static String displayModuleByID() {
        int id;
        System.out.println("Please enter the module id: ");
        Scanner kbr = new Scanner(System.in);
        id = kbr.nextInt();
        return Protocol_Constants.DISPLAY_MODULE_BY_ID + id;
    }

    /**
     //
     //     * Author: Conor Gilbert
     //
     //     * Date: 12-April 2024
     //
     //
     */
    public static String displayAllOptions() {
        int userInput = 0;
        String query = "";

        do {
            System.out.println("1. Display all students");
            System.out.println("2. Display all courses");
            System.out.println("3. Display all departments");
            System.out.println("4. Display all modules");
            System.out.println("5. Back");

            Scanner sc = new Scanner(System.in);
            userInput = sc.nextInt();
            System.out.println(userInput);
            switch (userInput) {
                case 1:
                    query = Protocol_Constants.DISPLAY_ALL_STUDENTS;
                    return query;
                case 2:
                    query = Protocol_Constants.DISPLAY_ALL_COURSES;
                    return query;
                case 3:
                    query = Protocol_Constants.DISPLAY_ALL_DEPARTMENTS;
                    return query;
                case 4:
                    query = Protocol_Constants.DISPLAY_ALL_MODULES;
                    return query;
                case 5:
                    break;
                default:
                    System.out.println("Enter a valid option");
            }

        } while (userInput != 5);
        return query;
    }

    /**
     //     //
     //     //     * Author: Meghana Rathnam
     //     //
     //     //     * Date: 9-April 2024
     //     //
     //     //
     */

    public static String addEntityOptions() {
        int userInput = 0;
        String query = "";

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

        } while (userInput != 5);
        return query;
    }

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

            Student newStudent = new Student(0, firstName, lastName, birthDate, email,
                    phone, address, graduationYear, hasPaidFullFee, currentGPA, courseId);
            String studentJson = gson.toJson(newStudent);
            return Protocol_Constants.ADD_STUDENT + studentJson;
        } catch (Exception e) {
            System.out.println("Error preparing student data: " + e.getMessage());
            return null;
        }
    }

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

            Course newCourse =
                    new Course(0, courseName, courseCode, departmentId, credits, level);
            String courseJson = gson.toJson(newCourse);
            return Protocol_Constants.ADD_COURSE + courseJson;
        } catch (Exception e) {
            System.out.println("Error preparing course data: " + e.getMessage());
            return null;
        }
    }

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

    /**
     //
     //     * Author: Harjappan Singh
     //
     //     * Date: 17-April 2024
     //
     //
     */
    private static void receiveFile(String fileName) throws Exception {
        int bytes = 0;
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);

        long size = dataInputStream.readLong();

        byte[] buffer = new byte[4 * 1024]; // 4 kilobyte buffer

        while (size > 0
                && (bytes = dataInputStream.read(
                buffer, 0, (int) Math.min(buffer.length, size)))
                != -1) {
            fileOutputStream.write(buffer, 0, bytes);
            size = size - bytes;
        }

        System.out.println("File is Received");

        System.out.println(
                "Look in the StudentImages folder to see the transferred file");
        fileOutputStream.close();
    }

    /**
     //     //
     //     //     * Author: Meghana Rathnam
     //     //
     //     //     * Date: 20-April 2024
     //     //
     //     //
     */
    public static String deleteEntityOptions() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose an entity to delete:");
        System.out.println("1. Student");
        System.out.println("2. Course");
        System.out.println("3. Department");
        System.out.println("4. Module");
        System.out.println("5. Back");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                return deleteEntityById(Protocol_Constants.DELETE_STUDENT_BY_ID);
            case 2:
                return deleteEntityById(Protocol_Constants.DELETE_COURSE_BY_ID);
            case 3:
                return deleteEntityById(Protocol_Constants.DELETE_DEPARTMENT_BY_ID);
            case 4:
                return deleteEntityById(Protocol_Constants.DELETE_MODULE_BY_ID);
            case 5:
                return "back";
            default:
                System.out.println("Invalid choice. Please try again.");
                return "";
        }
    }

    private static String deleteEntityById(String protocolConstant) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the ID of the entity to delete: ");
        int id = sc.nextInt();
        return protocolConstant + " " + id;
    }
}
