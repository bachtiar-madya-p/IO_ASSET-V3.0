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

public class AssetRegisterModel {
    
    private String assetid;
    private String locationname;
    private String assetcode;
    private String buildingname;
    private String membercode;
    private String rateid;
    private String geolocation;
    private String photo;
    private String note;
    private String createdt;
    
    public AssetRegisterModel(String assetid, String locationname, String assetcode, String buildingname, String membercode, String rateid, String geolocation, String photo, String note, String createdt){
        this.assetid        = assetid;
        this.locationname   = locationname;
        this.assetcode      = assetcode;
        this.buildingname   = buildingname;
        this.membercode     = membercode;
        this.rateid         = rateid;
        this.geolocation    = geolocation;
        this.photo          = photo;
        this.note           = note;
        this.createdt       = createdt;
    }

    public AssetRegisterModel() {
        
    }
    
    public String getAssetid() {
        return assetid;
    }
    
    public void setAssetid(String assetid){
        this.assetid = assetid;
    }
    
    public String getLocationname(){
        return locationname;
    }
    
    public void setLocationname(String locationname){
        this.locationname = locationname;
    }
    
    public String getAssetcode(){
        return assetcode;
    }
    
    public void setAssetcode(String assetcode){
        this.assetcode = assetcode;
    }
    
    public String getBuildingname(){
        return buildingname;
    }
    
    public void setBuildingname(String buildingname){
        this.buildingname = buildingname;
    }
    
    public String getMembercode(){
        return membercode;
    }
    
    public void setMembercode(String membercode){
        this.membercode = membercode;
    }
    
    public String getRateid(){
        return rateid;
    }
    
    public void setRateid(String rateid){
        this.rateid = rateid;
    }
    
    public String getGeolocation(){
        return geolocation;
    }
    
    public void setGeolocation(String geolocation){
        this.geolocation = geolocation;
    }
    
    public String getPhoto(){
        return photo;
    }
    
    public void setPhoto(String photo){
        this.photo = photo;
    }
    
    public String getNote(){
        return note;
    }
    
    public void setNote(String note){
         this.note = note;
    }
    
    public String getCreatedt(){
        return createdt;
    }
    
    public void setCreatedt(String createdt){
        this.createdt = createdt;
    }
}
