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
        
        final String sql = "INSERT INTO asset_register (assetid, assetcode, assetname, typeid, manufacture, model, vendorid, note) VALUES (:assetid, :assetcode, :assetname, :typeid, :manufacture, :model, :vendorid, :note);";
        int row = 0;
        try(Handle handle = getHandle()){
            row  = handle.createUpdate(sql)
                    .bind(ConstantHelper.ASSETREGISTER_ASSETID,assetregister.getAssetid())
                    .bind(ConstantHelper.ASSETREGISTER_ASSETCODE, assetregister.getAssetcode())
                    .bind(ConstantHelper.ASSETREGISTER_ASSETNAME, assetregister.getAssetname())
                    .bind(ConstantHelper.ASSETREGISTER_TYPEID, assetregister.getTypeid())
                    .bind(ConstantHelper.ASSETREGISTER_MANUFACTURE, assetregister.getManufacture())
                    .bind(ConstantHelper.ASSETREGISTER_MODEL, assetregister.getModel())
                    .bind(ConstantHelper.ASSETREGISTER_VENDORID, assetregister.getVendorid())
                    .bind(ConstantHelper.ASSETREGISTER_NOTE, assetregister.getNote()).execute();
            
        }catch(SQLException ex){
            log.error(AssetRegisterDatabaseHelper.class.getName(), "- errorCreateAssetRegister" + ex);
        }
        return row;
    }
    //update
    public int update(String assetId, AssetRegisterModel assetregister){
        log.debug(AssetRegisterDatabaseHelper.class.getName(),"- updateAssetRegister");
        
        final String sql = "UPDATE asset_register SET assetname= :assetname, typeid= :typeid, manufacture= :manufacture, model= :model, vendorid= :vendorid, note= :note WHERE assetid= :asstid;";
        int row = 0;
        try(Handle handle = getHandle()){
            row = handle.createUpdate(sql)
                    .bind(ConstantHelper.ASSETREGISTER_ASSETID, assetId)
                    .bind(ConstantHelper.ASSETREGISTER_ASSETNAME, assetregister.getAssetname())
                    .bind(ConstantHelper.ASSET_TYPEID, assetregister.getTypeid())
                    .bind(ConstantHelper.ASSET_MANUFACTURE, assetregister.getManufacture())
                    .bind(ConstantHelper.ASSET_MODEL, assetregister.getModel())
                    .bind(ConstantHelper.ASSET_VENDORID, assetregister.getVendorid())
                    .bind(ConstantHelper.ASSET_NOTE, assetregister.getNote()).execute();
        }catch(SQLException ex){
            log.error(AssetRegisterDatabaseHelper.class.getName(),"- errorUpdateAssetRegister" + ex);
        }
        return row;
    }
    //delete 
    public int delete(String assetId){
        log.debug(AssetRegisterDatabaseHelper.class.getName(),"- deleteAssetRegister");
        
        final String sql = "DELETE FROM asset_register WHERE assetid = :assetid;";
        int result = 0;
        try(Handle handle = getHandle()){
            result = handle.createUpdate(sql).bind(ConstantHelper.ASSETREGISTER_ASSETID, assetId).execute();
                    
        }catch(SQLException ex){
            log.error(AssetRegisterDatabaseHelper.class.getName()," - errorDeleteAssetRegister ");
        }
        return result;
    }
    
    
}
