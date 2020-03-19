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

public class ProvinceModel {
    private String provinceid;
    private String provincecode;
    private String provincename;
    private boolean isactive;
    
    public ProvinceModel (String provinceid, String provincecode,String provincename, boolean isactive){
        this.provinceid     = provinceid;
        this.provincecode   = provincecode;
        this.provincename   = provincename;
        this.isactive       = isactive;
    
    }
    
    public String getProvinceid(){
        return provinceid;
    }
    
    public void setProvinceid(String provinceid){
        this.provinceid = provinceid;
    }
    
    public String getProvincecode(){
        return provincecode;
    }
    
    public void setProvincecode(String provincecode){
        this.provincecode = provincecode;
    }
    
    public String getProviincename(){
        return provincename;
    }
    
    public void setProvincename(String provincename){
        this.provincename = provincename;
    }
    
    public boolean isIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }
}
