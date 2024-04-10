package com.dkit.oop.sd2.DTOs;

public class Module {
    private int moduleID;
    private String moduleName;
    private int credits;

    public Module() {
    }

    public Module(int moduleID, String moduleName, int credits) {
        this.moduleID = moduleID;
        this.moduleName = moduleName;
        this.credits = credits;
    }

    public Module(String moduleName, int credits) {
        this.moduleName = moduleName;
        this.credits = credits;
    }
    public int getModuleID() {
        return moduleID;
    }

    public void setModuleID(int moduleID) {
        this.moduleID = moduleID;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    @Override
    public String toString() {
        return "Module{" +
                "moduleID=" + moduleID +
                ", moduleName='" + moduleName + '\'' +
                ", credits=" + credits +
                '}';
    }
}
