package com.dkit.oop.sd2.Server;

import com.dkit.oop.sd2.BusinessObjects.JSONConverter;
import com.dkit.oop.sd2.Server.DAOs.*;
import com.dkit.oop.sd2.Server.DTOs.Course;
import com.dkit.oop.sd2.Server.DTOs.Department;
import com.dkit.oop.sd2.Server.DTOs.Module;
import com.dkit.oop.sd2.Server.DTOs.Student;
import com.dkit.oop.sd2.Server.Exceptions.DaoException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    final int SERVER_PORT_NUMBER = 8888;

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
                if(request.startsWith("Display By ID ")){
                    if (request.startsWith("Display By ID Student - "))
                    {
                        try
                        {
                            StudentDaoInterface IStudentDao = new MySqlStudentDao();
                            int id;
                            id = Integer.valueOf(request.substring(24));
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
                    } else if (request.startsWith("Display By ID Course - ")) {
                        try
                        {
                            CourseDAOInterface ICourseDao = new MySqlCourseDAO();
                            int id;
                            id = Integer.valueOf(request.substring(23));
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
                    } else if (request.startsWith("Display By ID Department - ")) {
                        try
                        {
                            DepartmentDAOInterface IDepartmentDao = new MySqlDepartmentDAO();
                            int id;
                            id = Integer.valueOf(request.substring(27));
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
                    } else if (request.startsWith("Display By ID Module - ")) {
                        try
                        {
                            ModuleDAOInterface IModuleDao = new MySqlModuleDAO();
                            int id;
                            id = Integer.valueOf(request.substring(23));
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
                    }
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
            ex.printStackTrace();
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
}
