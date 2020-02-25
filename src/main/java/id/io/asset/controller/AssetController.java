/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.io.asset.controller;

import id.io.asset.model.AssetModel;
import id.io.asset.util.constant.ConstantHelper;
import id.io.asset.util.database.AssetDatabaseHelper;
import id.io.asset.util.database.UUIDGeneratorHelper;
import java.util.List;
import java.util.UUID;
import org.apache.http.HttpStatus;
import org.json.JSONObject;

/**
 *
 * @author untung bagus purnomo
 */
public class AssetController extends BaseController{
    
    private AssetDatabaseHelper assetDbHelper;
    private UUIDGeneratorHelper uuidGenerator;
    
    public AssetController() {
        assetDbHelper = new AssetDatabaseHelper();
        this.uuidGenerator = new UUIDGeneratorHelper();
    }
    
    public List<AssetModel> assetList() {
        List<AssetModel> assetList = assetDbHelper.getList();
        return assetList;
    }
    
    public AssetModel getAsset(String assetId) {
        AssetModel asset = assetDbHelper.findById(assetId);
        return asset;
    }
    //create
    public JSONObject create(JSONObject json) {
        JSONObject response = new JSONObject();
        if (json.length() != 0) {
            UUID uuid = uuidGenerator.generateUUID(json.getString(ConstantHelper.ASSET_ASSETCODE));
            boolean codeValidate = assetDbHelper.assetCodeValidity(json.getString(ConstantHelper.ASSET_ASSETCODE));
            if (codeValidate) {
                AssetModel model = new AssetModel();
                model.setAssetid(uuid.toString());
                model.setAssetcode(json.getString(ConstantHelper.ASSET_ASSETCODE));
                model.setAssetname(json.getString(ConstantHelper.ASSET_ASSETNAME));
                model.setTypeid(json.getString(ConstantHelper.ASSET_TYPEID));
                model.setManufacture(json.getString(ConstantHelper.ASSET_MANUFACTURE));
                model.setModel(json.getString(ConstantHelper.ASSET_MODEL));
                model.setVendorid(json.getString(ConstantHelper.ASSET_VENDORID));
                model.setNote(json.getString(ConstantHelper.ASSET_NOTE));

                assetDbHelper.create(model);

                response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_OK);
                response.put(ConstantHelper.HTTP_REASON, "create_asset_successful");
                response.put(ConstantHelper.HTTP_MESSAGE, "Create Asset Successful!");
            } else {
                response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_BAD_REQUEST);
                response.put(ConstantHelper.HTTP_REASON, "duplicate_assetid");
                response.put(ConstantHelper.HTTP_MESSAGE, "Error Duplicate AssetId " + codeValidate);
            }
        } else {
           response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_BAD_REQUEST);
            response.put(ConstantHelper.HTTP_REASON, "error_create_asset");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Create Asset : No such Asset");
        }
        return response;
    }
    //update
    public JSONObject update(String assetId, JSONObject json) {
        JSONObject response = new JSONObject();
        if (json.length() != 0) {
            AssetModel model = new AssetModel();
            model.setAssetcode(json.getString(ConstantHelper.ASSET_ASSETCODE));
            model.setAssetname(json.getString(ConstantHelper.ASSET_ASSETNAME));
            model.setTypeid(json.getString(ConstantHelper.ASSET_TYPEID));
            model.setManufacture(json.getString(ConstantHelper.ASSET_MANUFACTURE));
            model.setModel(json.getString(ConstantHelper.ASSET_MODEL));
            model.setVendorid(json.getString(ConstantHelper.ASSET_VENDORID));
            model.setNote(json.getString(ConstantHelper.ASSET_NOTE));
            assetDbHelper.update(assetId, model);
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_OK);
            response.put(ConstantHelper.HTTP_REASON, "update_asset_successful");
            response.put(ConstantHelper.HTTP_MESSAGE, "Update Asset Successful!");
        } else {
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_BAD_REQUEST);
            response.put(ConstantHelper.HTTP_REASON, "error_update_asset");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Update Asset : No such Asset");
        }
        return response;
    }
    //delete
    public JSONObject delete(String assetId) {
        JSONObject json = new JSONObject();
        int result = assetDbHelper.delete(assetId);
        if (result == 1) {
            json.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_OK);
            json.put(ConstantHelper.HTTP_REASON, "delete_asset_successful");
            json.put(ConstantHelper.HTTP_MESSAGE, "Delete Asset Successful!");
        } else {
            json.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_BAD_REQUEST);
            json.put(ConstantHelper.HTTP_REASON, "error_delete_asset");
            json.put(ConstantHelper.HTTP_MESSAGE, "Error Delete Asset");
        }
        return json;
    }
}