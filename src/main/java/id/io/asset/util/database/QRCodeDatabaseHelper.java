/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.io.asset.util.database;

import id.io.asset.model.AssetModel;
import id.io.asset.util.constant.ConstantHelper;
import java.util.ArrayList;
import java.util.List;
import org.jdbi.v3.core.Handle;

/**
 *
 * @author user
 */
public class QRCodeDatabaseHelper extends BaseDatabaseHelper{
    
    public QRCodeDatabaseHelper(){
        log = getLogger(this.getClass());
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
    
}