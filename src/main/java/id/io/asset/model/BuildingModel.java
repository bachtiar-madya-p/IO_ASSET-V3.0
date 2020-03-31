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

    public BuildingModel() {
       
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
    
    public void setBuildingname (String buildingname){
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
