package com.dkit.oop.sd2.Server;

import com.dkit.oop.sd2.BusinessObjects.JSONConverter;
import com.dkit.oop.sd2.Protocol.Protocol_Constants;
import com.dkit.oop.sd2.Server.DAOs.*;
import com.dkit.oop.sd2.Server.DTOs.Course;
import com.dkit.oop.sd2.Server.DTOs.Department;
import com.dkit.oop.sd2.Server.DTOs.Module;
import com.dkit.oop.sd2.Server.DTOs.Student;
import com.dkit.oop.sd2.Server.Exceptions.DaoException;
import com.google.gson.Gson;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 //
 //     * Main Author: Harjappan Singh
 //     * Contributors: Meghana Rathnam , Conor Gilbert
 //     * Date: 10-April 2024
 //
 //
 */

public class Server {
    final int SERVER_PORT_NUMBER = 8881;

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    public void start() {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;

        try {
            serverSocket = new ServerSocket(SERVER_PORT_NUMBER);
            System.out.println("Server has started.");
            int clientNumber = 0;

            while (true) {
                System.out.println(
                        "Server: Listening/waiting for connections on port ..."
                                + SERVER_PORT_NUMBER);
                clientSocket = serverSocket.accept(); //waits till a client gets connected to server
                clientNumber++;
                System.out.println("Server: Listening for connections on port ..."
                        + SERVER_PORT_NUMBER);

                System.out.println(
                        "Server: Client " + clientNumber + " has connected.");
                System.out.println(
                        "Server: Port number of remote client: " + clientSocket.getPort());
                Thread t = new Thread(new ClientHandler(clientSocket, clientNumber)); // assign an individual thread to a client
                t.start();

                System.out.println("Server: ClientHandler started in thread "
                        + t.getName() + " for client " + clientNumber + ". ");
            }
        } catch (IOException ex) {
            System.out.println(ex);
        } finally {
            try {
                if (clientSocket != null)
                    clientSocket.close();
            } catch (IOException e) {
                System.out.println(e);
            }
            try {
                if (serverSocket != null)
                    serverSocket.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        System.out.println("Server: Server exiting, Goodbye!");
    }
}

class ClientHandler implements Runnable {
    BufferedReader socketReader;
    PrintWriter socketWriter;
    Socket clientSocket;
    final int clientNumber;
    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;
    private static String[] imgList = null;
    private static StudentDaoInterface IStudentDao = new MySqlStudentDao();
    private static DepartmentDAOInterface IDepartmentDao = new MySqlDepartmentDAO();
    private static ModuleDAOInterface IModuleDao = new MySqlModuleDAO();
    private static CourseDAOInterface ICourseDao = new MySqlCourseDAO();

    private static  Gson gson = new Gson();

    // Constructor
    public ClientHandler(Socket clientSocket, int clientNumber) {
        this.clientSocket = clientSocket;
        this.clientNumber = clientNumber;
        try {
            this.socketWriter = new PrintWriter(clientSocket.getOutputStream(), true);
            this.socketReader = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * run() method is called by the Thread it is assigned to.
     * This code runs independently of all other threads.
     */
    @Override
    public void run() {
        String request;
        try {
            while ((request = socketReader.readLine()) != null) {
                System.out.println("Server: (ClientHandler): Read command from client "
                        + clientNumber + ": " + request);

                // Implementation of our PROTOCOL
                if (request.startsWith(Protocol_Constants.DISPLAY_STUDENT_BY_ID)) {
                    try {
                        int id;
                        id = Integer.valueOf(request.substring(21));
                        Student student = IStudentDao.findStudentById(id);

                        if (student != null) {
                            String studentJSON = JSONConverter.studentToJson(student);
                            socketWriter.println(studentJSON);
                        } else {
                            String msg = "Student with student ID - " + id + " not found";
                            socketWriter.println(JSONConverter.msgToJson(msg));
                        }
                    } catch (DaoException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Server message: response sent to client.");
                } else if (request.startsWith(
                        Protocol_Constants.DISPLAY_COURSE_BY_ID)) {
                    try {
                        int id;
                        id = Integer.valueOf(request.substring(20));
                        Course cs = ICourseDao.findCourseById(id);

                        if (cs != null) {
                            String courseJSON = JSONConverter.courseToJson(cs);
                            socketWriter.println(courseJSON);
                        } else {
                            String msg = "Course with course ID - " + id + " not found";
                            socketWriter.println(JSONConverter.msgToJson(msg));
                        }
                    } catch (DaoException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Server message: response sent to client.");
                } else if (request.startsWith(
                        Protocol_Constants.DISPLAY_DEPARTMENT_BY_ID)) {
                    try {
                        int id;
                        id = Integer.valueOf(request.substring(24));
                        Department dt = IDepartmentDao.findDepartmentById(id);

                        if (dt != null) {
                            String departmentJSON = JSONConverter.departmentToJson(dt);
                            socketWriter.println(departmentJSON);
                        } else {
                            String msg = "Department with department ID - " + id + " not found";
                            socketWriter.println(JSONConverter.msgToJson(msg));
                        }
                    } catch (DaoException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Server message: response sent to client.");
                } else if (request.equals(
                        Protocol_Constants.DISPLAY_MODULE_BY_ID)) {
                    try {
                        int id;
                        id = Integer.valueOf(request.substring(20));
                        Module md = IModuleDao.findModuleById(id);

                        if (md != null) {
                            String moduleJSON = JSONConverter.moduleToJson(md);
                            socketWriter.println(moduleJSON);
                        } else {
                            String msg = "Module with module ID - " + id + " not found";
                            socketWriter.println(JSONConverter.msgToJson(msg));
                        }
                    } catch (DaoException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Server message: response sent to client.");
                } else if (request.equals(
                        Protocol_Constants.DISPLAY_ALL_STUDENTS)) {
                    try {
                        List<Student> students = IStudentDao.findAllStudents();
                        if (students.isEmpty()) {
                            String msg = "There are no students.";
                            socketWriter.println(JSONConverter.msgToJson(msg));
                        } else {
                            String studentListJSON =
                                    JSONConverter.studentsListToJson(students);
                            socketWriter.println(studentListJSON);
                        }
                    } catch (DaoException e) {
                        e.printStackTrace();
                        String msg = "Error retrieving students.";
                        socketWriter.println(JSONConverter.msgToJson(msg));
                    }
                } else if (request.equals(Protocol_Constants.DISPLAY_ALL_COURSES)) {
                    try {
                        List<Course> courses = ICourseDao.findAllCourses();
                        if (courses.isEmpty()) {
                            String msg = "There are no courses.";
                            socketWriter.println(JSONConverter.msgToJson(msg));
                        } else {
                            String courseListJSON = JSONConverter.coursesListToJson(courses);
                            socketWriter.println(courseListJSON);
                        }
                    } catch (DaoException e) {
                        e.printStackTrace();
                        String msg = "Error retrieving courses.";
                        socketWriter.println(JSONConverter.msgToJson(msg));
                    }
                } else if (request.equals(
                        Protocol_Constants.DISPLAY_ALL_DEPARTMENTS)) {
                    try {
                        List<Department> departments = IDepartmentDao.findAllDepartments();
                        if (departments.isEmpty()) {
                            String msg = "There are no departments.";
                            socketWriter.println(JSONConverter.msgToJson(msg));
                        } else {
                            String departmentListJSON =
                                    JSONConverter.departmentsListToJson(departments);
                            socketWriter.println(departmentListJSON);
                        }
                    } catch (DaoException e) {
                        e.printStackTrace();
                        String msg = "Error retrieving departments.";
                        socketWriter.println(JSONConverter.msgToJson(msg));
                    }
                } else if (request.equals(Protocol_Constants.DISPLAY_ALL_MODULES)) {
                    try {
                        List<Module> modules = IModuleDao.findAllModules();
                        if (modules.isEmpty()) {
                            String msg = "There are no modules.";
                            socketWriter.println(JSONConverter.msgToJson(msg));
                        } else {
                            String moduleListJSON = JSONConverter.modulesListToJson(modules);
                            socketWriter.println(moduleListJSON);
                        }
                    } catch (DaoException e) {
                        e.printStackTrace();
                        String msg = "Error retrieving modules.";
                        socketWriter.println(JSONConverter.msgToJson(msg));
                    }
                } else if (request.startsWith(Protocol_Constants.ADD_STUDENT)) {
                    try {
                        String studentJSONFromClient =
                                request.substring(Protocol_Constants.ADD_STUDENT.length());
                        Student student = gson.fromJson(studentJSONFromClient, Student.class);
                        Student addedStudent = IStudentDao.insertNewStudent(student);

                        if (addedStudent != null && addedStudent.getId() != 0) {
                            String responseJson = gson.toJson(addedStudent);
                            socketWriter.println(responseJson);
                        } else {
                            String errorJson =
                                    "{\"error\":\"Failed to add student\",\"message\":\"Operation failed\"}";
                            socketWriter.println(errorJson);
                        }
                    } catch (DaoException e) {
                        String errorJson =
                                "{\"error\":\"Error adding student\",\"message\":\""
                                        + e.getMessage() + "\"}";
                        socketWriter.println(errorJson);
                    }
                } else if (request.startsWith(Protocol_Constants.ADD_COURSE)) {
                    try {
                        String courseJSONFromClient =
                                request.substring(Protocol_Constants.ADD_COURSE.length());

                        Course course = gson.fromJson(courseJSONFromClient, Course.class);
                        Course addedCourse = ICourseDao.insertNewCourse(course);

                        if (addedCourse != null && addedCourse.getCourseID() != 0) {
                            String responseJson = gson.toJson(addedCourse);
                            socketWriter.println(responseJson);
                        } else {
                            String errorJson =
                                    "{\"error\":\"Failed to add course\",\"message\":\"Operation failed\"}";
                            socketWriter.println(errorJson);
                        }
                    } catch (DaoException e) {
                        String errorJson =
                                "{\"error\":\"Error adding course\",\"message\":\""
                                        + e.getMessage() + "\"}";
                        socketWriter.println(errorJson);
                    }
                } else if (request.startsWith(Protocol_Constants.ADD_DEPARTMENT)) {
                    try {
                        String departmentJSONFromClient =
                                request.substring(Protocol_Constants.ADD_DEPARTMENT.length());
                        Department department = gson.fromJson(departmentJSONFromClient, Department.class);
                        Department addedDepartment =
                                IDepartmentDao.insertNewDepartment(department);

                        if (addedDepartment != null
                                && addedDepartment.getDepartmentID() != 0) {
                            String responseJson = gson.toJson(addedDepartment);
                            socketWriter.println(responseJson);
                        } else {
                            String errorJson =
                                    "{\"error\":\"Failed to add department\",\"message\":\"Operation failed\"}";
                            socketWriter.println(errorJson);
                        }
                    } catch (DaoException e) {
                        String errorJson =
                                "{\"error\":\"Error adding department\",\"message\":\""
                                        + e.getMessage() + "\"}";
                        socketWriter.println(errorJson);
                    }
                } else if (request.startsWith(Protocol_Constants.ADD_MODULE)) {
                    try {
                        String moduleJSONFromClient =
                                request.substring(Protocol_Constants.ADD_MODULE.length());
                        Module module = gson.fromJson(moduleJSONFromClient, Module.class);

                        Module addedModule = IModuleDao.insertNewModule(module);

                        if (addedModule != null && addedModule.getModuleID() != 0) {
                            String responseJson = gson.toJson(addedModule);
                            socketWriter.println(responseJson);
                        } else {
                            String errorJson =
                                    "{\"error\":\"Failed to add module\",\"message\":\"Operation failed\"}";
                            socketWriter.println(errorJson);
                        }
                    } catch (DaoException e) {
                        String errorJson =
                                "{\"error\":\"Error adding module\",\"message\":\""
                                        + e.getMessage() + "\"}";
                        socketWriter.println(errorJson);
                    }
                } else if (request.startsWith(Protocol_Constants.DELETE_STUDENT_BY_ID)) {
                    try {
                        int id = Integer.parseInt(request.split(" ")[1]);
                        int result = IStudentDao.deleteStudentById(id);
                        socketWriter.println(result > 0
                                ? "{\"message\":\"Student deleted successfully.\"}"
                                : "{\"message\":\"Failed to delete student.\"}");
                    } catch (Exception e) {
                        String errorJson =
                                "{\"error\":\"Error deleting student\",\"message\":\""
                                        + e.getMessage() + "\"}";
                        socketWriter.println(errorJson);
                    }
                } else if (request.startsWith(Protocol_Constants.DELETE_COURSE_BY_ID)) {
                    try {
                        int id = Integer.parseInt(request.split(" ")[1]);
                        int result = ICourseDao.deleteCourseById(id);
                        socketWriter.println(result > 0
                                ? "{\"message\":\"Course deleted successfully.\"}"
                                : "{\"message\":\"Failed to delete course.\"}");
                    } catch (Exception e) {
                        String errorJson =
                                "{\"error\":\"Error deleting course\",\"message\":\""
                                        + e.getMessage() + "\"}";
                        socketWriter.println(errorJson);
                    }
                } else if (request.startsWith(
                        Protocol_Constants.DELETE_DEPARTMENT_BY_ID)) {
                    try {
                        int id = Integer.parseInt(request.split(" ")[1]);
                        int result = IDepartmentDao.deleteDepartmentById(id);
                        socketWriter.println(result > 0
                                ? "{\"message\":\"Department deleted successfully.\"}"
                                : "{\"message\":\"Failed to delete department.\"}");
                    } catch (Exception e) {
                        String errorJson =
                                "{\"error\":\"Error deleting department\",\"message\":\""
                                        + e.getMessage() + "\"}";
                        socketWriter.println(errorJson);
                    }
                } else if (request.startsWith(Protocol_Constants.DELETE_MODULE_BY_ID)) {
                    try {
                        int id = Integer.parseInt(request.split(" ")[1]);
                        int result = IModuleDao.deleteModuleById(id);
                        socketWriter.println(result > 0
                                ? "{\"message\":\"Module deleted successfully.\"}"
                                : "{\"message\":\"Failed to delete module.\"}");
                    } catch (Exception e) {
                        String errorJson =
                                "{\"error\":\"Error deleting module\",\"message\":\""
                                        + e.getMessage() + "\"}";
                        socketWriter.println(errorJson);
                    }
                } else if (request.startsWith(Protocol_Constants.GET_ALL_IMAGES)) {
                    try {
                        imgList =
                                Files
                                        .list(Paths.get(
                                                "src/main/java/com/dkit/oop/sd2/Server/StudentImages/"))
                                        .filter(Files::isRegularFile)
                                        .map(Path::getFileName)
                                        .map(Path::toString)
                                        .toArray(String[] ::new);
                        String imagesList = JSONConverter.imageListToJson(imgList);
                        socketWriter.println(imagesList);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (request.startsWith("GET_IMG_")) {
                    int imgIndex = Integer.parseInt(request.substring(8));
                    dataInputStream = new DataInputStream(clientSocket.getInputStream());
                    dataOutputStream =
                            new DataOutputStream(clientSocket.getOutputStream());

                    //                        imgList[imgIndex];
                    System.out.println("Sending the File to the Client");
                    // Call SendFile Method
                    try {
                        sendFile("src/main/java/com/dkit/oop/sd2/Server/StudentImages/"
                                + imgList[imgIndex]); // hardcode location for convenience
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    dataInputStream.close();
                    dataOutputStream.close();
                } else if (request.startsWith("quit")) {
                    socketWriter.println("Sorry to see you leaving. Goodbye.");
                    System.out.println(
                            "Server message: Client has notified us that it is quitting.");
                } else {
                    socketWriter.println(
                            "error I'm sorry I don't understand your request");
                    System.out.println("Server message: Invalid request from client.");
                }
            }
        } catch (IOException ex) {
            //            ex.printStackTrace();
            System.out.println("Client might have disconnected");
        } finally {
            this.socketWriter.close();
            try {
                this.socketReader.close();
                this.clientSocket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("Server: (ClientHandler): Handler for Client "
                + clientNumber + " is terminating .....");
    }

    private static void sendFile(String path) throws Exception {
        int bytes = 0;
        File file = new File(path);
        FileInputStream fileInputStream = new FileInputStream(file);

        dataOutputStream.writeLong(file.length());

        byte[] buffer = new byte[4 * 1024]; // 4 kilobyte buffer

        while ((bytes = fileInputStream.read(buffer)) != -1) {
            dataOutputStream.write(buffer, 0, bytes);
            dataOutputStream.flush(); // force the data into the stream
        }
        fileInputStream.close();
    }
}
