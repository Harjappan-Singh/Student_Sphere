package com.dkit.oop.sd2.BusinessObjects;

import com.dkit.oop.sd2.Server.DTOs.Course;
import com.dkit.oop.sd2.Server.DTOs.Department;
import com.dkit.oop.sd2.Server.DTOs.Module;
import com.dkit.oop.sd2.Server.DTOs.Student;
import com.google.gson.Gson;

import java.util.List;

public class JSONConverter {

    /**

     * Author: Harjappan Singh

     * Date: 11-Mar 2024

     */
    public static String studentToJson( Student s ){
        Gson gsonParser = new Gson();
        String studentJSON =  gsonParser.toJson(s);
//        System.out.println("Student JSON String is:\n" + studentJSON);
        return studentJSON;
    }
    /**

     * Author: Harjappan Singh

     * Date: 11-April 2024

     */
    public static String courseToJson( Course cs ){
        Gson gsonParser = new Gson();
        String courseJSON =  gsonParser.toJson(cs);
        return courseJSON;
    }
    /**

     * Author: Harjappan Singh

     * Date: 11-April 2024

     */
    public static String departmentToJson( Department dt ){
        Gson gsonParser = new Gson();
        String departmentJSON =  gsonParser.toJson(dt);
        return departmentJSON;
    }
    /**

     * Author: Harjappan Singh

     * Date: 11-April 2024

     */
    public static String moduleToJson( Module md ){
        Gson gsonParser = new Gson();
        String moduleJSON =  gsonParser.toJson(md);
        return moduleJSON;
    }



    /**

     * Author: Harjappan Singh

     * Date: 11-Mar 2024

     */
    public static String studentsListToJson( List<Student> list ){
        Gson gsonParser = new Gson();
        String studentListJson =  gsonParser.toJson(list);
//        System.out.println("Student List JSON String is:\n" + studentListJson);
        return studentListJson;

    }

    public static String departmentsListToJson( List<Department> list ){
        Gson gsonParser = new Gson();
        String departmentsListJson =  gsonParser.toJson(list);
//
        return departmentsListJson;

    }
    public static String coursesListToJson( List<Course> list ){
        Gson gsonParser = new Gson();
        String coursesListJson =  gsonParser.toJson(list);
//
        return coursesListJson;

    }
    public static String modulesListToJson( List<Module> list ){
        Gson gsonParser = new Gson();
        String modulesListJson =  gsonParser.toJson(list);
//
        return modulesListJson;

    }

    /**

     * Author: Harjappan Singh

     * Date: 17-April 2024

     */
    public  static String imageListToJson(String[] imageList){
        Gson gsonParser = new Gson();
        String imageListJson = gsonParser.toJson(imageList);
        return imageListJson;
    }
}
