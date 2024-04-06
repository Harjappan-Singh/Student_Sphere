package com.dkit.oop.sd2.DTOs;

public class Department {
    private int departmentID;
    private String departmentName;

    public Department() {
    }

    public Department(int departmentID, String departmentName) {
        this.departmentID = departmentID;
        this.departmentName = departmentName;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public String getGetDepartmentName() {
        return departmentName;
    }

    public void setGetDepartmentName(String getDepartmentName) {
        this.departmentName = getDepartmentName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentID=" + departmentID +
                ", DepartmentName='" + departmentName + '\'' +
                '}';
    }
}
