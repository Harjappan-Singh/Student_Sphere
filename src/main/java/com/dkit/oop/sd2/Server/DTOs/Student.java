package com.dkit.oop.sd2.Server.DTOs;

import java.sql.Date;

public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String studentEmail;
    private String studentPhone;
    private String address;
    private int graduationYear;
    private boolean hasPaidFullFee;
    private double currentGPA;
    private int courseId;

    public Student() {
    }

    public Student(String firstName, String lastName, Date birthDate, String studentEmail, String studentPhone, String address, int graduationYear, boolean hasPaidFullFee, double currentGPA, int courseId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.studentEmail = studentEmail;
        this.studentPhone = studentPhone;
        this.address = address;
        this.graduationYear = graduationYear;
        this.hasPaidFullFee = hasPaidFullFee;
        this.currentGPA = currentGPA;
        this.courseId = courseId;
    }

    public Student(int id, String firstName, String lastName, Date birthDate, String studentEmail, String studentPhone, String address, int graduationYear, boolean hasPaidFullFee, double currentGPA, int courseId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.studentEmail = studentEmail;
        this.studentPhone = studentPhone;
        this.address = address;
        this.graduationYear = graduationYear;
        this.hasPaidFullFee = hasPaidFullFee;
        this.currentGPA = currentGPA;
        this.courseId = courseId;
    }

    public int getId() {
        return id;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
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

    public int getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(int graduationYear) {
        this.graduationYear = graduationYear;
    }

    public boolean isHasPaidFullFee() {
        return hasPaidFullFee;
    }

    public void setHasPaidFullFee(boolean hasPaidFullFee) {
        this.hasPaidFullFee = hasPaidFullFee;
    }

    public double getCurrentGPA() {
        return currentGPA;
    }

    public void setCurrentGPA(double currentGPA) {
        this.currentGPA = currentGPA;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
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
                ", graduationYear=" + graduationYear +
                ", hasPaidFullFee=" + hasPaidFullFee +
                ", currentGPA=" + currentGPA +
                ", courseId=" + courseId +
                '}';
    }
}
