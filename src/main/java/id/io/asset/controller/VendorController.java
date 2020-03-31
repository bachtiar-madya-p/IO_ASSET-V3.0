/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.io.asset.controller;

import id.io.asset.model.VendorModel;
import id.io.asset.util.constant.ConstantHelper;
import id.io.asset.util.database.UUIDGeneratorHelper;
import id.io.asset.util.database.VendorDatabaseHelper;
import java.util.List;
import java.util.UUID;
import org.apache.http.HttpStatus;
import org.json.JSONObject;

/**
 *
 * @author user
 */
public class VendorController extends BaseController{
    private VendorDatabaseHelper vendorDatabaseHelper;
    private UUIDGeneratorHelper uuidGenerator;
    
    public VendorController(){
        vendorDatabaseHelper = new VendorDatabaseHelper();
        this.uuidGenerator = new UUIDGeneratorHelper();
    }
    //create
    public JSONObject create(JSONObject json){
        JSONObject response = new JSONObject();
        if(json.length() != 0){
            UUID uuid = uuidGenerator.generateUUID(json.getString(ConstantHelper.VENDOR_VENDORCODE));
            boolean codeValidate = vendorDatabaseHelper.vendorCodeValidity(json.getString(ConstantHelper.VENDOR_VENDORCODE));
            if(codeValidate){
                VendorModel model = new VendorModel();
                model.setVendorid(uuid.toString());
                model.setVendorcode(json.getString(ConstantHelper.VENDOR_VENDORCODE));
                model.setVendorname(json.getString(ConstantHelper.VENDOR_VENDORNAME));
                model.setContact(json.getString(ConstantHelper.VENDOR_CONTACT));
                model.setEmail(json.getString(ConstantHelper.VENDOR_EMAIL));
                model.setAddress(json.getString(ConstantHelper.VENDOR_ADDRESS));
                model.setNote(json.getString(ConstantHelper.VENDOR_NOTE));
                model.setRate(json.getString(ConstantHelper.VENDOR_RATE));
                
                vendorDatabaseHelper.create(model);
                
                response .put(ConstantHelper.HTTP_CODE, HttpStatus.SC_OK);
                response.put(ConstantHelper.HTTP_REASON, "create_vendor_successful");
                response.put(ConstantHelper.HTTP_MESSAGE, "Create Vendor Successful!");
            }else{
                response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_BAD_REQUEST);
                response.put(ConstantHelper.HTTP_REASON, "duplicate_vendorid");
                response.put(ConstantHelper.HTTP_MESSAGE, "Error Duplicate VendorId " + codeValidate);
            }
        }else{
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_BAD_REQUEST);
            response.put(ConstantHelper.HTTP_REASON, "error_create_vendor");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Create Vendor : No such Vendor");
        }
        return response;
    }
    
    //update
    public JSONObject update(String vendorId, JSONObject json) {
        JSONObject response = new JSONObject();
        if (json.length() != 0) {
            VendorModel model = new VendorModel();
            model.setVendorcode(json.getString(ConstantHelper.VENDOR_VENDORCODE));
            model.setVendorname(json.getString(ConstantHelper.VENDOR_VENDORNAME));
            model.setContact(json.getString(ConstantHelper.VENDOR_CONTACT));
            model.setEmail(json.getString(ConstantHelper.VENDOR_EMAIL));
            model.setAddress(json.getString(ConstantHelper.VENDOR_ADDRESS));
            model.setNote(json.getString(ConstantHelper.VENDOR_NOTE));
            model.setRate(json.getString(ConstantHelper.VENDOR_RATE));
 
            vendorDatabaseHelper.update(vendorId, model);
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_OK);
            response.put(ConstantHelper.HTTP_REASON, "update_vendor_successful");
            response.put(ConstantHelper.HTTP_MESSAGE, "Update Vendor Successful!");
        } else {
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_BAD_REQUEST);
            response.put(ConstantHelper.HTTP_REASON, "error_update_vendor");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Update Vendor : No such Vendor");
        }
        return response;
    }
    
    //delete
    public JSONObject delete(String vendorId) {
        JSONObject json = new JSONObject();
        int result = vendorDatabaseHelper.delete(vendorId);
        if (result == 1) {
            json.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_OK);
            json.put(ConstantHelper.HTTP_REASON, "delete_vendor_successful");
            json.put(ConstantHelper.HTTP_MESSAGE, "Delete Vendor Successful!");
        } else {
            json.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_BAD_REQUEST);
            json.put(ConstantHelper.HTTP_REASON, "error_delete_vendor");
            json.put(ConstantHelper.HTTP_MESSAGE, "Error Delete Vendor");
        }
        return json;
    }
    
    //list
    public List<VendorModel> vendorList() {
        List<VendorModel> vendorList = vendorDatabaseHelper.getList();
        return vendorList;
    }
    
    //findById
    public VendorModel getVendor(String vendorId) {
        VendorModel vendor = vendorDatabaseHelper.findById(vendorId);
        return vendor;
    }
}
