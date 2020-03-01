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
    private String assetcode;
    private String assetname;
    private String typeid;
    private String manufacture;
    private String model;
    private String vendorid;
    private String note;
    private String createdt;
    
    public AssetRegisterModel(String assetid, String assetcode, String assetname, String typeid, String manufacture, String model, String vendorid, String note, String createdt){
        this.assetid        = assetid;
        this.assetcode      = assetcode;
        this.assetname      = assetname;
        this.typeid         = typeid;
        this.manufacture    = manufacture;
        this.model          = model;
        this.vendorid       = vendorid;
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
    
    public String getAssetcode(){
        return assetcode;
    }
    
    public void setAssetcode(String assetcode){
        this.assetcode = assetcode;
    }
    
    public String getAssetname(){
        return assetname;
    }
    
    public void setAssetname(String assetname){
        this.assetname = assetname;
    }
    
    public String getTypeid(){
        return typeid;
    }
    
    public void setTypeid(String typeid){
        this.typeid = typeid;
    }
    
    public String getManufacture(){
        return manufacture;
    }
    
    public void setManufacture(String manufacture){
        this.manufacture = manufacture;
    }
    
    public String getModel(){
        return model;
    }
    
    public void setModel(String model){
        this.model = model;
    }
    
    public String getVendorid(){
        return vendorid;
    }
    
    public void setVendorid(String vendorid){
        this.vendorid = vendorid;
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
