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
package id.io.asset.controller;

import id.io.asset.model.ProvinceModel;
import id.io.asset.util.constant.ConstantHelper;
import id.io.asset.util.database.ProvinceDatabaseHelper;
import id.io.asset.util.database.UUIDGeneratorHelper;
import java.util.List;
import java.util.UUID;
import org.apache.http.HttpStatus;
import org.json.JSONObject;

public class ProvinceController extends BaseController{
    private ProvinceDatabaseHelper provincedatabasehelper;
    private UUIDGeneratorHelper uuidGenerator;
    
    public ProvinceController (){
        provincedatabasehelper = new ProvinceDatabaseHelper();
        this.uuidGenerator = new UUIDGeneratorHelper();
    }
    
    //List
    public List<ProvinceModel> provinceList() {
        List<ProvinceModel> provinceList = provincedatabasehelper.getList();
        return provinceList;
    }

    public ProvinceModel getProvince(String provinceId) {
        ProvinceModel province = provincedatabasehelper.findById(provinceId);
        return province;
    }
    
    //create
    public JSONObject create(JSONObject json) {
        JSONObject response = new JSONObject();
        if (json.length() != 0) {
            UUID uuid = uuidGenerator.generateUUID(json.getString(ConstantHelper.PROVINCE_PROVINCECODE));

            ProvinceModel model = new ProvinceModel();
            model.setProvinceid(uuid.toString());
            model.setProvincecode(json.getString(ConstantHelper.PROVINCE_PROVINCECODE));
            model.setProvincename(json.getString(ConstantHelper.PROVINCE_PROVINCENAME));

            provincedatabasehelper.create(model);

            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_OK);
            response.put(ConstantHelper.HTTP_REASON, "create_province_successful");
            response.put(ConstantHelper.HTTP_MESSAGE, "Create Province Successful!");
        } else {
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_BAD_REQUEST);
            response.put(ConstantHelper.HTTP_REASON, "error_create_province");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Create Province : No such Province");
        }
        return response;
    }
    
    //update
    public JSONObject update(String provinceId, JSONObject json) {
        JSONObject response = new JSONObject();
        if (json.length() != 0) {

            ProvinceModel model = new ProvinceModel();
            model.setProvincecode(json.getString(ConstantHelper.PROVINCE_PROVINCECODE));
            model.setProvincename(json.getString(ConstantHelper.PROVINCE_PROVINCENAME));
            provincedatabasehelper.update(provinceId, model);

            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_OK);
            response.put(ConstantHelper.HTTP_REASON, "update_province_successful");
            response.put(ConstantHelper.HTTP_MESSAGE, "Update Province Successful!");
        } else {
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_BAD_REQUEST);
            response.put(ConstantHelper.HTTP_REASON, "error_update_province");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Update Province : No such Province");
        }
        return response;
    }
    
    //delete
    public JSONObject delete(String provinceId) {
        JSONObject json = new JSONObject();
        int result = provincedatabasehelper.delete(provinceId);
        if (result == 1) {
            json.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_OK);
            json.put(ConstantHelper.HTTP_REASON, "delete_province_successful");
            json.put(ConstantHelper.HTTP_MESSAGE, "Delete Province Successful!");

        } else {
            json.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_BAD_REQUEST);
            json.put(ConstantHelper.HTTP_REASON, "error_delete_province");
            json.put(ConstantHelper.HTTP_MESSAGE, "Error Delete Province");
        }
        return json;
    }
    
    //activate
    public JSONObject activate(String provinceId, JSONObject json) {
        JSONObject response = new JSONObject();
        if (json.length() != 0) {
            provincedatabasehelper.activate(provinceId, json.getBoolean(ConstantHelper.PROVINCE__ISACTIVE));

            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_OK);
            response.put(ConstantHelper.HTTP_REASON, "activate/inactivate_province_successful");
            response.put(ConstantHelper.HTTP_MESSAGE, "Activate/Inactivate Province Successful!");
        } else {
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_BAD_REQUEST);
            response.put(ConstantHelper.HTTP_REASON, "error_activate/inactivate_province");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Activate/Inactivate Province : No such Province");
        }
        return response;
    }
    
}
