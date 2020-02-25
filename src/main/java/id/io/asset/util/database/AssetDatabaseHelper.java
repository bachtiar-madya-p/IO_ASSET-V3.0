/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.io.asset.util.database;

import id.io.asset.model.AssetModel;
import id.io.asset.util.constant.ConstantHelper;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.jdbi.v3.core.Handle;

/**
 *
 * @author untun bagus purnomo
 */
public class AssetDatabaseHelper extends BaseDatabaseHelper{
    
    public AssetDatabaseHelper(){
        log = getLogger(this.getClass());
    }
    //create
    public int create(AssetModel asset) {
        log.debug(AssetDatabaseHelper.class.getName(), "- createAsset");
         
        final String sql = "INSERT INTO asset_master (assetid, assetcode, assetname, typeid, manufacture, model, vendorid, note) VALUES(:assetid, :assetcode, :assetname, :typeid, :manufacture, :model, :vendorid, :note);";
        int row = 0;
        try (Handle handle = getHandle()) {
            row = handle.createUpdate(sql)
                    .bind(ConstantHelper.ASSET_ID, asset.getAssetid())
                    .bind(ConstantHelper.ASSET_ASSETCODE, asset.getAssetcode())
                    .bind(ConstantHelper.ASSET_ASSETNAME, asset.getAssetname())
                    .bind(ConstantHelper.ASSET_TYPEID, asset.getTypeid())
                    .bind(ConstantHelper.ASSET_MANUFACTURE, asset.getManufacture())
                    .bind(ConstantHelper.ASSET_MODEL, asset.getModel())
                    .bind(ConstantHelper.ASSET_VENDORID, asset.getVendorid())
                    .bind(ConstantHelper.ASSET_NOTE, asset.getNote()).execute();
        } catch (SQLException ex) {
            log.error(AssetDatabaseHelper.class.getName(), " - errorCreateAsset " + ex);
        }
        return row;
    }
    //update
    public int update(String assetId, AssetModel asset) {
        log.debug(AssetDatabaseHelper.class.getName(), "- updateAsset");
        final String sql = "UPDATE asset_master SET  assetname= :assetname, typeid= :typeid, manufacture= :manufacture, model= :model, vendorid= :vendorid, note= :note WHERE assetid= :assetid;";
        int row = 0;
        try (Handle handle = getHandle()) {
            row = handle.createUpdate(sql)
                    .bind(ConstantHelper.ASSET_ID, assetId)
                    .bind(ConstantHelper.ASSET_ASSETNAME, asset.getAssetname())
                    .bind(ConstantHelper.ASSET_TYPEID, asset.getTypeid())
                    .bind(ConstantHelper.ASSET_MANUFACTURE, asset.getManufacture())
                    .bind(ConstantHelper.ASSET_MODEL, asset.getModel())
                    .bind(ConstantHelper.ASSET_VENDORID, asset.getVendorid())
                    .bind(ConstantHelper.ASSET_NOTE, asset.getNote()).execute();
        } catch (SQLException ex) {
            log.error(AssetDatabaseHelper.class.getName(), " - errorUpdateAsset " + ex);
        }
        return row;
    }
    //delete
    public int delete(String assetId) {
        log.debug(AssetDatabaseHelper.class.getName(), "- deleteAsset");
        final String sql = "DELETE FROM asset_master WHERE assetid = :assetid;";
        int result = 0;
        try (Handle handle = getHandle()) {
            result = handle.createUpdate(sql).bind(ConstantHelper.ASSET_ID, assetId).execute();
        } catch (SQLException ex) {
            log.error(AssetDatabaseHelper.class.getName(), " - errorDeleteAsset " + ex);
        }
        return result;
    }
    //findById
    public AssetModel findById(String assetId) {
        AssetModel asset = new AssetModel();
        log.debug(MemberLevelDatabaseHelper.class.getName(), " - getListUserLevel");
        final String sql = "SELECT assetid, assetcode, assetname, typeid, manufacture, model, vendorid, note, createdt FROM asset_master WHERE assetid = :assetid;";
        try (Handle h = getHandle()) {
            asset = h.createQuery(sql).bind(ConstantHelper.ASSET_ID, assetId).mapToBean(AssetModel.class).first();
        } catch (Exception ex) {
            log.error(MemberLevelDatabaseHelper.class.getName(), " - errorGetListUserLevel " + ex);
        }
        return asset;
    }
    //list
    public List<AssetModel> getList() {
        List<AssetModel> assetList = new ArrayList<>();
        log.debug(MemberLevelDatabaseHelper.class.getName(), " - getListAsset");
        final String sql = "SELECT assetid, assetcode, assetname, typeid, manufacture, model, vendorid, note, createdt FROM asset_master;";
        try (Handle h = getHandle()) {
            assetList = h.createQuery(sql).mapToBean(AssetModel.class).list();
        } catch (Exception ex) {
            log.error(MemberLevelDatabaseHelper.class.getName(), " - errorGetListAsset " + ex);
        }
        return assetList;
    }
    //assetCodeValidity
    public boolean assetCodeValidity (String assetCode) {
        log.debug(AssetDatabaseHelper.class.getName(), "- assetCodeValidity");
        boolean isValid = false;
        final String sql = "select count(1) from asset_master where assetcode= :assetCode";
        int row = 0;
        try (Handle h = getHandle()) {
            row = h.createQuery(sql).bind(ConstantHelper.ASSET_ASSETCODE, assetCode).mapTo(Integer.class).findOnly();
            if (row == 0) {
                isValid = true;
            }
        } catch (SQLException ex) {
            log.error(AssetDatabaseHelper.class.getName(), " - errorAssetCodeCek " + ex);
        }
        return isValid;
    }
}