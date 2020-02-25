/**
  * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
  *
  * Copyright (c) 2019 IO-Teknologi Indonesia, and individual contributors
  * as indicated by the @author tags. All Rights Reserved
  *
  * The contents of this file are subject to the terms of the
  * Common Development and Distribution License (the License).
  *
  * Everyone is permitted to copy and distribute verbatim copies
  * of this license document, but changing it is not allowed.
  *
  */
package id.io.asset.model;

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
    private String assetid;
    private String assetcode;
    private String assetname;
    private String typeid;
    private String manufacture;
    private String model;
    private String vendorid;
    private String note;
    private boolean isadmin;
    private boolean isactive;

    public UserModel() {
        
    }
    
    public UserModel(String userid, String username, String password, String alias, String memberid, String membercode, String membername, String email, String imageaddress, String description, String levelid, String departmentid, String assetid,String assetcode,String assetname,String typeid,String manufacture,String model,String vendorid,String note,boolean isadmin, boolean isactive) {
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
        this.assetid = assetid;
        this.assetcode = assetcode;
        this.assetname = assetname;
        this.typeid = typeid;
        this.manufacture = manufacture;
        this.model = model;
        this.vendorid = vendorid;
        this.note = note;
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

    public String getAssetid() {
         return assetid;
    }
    
    public void setAssetid(String assetid) {
        this.assetid = assetid;
    }

    public String getAssetcode() {
       return assetcode;
    }
    
    public void setAssetcode(String assetcode) {
        this.assetcode = assetcode;
    }

    public String getAssetname() {
        return assetname;
    }
    
    public void setAssetname(String assetname) {
        this.assetname = assetname;
    }

    public String getTypeid() {
        return typeid;
    }
    
    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    public String getManufacture() {
       return manufacture;
    }
    
    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }
    
    public String getModel() {
       return model;
    }
    
    public void setModel(String model) {
        this.model = model;
    }
    
    public String getVendorid() {
       return vendorid;
    }
    
    public void setVendorid(String vendorid) {
        this.vendorid = vendorid;
    }
    
    public String getNote() {
       return note;
    }
    
    public void setNote(String note) {
        this.note = note;
    }


}
