package com.dkit.oop.sd2.Server.DTOs;

public class CourseModule {
    private int courseModuleID;
    private int courseID;
    private int moduleID;

    public CourseModule() {
    }

    public CourseModule(int courseModuleID, int courseID, int moduleID) {
        this.courseModuleID = courseModuleID;
        this.courseID = courseID;
        this.moduleID = moduleID;
    }

    public int getCourseModuleID() {
        return courseModuleID;
    }

    public void setCourseModuleID(int courseModuleID) {
        this.courseModuleID = courseModuleID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getModuleID() {
        return moduleID;
    }

    public void setModuleID(int moduleID) {
        this.moduleID = moduleID;
    }

    @Override
    public String toString() {
        return "CourseModule{" +
                "courseModuleID=" + courseModuleID +
                ", courseID=" + courseID +
                ", moduleID=" + moduleID +
                '}';
    }
}
