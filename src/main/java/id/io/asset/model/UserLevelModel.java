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
public class UserLevelModel {
    
    private String levelid;
    private String levelcode;
    private String levelname;
    private String description;
    private boolean isactive;

    public UserLevelModel() {
    }

    public UserLevelModel(String levelid, String levelcode, String levelname, String description, boolean isactive) {
        this.levelid = levelid;
        this.levelcode = levelcode;
        this.levelname = levelname;
        this.description = description;
        this.isactive = isactive;
    }

    public String getLevelid() {
        return levelid;
    }

    public void setLevelid(String levelid) {
        this.levelid = levelid;
    }

    public String getLevelcode() {
        return levelcode;
    }

    public void setLevelcode(String levelcode) {
        this.levelcode = levelcode;
    }

    public String getLevelname() {
        return levelname;
    }

    public void setLevelname(String levelname) {
        this.levelname = levelname;
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
