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
package id.io.asset.util.database;

import id.io.asset.model.AssetRegisterModel;
import id.io.asset.util.constant.ConstantHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import org.jdbi.v3.core.Handle;

public class AssetRegisterDatabaseHelper extends BaseDatabaseHelper {
    
    public AssetRegisterDatabaseHelper(){
        log = getLogger(this.getClass());
    }
    //create
    public int create(AssetRegisterModel assetregister){
        log.debug(AssetRegisterDatabaseHelper.class.getName(),"- createAssetRegister");
        
        final String sql = "INSERT INTO assetregister (assetid, locationname, assetcode, buildingname, membercode, rateid, geolocation, photo, note) VALUES (:assetid, :locationname, :assetcode, :buildingname, :membercode, :rateid, :geolocation, :photo, :note);";
        int row = 0;
        try(Handle handle = getHandle()){
            row  = handle.createUpdate(sql)
                    .bind(ConstantHelper.ASSETREGISTER_ASSETID,assetregister.getAssetid())
                    .bind(ConstantHelper.ASSETREGISTER_LOCATIONNAME, assetregister.getLocationname())
                    .bind(ConstantHelper.ASSETREGISTER_ASSETCODE, assetregister.getAssetcode())
                    .bind(ConstantHelper.ASSETREGISTER_BUILDINGNAME, assetregister.getBuildingname())
                    .bind(ConstantHelper.ASSETREGISTER_MEMBERCODE, assetregister.getMembercode())
                    .bind(ConstantHelper.ASSETREGISTER_RATEID, assetregister.getRateid())
                    .bind(ConstantHelper.ASSETREGISTER_GEOLOCATION, assetregister.getGeolocation())
                    .bind(ConstantHelper.ASSETREGISTER_PHOTO, assetregister.getPhoto())
                    .bind(ConstantHelper.ASSETREGISTER_NOTE, assetregister.getNote()).execute();
            
        }catch(SQLException ex){
            log.error(AssetRegisterDatabaseHelper.class.getName(), "- errorCreateAssetRegister" + ex);
        }
        return row;
    }
    //update
    public int update(String assetId, AssetRegisterModel assetregister){
        log.debug(AssetRegisterDatabaseHelper.class.getName(),"- updateAssetRegister");
        
        final String sql = "UPDATE assetregister SET locationname= :locationname, assetcode= :assetcode, buildingname= :buildingname, membercode= :membercode, rateid= :rateid, geolocation= :geolocation, photo= :photo, note= :note WHERE assetid= :asstid;";
        int row = 0;
        try(Handle handle = getHandle()){
            row = handle.createUpdate(sql)
                    .bind(ConstantHelper.ASSETREGISTER_ASSETID, assetId)
                    .bind(ConstantHelper.ASSETREGISTER_LOCATIONNAME, assetregister.getLocationname())
                    .bind(ConstantHelper.ASSETREGISTER_ASSETCODE, assetregister.getAssetcode())
                    .bind(ConstantHelper.ASSETREGISTER_BUILDINGNAME, assetregister.getBuildingname())
                    .bind(ConstantHelper.ASSETREGISTER_MEMBERCODE, assetregister.getMembercode())
                    .bind(ConstantHelper.ASSETREGISTER_RATEID, assetregister.getRateid())
                    .bind(ConstantHelper.ASSETREGISTER_GEOLOCATION, assetregister.getGeolocation())
                    .bind(ConstantHelper.ASSETREGISTER_PHOTO, assetregister.getPhoto())
                    .bind(ConstantHelper.ASSET_NOTE, assetregister.getNote()).execute();
        }catch(SQLException ex){
            log.error(AssetRegisterDatabaseHelper.class.getName(),"- errorUpdateAssetRegister" + ex);
        }
        return row;
    }
    //delete 
    public int delete(String assetId){
        log.debug(AssetRegisterDatabaseHelper.class.getName(),"- deleteAssetRegister");
        
        final String sql = "DELETE FROM assetregister WHERE assetid = :assetid;";
        int result = 0;
        try(Handle handle = getHandle()){
            result = handle.createUpdate(sql).bind(ConstantHelper.ASSETREGISTER_ASSETID, assetId).execute();
                    
        }catch(SQLException ex){
            log.error(AssetRegisterDatabaseHelper.class.getName()," - errorDeleteAssetRegister " + ex);
        }
        return result;
    }
    //findById
    public AssetRegisterModel findById(String assetId){
        AssetRegisterModel assetregister = new AssetRegisterModel();
        log.debug(MemberLevelDatabaseHelper.class.getName()," - getListUserLevel");
        
        final String sql = "SELECT assetid, locationname, assetcode, buildingname, membercode, rateid, geolocation, photo, note, createdt FROM assetregister WHERE assetid= :assetid;";
        try(Handle handle = getHandle()){
            assetregister = handle.createQuery(sql).bind(ConstantHelper.ASSETREGISTER_ASSETID, assetId).mapToBean(AssetRegisterModel.class).first();
        }catch(SQLException ex){
            log.error(MemberLevelDatabaseHelper.class.getName()," - errorGetListUserLevel" + ex);
        }
        return assetregister;
    }
    //list
    public List<AssetRegisterModel> getList(){
        List<AssetRegisterModel> assetRegisterList = new ArrayList<>();
        log.debug(MemberLevelDatabaseHelper.class.getName()," - getListAssetRegister");
        
        final String sql = "SELECT assetid, locationname, assetcode, buildingname, membercode, rateid, geolocation, photo, note, createdt FROM assetregister;";
        try(Handle handle = getHandle()){
            assetRegisterList = handle.createQuery(sql).mapToBean(AssetRegisterModel.class).list();
        }catch(SQLException ex){
            log.error(MemberLevelDatabaseHelper.class.getName()," - errorGetListAssetRegister"+ ex);
        }
        return assetRegisterList;
    }
    //assetRegisterCodeValidity
    public boolean assetRegisterCodeValidity(String assetCode){
        log.debug(AssetRegisterDatabaseHelper.class.getName()," - assetRegisterCodeValidity");
        boolean isValid = false;
        final String sql = "SELECT count(1) FROM assetregister WHERE assetcode= :assetcode";
        int row = 0;
        try(Handle handle = getHandle()){
            row = handle.createQuery(sql).bind(ConstantHelper.ASSETREGISTER_ASSETCODE, assetCode).mapTo(Integer.class).findOnly();
            if(row == 0){
                isValid = true;
            }
        }catch(SQLException ex){
            log.error(AssetRegisterDatabaseHelper.class.getName()," - errorAssetRegisterCodeValidity" + ex);
        }
        return isValid;
    }
}
