/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.io.asset.util.database;

import id.io.asset.model.AssetModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.jdbi.v3.core.Handle;

/**
 *
 * @author user
 */
public class AssetDbHelper extends BaseDatabaseHelper{
    
    public AssetDbHelper(){
        log = getLogger(this.getClass());
    }
    
    //create
    
    public int create(AssetModel asset) {
        log.debug(AssetDbHelper.class.getName(), "- createAsset");
         
        final String sql = "INSERT INTO asset_master (assetid, assetcode, assetname, typeid, manufacture, model, vendorid, note) VALUES(:assetid, :assetcode, :assetname, :typeid, :manufacture, :model, :vendorid, :note);";
        int row = 0;
        try (Handle handle = getHandle()) {

            row = handle.createUpdate(sql)
                    .bind("assetid", asset.getAssetid())
                    .bind("assetcode", asset.getAssetcode())
                    .bind("assetname", asset.getAssetname())
                    .bind("typeid", asset.getTypeid())
                    .bind("manufacture", asset.getManufacture())
                    .bind("model", asset.getModel())
                    .bind("vendorid", asset.getVendorid())
                    .bind("note", asset.getNote()).execute();

        } catch (SQLException ex) {
            log.error(AssetDbHelper.class.getName(), " - errorCreateAsset " + ex);
        }
        return row;
    }
    
    
    
    //update
    public int update(String assetId, AssetModel asset) {
        log.debug(AssetDbHelper.class.getName(), "- updateAsset");

        final String sql = "UPDATE asset_master SET  assetname= :assetname, typeid= :typeid, manufacture= :manufacture, model= :model, vendorid= :vendorid, note= :note WHERE assetid= :assetid;";
        int row = 0;
        try (Handle handle = getHandle()) {

            row = handle.createUpdate(sql)
                    .bind("assetid", assetId)
                    .bind("assetname", asset.getAssetname())
                    .bind("typeid", asset.getTypeid())
                    .bind("manufacture", asset.getManufacture())
                    .bind("model", asset.getModel())
                    .bind("vendorid", asset.getVendorid())
                    .bind("note", asset.getNote()).execute();

        } catch (SQLException ex) {
            log.error(AssetDbHelper.class.getName(), " - errorUpdateAsset " + ex);
        }
        return row;
    }
    //delete
    public int delete(String assetId) {
        log.debug(AssetDbHelper.class.getName(), "- deleteAsset");

        final String sql = "DELETE FROM asset_master WHERE assetid = :assetid;";
        int result = 0;
        try (Handle handle = getHandle()) {

            result = handle.createUpdate(sql).bind("assetid", assetId).execute();

        } catch (SQLException ex) {
            log.error(AssetDbHelper.class.getName(), " - errorDeleteAsset " + ex);
        }
        return result;
    }
    
     public AssetModel findById(String assetId) {
        AssetModel asset = new AssetModel();

        log.debug(MemberLevelDatabaseHelper.class.getName(), " - getListUserLevel");

        final String sql = "SELECT assetid, assetcode, assetname, typeid, manufacture, model, vendorid, note, IF(createdt, 'true', 'false') createdt FROM asset_master WHERE assetid = :assetid;";

        try (Handle h = getHandle()) {
            asset = h.createQuery(sql).bind("assetid", assetId).mapToBean(AssetModel.class).first();
        } catch (Exception ex) {
            log.error(MemberLevelDatabaseHelper.class.getName(), " - errorGetListUserLevel " + ex);
        }
        return asset;

    }
    
    //list
    
    public List<AssetModel> getList() {
        List<AssetModel> assetList = new ArrayList<>();

        log.debug(MemberLevelDatabaseHelper.class.getName(), " - getListAsset");

        final String sql = "SELECT assetid, assetcode, assetname, typeid, manufacture, model, vendorid, note, IF(createdt, 'true', 'false') createdt FROM asset_master;";

        try (Handle h = getHandle()) {
            assetList = h.createQuery(sql).mapToBean(AssetModel.class).list();
        } catch (Exception ex) {
            log.error(MemberLevelDatabaseHelper.class.getName(), " - errorGetListAsset " + ex);
        }
        return assetList;
    }

}