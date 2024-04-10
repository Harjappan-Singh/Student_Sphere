package com.dkit.oop.sd2.Server.DTOs;

public class Department {
    private int departmentID;
    private String departmentName;

    public Department() {
    }

    public Department(int departmentID, String departmentName) {
        this.departmentID = departmentID;
        this.departmentName = departmentName;
    }

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }
    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String getDepartmentName) {
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
