/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.io.asset.model;

/**
 *
 * @author user
 */
public class BuildingModel {
    
    private String buildingid;
    private String buildingname;
    private String description;
    private String cityid;
    private boolean isactive;
    
    public BuildingModel (String buildingid, String buildingname, String description, String cityid, boolean isactive){
        
        this.buildingid     = buildingid;
        this.buildingname   = buildingname;
        this.description    = description;
        this.cityid         = cityid;
        this.isactive       = isactive;
    }
    
    public String getBuildingid (){
        return buildingid;
    }
    
    public void setBuildingid (String buildingid){
        this.buildingid = buildingid;
    }
    
    public String getBuildingname (){
        return buildingname;
    }
    
    public void setBbuildingname (String buildingname){
        this.buildingname = buildingname;
    }
    
    public String getDescription (){
        return description ;
    }
    
    public void setDescription (String description){
        this.description = description;
    }
    
    public String getCityid (){
        return cityid;
    }
    
    public void setCityid (String cityid){
        this.cityid = cityid;
    }
    
    public boolean isIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }
}
