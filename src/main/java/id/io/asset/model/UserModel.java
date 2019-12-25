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
public class UserModel {

    private String userid;
    private String username;
    private String password;
    private String alias;
    private String memberid;
    private String membercode;
    private String membername;
    private String email;
    private String imageaddress;
    private String description;
    private String levelid;
    private String departmentid;
    private boolean isadmin;
    private boolean isactive;

    public UserModel() {
    }

    public UserModel(String userid, String username, String password, String alias, String memberid, String membercode, String membername, String email, String imageaddress, String description, String levelid, String departmentid, boolean isadmin, boolean isactive) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.alias = alias;
        this.memberid = memberid;
        this.membercode = membercode;
        this.membername = membername;
        this.email = email;
        this.imageaddress = imageaddress;
        this.description = description;
        this.levelid = levelid;
        this.departmentid = departmentid;
        this.isadmin = isadmin;
        this.isactive = isactive;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }

    public String getMembercode() {
        return membercode;
    }

    public void setMembercode(String membercode) {
        this.membercode = membercode;
    }

    public String getMembername() {
        return membername;
    }

    public void setMembername(String membername) {
        this.membername = membername;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageaddress() {
        return imageaddress;
    }

    public void setImageaddress(String imageaddress) {
        this.imageaddress = imageaddress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLevelid() {
        return levelid;
    }

    public void setLevelid(String levelid) {
        this.levelid = levelid;
    }

    public String getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid;
    }

    public boolean isIsadmin() {
        return isadmin;
    }

    public void setIsadmin(boolean isadmin) {
        this.isadmin = isadmin;
    }

    public boolean isIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }

}
