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

public class CityModel {
    
    private String cityid;
    private String cityname;
    private String provinceid;
    private boolean isactive;
    
    public CityModel (String cityid, String cityname,String provinceid, boolean isactive){
        this.cityid     = cityid;
        this.cityname   = cityname;
        this.provinceid = provinceid;
        this.isactive   = isactive;
    
    }
    
    public String getCityid(){
        return cityid;
    }
    
    public void setCityid(String cityid){
        this.cityid = cityid;
    }
    
    public String getCityname(){
        return cityname;
    }
    
    public void setCityname(String cityname){
        this.cityname = cityname;
    }
    
    public String getProvinceid(){
        return provinceid;
    }
    
    public void setProvinceid(String provinceid){
        this.provinceid = provinceid;
    }
    
    public boolean isIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }
}
