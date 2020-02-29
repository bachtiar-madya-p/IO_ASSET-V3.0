/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.io.asset.util.database;

import id.io.asset.model.AssetRegisterModel;
import id.io.asset.util.constant.ConstantHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import org.jdbi.v3.core.Handle;

/**
 *
 * @author user
 */
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
    
}
