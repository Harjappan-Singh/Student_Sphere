package com.dkit.oop.sd2.Server.DTOs;

public class Course {
    private int courseID;
    private String courseName;
    private String courseCode;
    private int departmentID;
    private int credits;
    private String level;

    public Course() {
    }

    public Course(String courseName, String courseCode, int departmentID, int credits, String level) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.departmentID = departmentID;
        this.credits = credits;
        this.level = level;
    }

    public Course(int courseID, String courseName, String courseCode, int departmentID, int credits, String level) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.departmentID = departmentID;
        this.credits = credits;
        this.level = level;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseID=" + courseID +
                ", courseName='" + courseName + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", departmentID=" + departmentID +
                ", credits=" + credits +
                ", level='" + level + '\'' +
                '}';
    }
}
