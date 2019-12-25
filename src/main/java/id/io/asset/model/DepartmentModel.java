/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.io.asset.model;

/**
 *
 * @author permadi
 */
public class DepartmentModel {
    
    private String departmentid;
    private String departmentcode;
    private String departmentname;
    private String description;
    private boolean isactive;

    public DepartmentModel() {
    }

    public DepartmentModel(String departmentid, String departmentcode, String departmentname, String description, boolean isactive) {
        this.departmentid = departmentid;
        this.departmentcode = departmentcode;
        this.departmentname = departmentname;
        this.description = description;
        this.isactive = isactive;
    }

    public String getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid;
    }

    public String getDepartmentcode() {
        return departmentcode;
    }

    public void setDepartmentcode(String departmentcode) {
        this.departmentcode = departmentcode;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }
    
    
    
}
