package com.dkit.oop.sd2.DTOs;

import java.time.LocalDate;

public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String studentEmail;
    private String studentPhone;
    private String address;
    private String courseFullName;
    private String courseStatus;
    private boolean hasPaidFullFee;
    private String classGroup;
    private int graduationYear;
    private double currentGPA;

    public Student(int id, String firstName, String lastName, String birthDate, String studentEmail, String studentPhone, String address, String courseFullName, String courseStatus, boolean hasPaidFullFee, String classGroup, int graduationYear, double currentGPA) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.studentEmail = studentEmail;
        this.studentPhone = studentPhone;
        this.address = address;
        this.courseFullName = courseFullName;
        this.courseStatus = courseStatus;
        this.hasPaidFullFee = hasPaidFullFee;
        this.classGroup = classGroup;
        this.graduationYear = graduationYear;
        this.currentGPA = currentGPA;
    }

    public Student(String firstName, String lastName, String birthDate, String studentEmail, String studentPhone, String address, String courseFullName, String courseStatus, boolean hasPaidFullFee, String classGroup, int graduationYear, double currentGPA) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.studentEmail = studentEmail;
        this.studentPhone = studentPhone;
        this.address = address;
        this.courseFullName = courseFullName;
        this.courseStatus = courseStatus;
        this.hasPaidFullFee = hasPaidFullFee;
        this.classGroup = classGroup;
        this.graduationYear = graduationYear;
        this.currentGPA = currentGPA;
    }

    public Student() {
    }

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCourseFullName() {
        return courseFullName;
    }

    public void setCourseFullName(String courseFullName) {
        this.courseFullName = courseFullName;
    }

    public String getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    public boolean isHasPaidFullFee() {
        return hasPaidFullFee;
    }

    public void setHasPaidFullFee(boolean hasPaidFullFee) {
        this.hasPaidFullFee = hasPaidFullFee;
    }

    public String getClassGroup() {
        return classGroup;
    }

    public void setClassGroup(String classGroup) {
        this.classGroup = classGroup;
    }

    public int getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(int graduationYear) {
        this.graduationYear = graduationYear;
    }

    public double getCurrentGPA() {
        return currentGPA;
    }

    public void setCurrentGPA(double currentGPA) {
        this.currentGPA = currentGPA;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", studentEmail='" + studentEmail + '\'' +
                ", studentPhone='" + studentPhone + '\'' +
                ", address='" + address + '\'' +
                ", courseFullName='" + courseFullName + '\'' +
                ", courseStatus='" + courseStatus + '\'' +
                ", hasPaidFullFee=" + hasPaidFullFee +
                ", classGroup='" + classGroup + '\'' +
                ", graduationYear=" + graduationYear +
                ", currentGPA=" + currentGPA +
                '}';
    }
}
