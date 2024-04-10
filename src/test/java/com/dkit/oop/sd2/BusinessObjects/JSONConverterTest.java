package com.dkit.oop.sd2.BusinessObjects;

import com.dkit.oop.sd2.Server.DTOs.Student;
import static org.junit.Assert.*;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;

public class JSONConverterTest {

    /**

     * Author: Harjappan Singh

     * Date: 14-Mar 2024

     */
    @Test
    public void testStudentToJson() {
        System.out.println("Convert student to JSON string test");

        Date studentBDate = Date.valueOf("2002-04-21");
        Student s = new Student(1,"Darryl", "Noone", studentBDate, "darryl@student.dkit.ie", "353 864099119", "20 Oriel House, Dundalk, Louth", "BSc (Hons) Virtual Reality", "full-time", false, "VR2A", 2030, 3.5);
        String expected = "{\"id\":1,\"firstName\":\"Darryl\",\"lastName\":\"Noone\",\"birthDate\":\"Apr 21, 2002\",\"studentEmail\":\"darryl@student.dkit.ie\",\"studentPhone\":\"353 864099119\",\"address\":\"20 Oriel House, Dundalk, Louth\",\"courseFullName\":\"BSc (Hons) Virtual Reality\",\"courseStatus\":\"full-time\",\"hasPaidFullFee\":false,\"classGroup\":\"VR2A\",\"graduationYear\":2030,\"currentGPA\":3.5}";

        String actual = JSONConverter.studentToJson(s);
        assertEquals(actual, expected);
    }


    /**

     * Author: Harjappan Singh

     * Date: 14-Mar 2024
     */

    @Test
    public void testStudentsListToJson() {
        System.out.println("Convert student to JSON string test");
        Date studentBDate1 = Date.valueOf("2000-01-01");
        Date studentBDate2 = Date.valueOf("2001-02-02");
        Date studentBDate3 = Date.valueOf("2002-03-03");
        Date studentBDate4 = Date.valueOf("2003-04-04");
        Date studentBDate5 = Date.valueOf("2004-05-05");

        Student s1 = new Student(1, "Sean", "Noone", studentBDate1, "sean1@student.dkit.ie", "353 864099119", "20 Oriel House, Dundalk, Louth", "BSc (Hons) Virtual Reality", "full-time", false, "VR2A", 2030, 3.5);
        Student s2 = new Student(2, "Luke", "Smith", studentBDate2, "luke@student.dkit.ie", "353 864099120", "21 Main Street, Dundalk, Louth", "BSc (Hons) Computer Science", "full-time", true, "CS1A", 2031, 3.8);
        Student s3 = new Student(3, "Eric", "Johnson", studentBDate3, "eric@student.dkit.ie", "353 864099121", "22 Park Avenue, Dundalk, Louth", "BSc (Hons) Mathematics", "part-time", false, "MA1B", 2029, 3.2);
        Student s4 = new Student(4, "Issac", "Davis", studentBDate4, "issac@student.dkit.ie", "353 864099122", "23 High Street, Dundalk, Louth", "BSc (Hons) Physics", "full-time", true, "PH2A", 2028, 3.6);
        Student s5 = new Student(5, "Roman", "Brown", studentBDate5, "roman@student.dkit.ie", "353 864099123", "24 Maple Avenue, Dundalk, Louth", "BSc (Hons) Chemistry", "part-time", false, "CH1B", 2027, 3.4);

        ArrayList<Student> studentList = new ArrayList<>();
        studentList.add(s1);
        studentList.add(s2);
        studentList.add(s3);
        studentList.add(s4);
        studentList.add(s5);

        String expected = "[{\"id\":1,\"firstName\":\"Sean\",\"lastName\":\"Noone\",\"birthDate\":\"Jan 1, 2000\",\"studentEmail\":\"sean1@student.dkit.ie\",\"studentPhone\":\"353 864099119\",\"address\":\"20 Oriel House, Dundalk, Louth\",\"courseFullName\":\"BSc (Hons) Virtual Reality\",\"courseStatus\":\"full-time\",\"hasPaidFullFee\":false,\"classGroup\":\"VR2A\",\"graduationYear\":2030,\"currentGPA\":3.5},{\"id\":2,\"firstName\":\"Luke\",\"lastName\":\"Smith\",\"birthDate\":\"Feb 2, 2001\",\"studentEmail\":\"luke@student.dkit.ie\",\"studentPhone\":\"353 864099120\",\"address\":\"21 Main Street, Dundalk, Louth\",\"courseFullName\":\"BSc (Hons) Computer Science\",\"courseStatus\":\"full-time\",\"hasPaidFullFee\":true,\"classGroup\":\"CS1A\",\"graduationYear\":2031,\"currentGPA\":3.8},{\"id\":3,\"firstName\":\"Eric\",\"lastName\":\"Johnson\",\"birthDate\":\"Mar 3, 2002\",\"studentEmail\":\"eric@student.dkit.ie\",\"studentPhone\":\"353 864099121\",\"address\":\"22 Park Avenue, Dundalk, Louth\",\"courseFullName\":\"BSc (Hons) Mathematics\",\"courseStatus\":\"part-time\",\"hasPaidFullFee\":false,\"classGroup\":\"MA1B\",\"graduationYear\":2029,\"currentGPA\":3.2},{\"id\":4,\"firstName\":\"Issac\",\"lastName\":\"Davis\",\"birthDate\":\"Apr 4, 2003\",\"studentEmail\":\"issac@student.dkit.ie\",\"studentPhone\":\"353 864099122\",\"address\":\"23 High Street, Dundalk, Louth\",\"courseFullName\":\"BSc (Hons) Physics\",\"courseStatus\":\"full-time\",\"hasPaidFullFee\":true,\"classGroup\":\"PH2A\",\"graduationYear\":2028,\"currentGPA\":3.6},{\"id\":5,\"firstName\":\"Roman\",\"lastName\":\"Brown\",\"birthDate\":\"May 5, 2004\",\"studentEmail\":\"roman@student.dkit.ie\",\"studentPhone\":\"353 864099123\",\"address\":\"24 Maple Avenue, Dundalk, Louth\",\"courseFullName\":\"BSc (Hons) Chemistry\",\"courseStatus\":\"part-time\",\"hasPaidFullFee\":false,\"classGroup\":\"CH1B\",\"graduationYear\":2027,\"currentGPA\":3.4}]";

        String actual = JSONConverter.studentsListToJson(studentList);

        assertEquals(expected, actual);
    }


}