package com.dkit.oop.sd2.BusinessObjects;

import com.dkit.oop.sd2.DTOs.Student;
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

     * Date: 11-Mar 2024

     */
    public static String studentsListToJson( List<Student> list ){
        Gson gsonParser = new Gson();
        String studentListJson =  gsonParser.toJson(list);
//        System.out.println("Student List JSON String is:\n" + studentListJson);
        return studentListJson;

    }
}
