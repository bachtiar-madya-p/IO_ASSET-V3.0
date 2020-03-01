/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.io.asset.controller;

import id.io.asset.model.AssetRegisterModel;
import id.io.asset.util.constant.ConstantHelper;
import id.io.asset.util.database.AssetRegisterDatabaseHelper;
import id.io.asset.util.database.UUIDGeneratorHelper;
import java.util.UUID;
import org.apache.http.HttpStatus;
import org.json.JSONObject;

/**
 *
 * @author user
 */
public class AssetRegisterController extends BaseController{
    private AssetRegisterDatabaseHelper assetRegisterDatabaseHelper;
    private UUIDGeneratorHelper uuidGenerator;
    
    public AssetRegisterController(){
        assetRegisterDatabaseHelper = new AssetRegisterDatabaseHelper();
        this.uuidGenerator = new UUIDGeneratorHelper();
    }
    //create
    public JSONObject create(JSONObject json){
        JSONObject response = new JSONObject();
        if(json.length() != 0){
            UUID uuid = uuidGenerator.generateUUID(json.getString(ConstantHelper.ASSETREGISTER_ASSETCODE));
            boolean codeValidate = assetRegisterDatabaseHelper.assetRegisterCodeValidity(json.getString(ConstantHelper.ASSETREGISTER_ASSETCODE));
            if(codeValidate){
                AssetRegisterModel model = new AssetRegisterModel();
                model.setAssetid(uuid.toString());
                model.setAssetcode(json.getString(ConstantHelper.ASSETREGISTER_ASSETCODE));
                model.setAssetname(json.getString(ConstantHelper.ASSETREGISTER_ASSETNAME));
                model.setTypeid(json.getString(ConstantHelper.ASSETREGISTER_TYPEID));
                model.setManufacture(json.getString(ConstantHelper.ASSETREGISTER_MANUFACTURE));
                model.setModel(json.getString(ConstantHelper.ASSETREGISTER_MODEL));
                model.setVendorid(json.getString(ConstantHelper.ASSET_VENDORID));
                model.setNote(json.getString(ConstantHelper.ASSETREGISTER_NOTE));
                
                assetRegisterDatabaseHelper.create(model);
                
                response .put(ConstantHelper.HTTP_CODE, HttpStatus.SC_OK);
                response.put(ConstantHelper.HTTP_REASON, "create_asset_register_successful");
                response.put(ConstantHelper.HTTP_MESSAGE, "Create Asset Register Successful!");
            }else{
                response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_BAD_REQUEST);
                response.put(ConstantHelper.HTTP_REASON, "duplicate_assetid");
                response.put(ConstantHelper.HTTP_MESSAGE, "Error Duplicate AssetId " + codeValidate);
            }
        }else{
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_BAD_REQUEST);
            response.put(ConstantHelper.HTTP_REASON, "error_create_asset_register");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Create Asset Register : No such Asset");
        }
        return response;
    }
    //update
    public JSONObject update(String assetId, JSONObject json) {
        JSONObject response = new JSONObject();
        if (json.length() != 0) {
            AssetRegisterModel model = new AssetRegisterModel();
            model.setAssetcode(json.getString(ConstantHelper.ASSETREGISTER_ASSETCODE));
            model.setAssetname(json.getString(ConstantHelper.ASSETREGISTER_ASSETNAME));
            model.setTypeid(json.getString(ConstantHelper.ASSETREGISTER_TYPEID));
            model.setManufacture(json.getString(ConstantHelper.ASSETREGISTER_MANUFACTURE));
            model.setModel(json.getString(ConstantHelper.ASSETREGISTER_MODEL));
            model.setVendorid(json.getString(ConstantHelper.ASSETREGISTER_VENDORID));
            model.setNote(json.getString(ConstantHelper.ASSETREGISTER_NOTE));
            assetRegisterDatabaseHelper.update(assetId, model);
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_OK);
            response.put(ConstantHelper.HTTP_REASON, "update_asset_register_successful");
            response.put(ConstantHelper.HTTP_MESSAGE, "Update Asset Register Successful!");
        } else {
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_BAD_REQUEST);
            response.put(ConstantHelper.HTTP_REASON, "error_update_asset_Register");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Update Asset Register : No such Asset");
        }
        return response;
    }
    //delete
    public JSONObject delete(String assetId) {
        JSONObject json = new JSONObject();
        int result = assetRegisterDatabaseHelper.delete(assetId);
        if (result == 1) {
            json.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_OK);
            json.put(ConstantHelper.HTTP_REASON, "delete_asset_register_successful");
            json.put(ConstantHelper.HTTP_MESSAGE, "Delete Asset Register Successful!");
        } else {
            json.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_BAD_REQUEST);
            json.put(ConstantHelper.HTTP_REASON, "error_delete_asset_register");
            json.put(ConstantHelper.HTTP_MESSAGE, "Error Delete Asset Register");
        }
        return json;
    }
}
