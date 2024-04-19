package com.dkit.oop.sd2.Server;

import com.dkit.oop.sd2.BusinessObjects.JSONConverter;
import com.dkit.oop.sd2.Protocol.Protocol_Constants;
import com.dkit.oop.sd2.Server.DAOs.*;
import com.dkit.oop.sd2.Server.DTOs.Course;
import com.dkit.oop.sd2.Server.DTOs.Department;
import com.dkit.oop.sd2.Server.DTOs.Module;
import com.dkit.oop.sd2.Server.DTOs.Student;
import com.dkit.oop.sd2.Server.Exceptions.DaoException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import com.google.gson.Gson;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;


public class Server {
    final int SERVER_PORT_NUMBER = 8881;

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    public void start() {

        ServerSocket serverSocket =null;
        Socket clientSocket =null;

        try {
            serverSocket = new ServerSocket(SERVER_PORT_NUMBER);
            System.out.println("Server has started.");
            int clientNumber = 0;

            while (true) {
                System.out.println("Server: Listening/waiting for connections on port ..." + SERVER_PORT_NUMBER);
                clientSocket = serverSocket.accept();
                clientNumber++;
                System.out.println("Server: Listening for connections on port ..." + SERVER_PORT_NUMBER);

                System.out.println("Server: Client " + clientNumber + " has connected.");
                System.out.println("Server: Port number of remote client: " + clientSocket.getPort());
                System.out.println("Server: Port number of the socket used to talk with client " + clientSocket.getLocalPort());


                Thread t = new Thread(new ClientHandler(clientSocket, clientNumber));
                t.start();

                System.out.println("Server: ClientHandler started in thread " + t.getName() + " for client " + clientNumber + ". ");

            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
        finally{
            try {
                if(clientSocket!=null)
                    clientSocket.close();
            } catch (IOException e) {
                System.out.println(e);
            }
            try {
                if(serverSocket!=null)
                    serverSocket.close();
            } catch (IOException e) {
                System.out.println(e);
            }

        }
        System.out.println("Server: Server exiting, Goodbye!");
    }
}

class ClientHandler implements Runnable
{
    BufferedReader socketReader;
    PrintWriter socketWriter;
    Socket clientSocket;
    final int clientNumber;

    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;

    private static String[] imgList = null;

    // Constructor
    public ClientHandler(Socket clientSocket, int clientNumber) {
        this.clientSocket = clientSocket;
        this.clientNumber = clientNumber;
        try {
            this.socketWriter = new PrintWriter(clientSocket.getOutputStream(), true);
            this.socketReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
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
                System.out.println("Server: (ClientHandler): Read command from client " + clientNumber + ": " + request);

                // Implementation our PROTOCOL
                    if (request.startsWith(Protocol_Constants.DISPLAY_STUDENT_BY_ID))
                    {
                        try
                        {
                            StudentDaoInterface IStudentDao = new MySqlStudentDao();
                            int id;
                            id = Integer.valueOf(request.substring(21));
                            Student student = IStudentDao.findStudentById(id);

                            if( student != null ) {
                            String studentJSON = JSONConverter.studentToJson(student);
                            socketWriter.println(studentJSON);
                            }
                            else{
                                socketWriter.println("Student with that student ID not found");
                            }
                        }
                        catch( DaoException e )
                        {
                            e.printStackTrace();
                        }
                        System.out.println("Server message: Student sent to client.");
                    } else if (request.startsWith(Protocol_Constants.DISPLAY_COURSE_BY_ID)) {
                        try
                        {
                            CourseDAOInterface ICourseDao = new MySqlCourseDAO();
                            int id;
                            id = Integer.valueOf(request.substring(20));
                            Course cs = ICourseDao.findCourseById(id);

                            if( cs != null ) {
                                String courseJSON = JSONConverter.courseToJson(cs);
                                socketWriter.println(courseJSON);
                            }
                            else{
                                socketWriter.println("Course with that course ID not found");
                            }
                        }
                        catch( DaoException e )
                        {
                            e.printStackTrace();
                        }
                        System.out.println("Server message: Course sent to client.");
                    } else if (request.startsWith(Protocol_Constants.DISPLAY_DEPARTMENT_BY_ID)) {
                        try
                        {
                            DepartmentDAOInterface IDepartmentDao = new MySqlDepartmentDAO();
                            int id;
                            id = Integer.valueOf(request.substring(24));
                            Department dt = IDepartmentDao.findDepartmentById(id);

                            if( dt != null ) {
                                String departmentJSON = JSONConverter.departmentToJson(dt);
                                socketWriter.println(departmentJSON);
                            }
                            else{
                                socketWriter.println("Department with that department ID not found");
                            }
                        }
                        catch( DaoException e )
                        {
                            e.printStackTrace();
                        }
                        System.out.println("Server message: Department sent to client.");
                    } else if (request.startsWith(Protocol_Constants.DISPLAY_MODULE_BY_ID)) {
                        try
                        {
                            ModuleDAOInterface IModuleDao = new MySqlModuleDAO();
                            int id;
                            id = Integer.valueOf(request.substring(20));
                            Module md = IModuleDao.findModuleById(id);

                            if( md != null ) {
                                String moduleJSON = JSONConverter.moduleToJson(md);
                                socketWriter.println(moduleJSON);
                            }
                            else{
                                socketWriter.println("Module with that module ID not found");
                            }
                        }
                        catch( DaoException e )
                        {
                            e.printStackTrace();
                        }
                        System.out.println("Server message: Module sent to client.");
                    } else if (request.startsWith(Protocol_Constants.ADD_STUDENT)) {
                        try {
                            String json = request.substring(Protocol_Constants.ADD_STUDENT.length());
                            Gson gson = new Gson();
                            Student student = gson.fromJson(json, Student.class);

                            StudentDaoInterface studentDao = new MySqlStudentDao();
                            Student addedStudent = studentDao.insertNewStudent(student);

                            if (addedStudent != null && addedStudent.getId() != 0) {
                                String responseJson = gson.toJson(addedStudent);
                                socketWriter.println(responseJson);
                            } else {
                                String errorJson = "{\"error\":\"Failed to add student\",\"message\":\"Operation failed\"}";
                                socketWriter.println(errorJson);
                            }
                        } catch (DaoException e) {
                            String errorJson = "{\"error\":\"Error adding student\",\"message\":\"" + e.getMessage() + "\"}";
                            socketWriter.println(errorJson);
                        }
                    }
                    else if (request.startsWith(Protocol_Constants.ADD_COURSE)) {
                        try {
                            String json = request.substring(Protocol_Constants.ADD_COURSE.length());
                            Gson gson = new Gson();
                            Course course = gson.fromJson(json, Course.class);

                            CourseDAOInterface courseDao = new MySqlCourseDAO();
                            Course addedCourse = courseDao.insertNewCourse(course);

                            if (addedCourse != null && addedCourse.getCourseID() != 0) {
                                String responseJson = gson.toJson(addedCourse);
                                socketWriter.println(responseJson);
                            } else {
                                String errorJson = "{\"error\":\"Failed to add course\",\"message\":\"Operation failed\"}";
                                socketWriter.println(errorJson);
                            }
                        } catch (DaoException e) {
                            String errorJson = "{\"error\":\"Error adding course\",\"message\":\"" + e.getMessage() + "\"}";
                            socketWriter.println(errorJson);
                        }
                    }
                    else if (request.startsWith(Protocol_Constants.ADD_DEPARTMENT)) {
                        try {
                            String json = request.substring(Protocol_Constants.ADD_DEPARTMENT.length());
                            Gson gson = new Gson();
                            Department department = gson.fromJson(json, Department.class);

                            DepartmentDAOInterface departmentDao = new MySqlDepartmentDAO();
                            Department addedDepartment = departmentDao.insertNewDepartment(department);

                            if (addedDepartment != null && addedDepartment.getDepartmentID() != 0) {
                                String responseJson = gson.toJson(addedDepartment);
                                socketWriter.println(responseJson);
                            } else {
                                String errorJson = "{\"error\":\"Failed to add department\",\"message\":\"Operation failed\"}";
                                socketWriter.println(errorJson);
                            }
                        } catch (DaoException e) {
                            String errorJson = "{\"error\":\"Error adding department\",\"message\":\"" + e.getMessage() + "\"}";
                            socketWriter.println(errorJson);
                        }
                    }
                    else if (request.startsWith(Protocol_Constants.ADD_MODULE)) {
                        try {
                            String json = request.substring(Protocol_Constants.ADD_MODULE.length());
                            Gson gson = new Gson();
                            Module module = gson.fromJson(json, Module.class);

                            ModuleDAOInterface moduleDao = new MySqlModuleDAO();
                            Module addedModule = moduleDao.insertNewModule(module);

                            if (addedModule != null && addedModule.getModuleID() != 0) {
                                String responseJson = gson.toJson(addedModule);
                                socketWriter.println(responseJson);
                            } else {
                                String errorJson = "{\"error\":\"Failed to add module\",\"message\":\"Operation failed\"}";
                                socketWriter.println(errorJson);
                            }
                        } catch (DaoException e) {
                            String errorJson = "{\"error\":\"Error adding module\",\"message\":\"" + e.getMessage() + "\"}";
                            socketWriter.println(errorJson);
                        }
                    }
                    else if (request.startsWith(Protocol_Constants.DISPLAY_ALL_STUDENTS))
                    {
                        try
                        {

                            StudentDaoInterface IStudentDao = new MySqlStudentDao();
                            List<Student> students = IStudentDao.findAllStudents();
                            if (students.isEmpty())
                            {
                                socketWriter.println("There are no students");
                            }
                            else
                            {
                                String studentListJSON = JSONConverter.studentsListToJson(students);


                                //System.out.println("JSON Response: " + studentListJSON);

                                socketWriter.println(studentListJSON);
                            }
                        }

                        catch (DaoException e)
                        {
                            e.printStackTrace();
                            socketWriter.println("Error retrieving students");
                        }
                    }
                    else if (request.startsWith(Protocol_Constants.DISPLAY_ALL_COURSES))
                    {
                        try
                        {
                            CourseDAOInterface ICourseDao = new MySqlCourseDAO();

                            List<Course> courses = ICourseDao.findAllCourses();
                            if (courses.isEmpty())
                            {
                                socketWriter.println("There are no students");
                            }
                            else
                            {
                                String courseListJSON = JSONConverter.coursesListToJson(courses);




                                socketWriter.println(courseListJSON);
                            }
                        }
                        catch (DaoException e)
                        {
                            e.printStackTrace();
                            socketWriter.println("Error retrieving courses");
                        }}
                    else if (request.startsWith(Protocol_Constants.DISPLAY_ALL_DEPARTMENTS))
                    {
                        try
                        {
                            DepartmentDAOInterface IDepartmentDao =new MySqlDepartmentDAO();

                            List<Department> departments = IDepartmentDao.findAllDepartments();
                            if (departments.isEmpty())
                            {
                                socketWriter.println("There are no departments");
                            }
                            else
                            {
                                String departmentListJSON = JSONConverter.departmentsListToJson(departments);




                                socketWriter.println(departmentListJSON);
                            }
                        }
                        catch (DaoException e)
                        {
                            e.printStackTrace();
                            socketWriter.println("Error retrieving departments");
                        }}
                    else if (request.startsWith(Protocol_Constants.DISPLAY_ALL_MODULES)) {
                        try
                        {
                            ModuleDAOInterface IModuleDao = new MySqlModuleDAO();

                            List<Module> modules = IModuleDao.findAllModules();
                            if (modules.isEmpty())
                            {
                                socketWriter.println("There are no modules");
                            }
                            else
                            {
                                String moduleListJSON = JSONConverter.modulesListToJson(modules);

                                socketWriter.println(moduleListJSON);
                            }
                        }
                        catch (DaoException e)
                        {
                            e.printStackTrace();
                            socketWriter.println("Error retrieving modules");
                        }} else if (request.startsWith(Protocol_Constants.GET_ALL_IMAGES)) {

                        try {
                            imgList = Files.list(Paths.get("src/main/java/com/dkit/oop/sd2/Server/StudentImages/"))
                                    .filter(Files::isRegularFile)
                                    .map(Path::getFileName)
                                    .map(Path::toString)
                                    .toArray(String[]::new);
                            String imagesList = JSONConverter.imageListToJson(imgList);
                            socketWriter.println(imagesList);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if (request.startsWith("GET_IMG_")) {
                        int imgIndex = Integer.parseInt(request.substring(8));
                        dataInputStream = new DataInputStream(clientSocket.getInputStream());
                        dataOutputStream = new DataOutputStream( clientSocket.getOutputStream());

//                        imgList[imgIndex];
                        System.out.println("Sending the File to the Client");
                        // Call SendFile Method
                        try{
                            sendFile("src/main/java/com/dkit/oop/sd2/Server/StudentImages/"+imgList[imgIndex]);   // hardcode location for convenience
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                        dataInputStream.close();
                        dataInputStream.close();
                    } else if (request.startsWith("quit"))
                {
                    socketWriter.println("Sorry to see you leaving. Goodbye.");
                    System.out.println("Server message: Client has notified us that it is quitting.");
                }
                else{
                    socketWriter.println("error I'm sorry I don't understand your request");
                    System.out.println("Server message: Invalid request from client.");
                }
            }
        } catch (IOException ex) {
//            ex.printStackTrace();
            System.out.println("Client might have disconeected");

        } finally {
            this.socketWriter.close();
            try {
                this.socketReader.close();
                this.clientSocket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("Server: (ClientHandler): Handler for Client " + clientNumber + " is terminating .....");
    }

    /**
     //
     //     * Author: Harjappan Singh
     //
     //     * Date: 17-April 2024
     //
     //     */
    private static void sendFile(String path)
            throws Exception
    {
        int bytes = 0;
        File file = new File(path);
        FileInputStream fileInputStream = new FileInputStream(file);

        dataOutputStream.writeLong(file.length());

        byte[] buffer = new byte[4 * 1024]; // 4 kilobyte buffer

        while ((bytes = fileInputStream.read(buffer))!= -1) {
            dataOutputStream.write(buffer, 0, bytes);
            dataOutputStream.flush();   // force the data into the stream
        }
        fileInputStream.close();
    }
}
