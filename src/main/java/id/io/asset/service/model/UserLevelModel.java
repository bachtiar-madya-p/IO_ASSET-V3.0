/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.io.asset.service.model;

/**
 *
 * @author permadi
 */
public class UserLevelModel {
    
    private String levelid;
    private String levelCode;
    private String levelName;
    private String description;
    private boolean isActive;

    public UserLevelModel() {
    }

    public UserLevelModel(String levelid, String levelCode, String levelName, String description, boolean isActive) {
        this.levelid = levelid;
        this.levelCode = levelCode;
        this.levelName = levelName;
        this.description = description;
        this.isActive = isActive;
    }

    public String getLevelid() {
        return levelid;
    }

    public void setLevelid(String levelid) {
        this.levelid = levelid;
    }

    public String getLevelCode() {
        return levelCode;
    }

    public void setLevelCode(String levelCode) {
        this.levelCode = levelCode;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    
}
