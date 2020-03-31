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

import id.io.asset.model.BuildingModel;
import id.io.asset.util.constant.ConstantHelper;
import id.io.asset.util.database.BuildingDatabaseHelper;
import id.io.asset.util.database.UUIDGeneratorHelper;
import java.util.List;
import java.util.UUID;
import org.apache.http.HttpStatus;
import org.json.JSONObject;

public class BuildingController extends BaseController {
    
    private BuildingDatabaseHelper buildingDatabaseHelper;
    private UUIDGeneratorHelper uuidGenerator;
    
    public BuildingController (){
        buildingDatabaseHelper = new BuildingDatabaseHelper();
        this.uuidGenerator = new UUIDGeneratorHelper();
    }
    
    //create
    public JSONObject create (JSONObject json) {
        
        JSONObject response = new JSONObject();
        if(json.length() != 0){
            UUID uuid = uuidGenerator.generateUUID(json.getString(ConstantHelper.BUILDING_BUILDINGNAME));
            BuildingModel model = new BuildingModel();
            model.setBuildingid(uuid.toString());
            model.setBuildingname(json.getString(ConstantHelper.BUILDING_BUILDINGNAME));
            model.setDescription(json.getString(ConstantHelper.BUILDING_DESCRIPTION));
            model.setCityid(json.getString(ConstantHelper.CITY_CITYID));
            buildingDatabaseHelper.create(model);
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_OK);
            response.put(ConstantHelper.HTTP_REASON, "create_bulding_successful");
            response.put(ConstantHelper.HTTP_MESSAGE, "Create Building Successful!");
        } else {
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_BAD_REQUEST);
            response.put(ConstantHelper.HTTP_REASON, "error_create_building");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Create Building : No such Building");
        }
        return response;
    }
    
    //delete
    public JSONObject delete(String buildingId) {
        JSONObject json = new JSONObject();
        int result = buildingDatabaseHelper.delete(buildingId);
        if (result == 1) {
            json.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_OK);
            json.put(ConstantHelper.HTTP_REASON, "delete_building_successful");
            json.put(ConstantHelper.HTTP_MESSAGE, "Delete Building Successful!");
        } else {
            json.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_BAD_REQUEST);
            json.put(ConstantHelper.HTTP_REASON, "error_delete_building");
            json.put(ConstantHelper.HTTP_MESSAGE, "Error Delete Building");
        }
        return json;
    }
    
    //update
    public JSONObject update(String buildingId, JSONObject json) {
        JSONObject response = new JSONObject();
        if (json.length() != 0) {
            BuildingModel model = new BuildingModel();
            model.setBuildingname(json.getString(ConstantHelper.BUILDING_BUILDINGNAME));
            model.setDescription(json.getString(ConstantHelper.BUILDING_DESCRIPTION));
            model.setCityid(json.getString(ConstantHelper.BUILDING_CITYID));
            buildingDatabaseHelper.update(buildingId, model);
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_OK);
            response.put(ConstantHelper.HTTP_REASON, "update_building_successful");
            response.put(ConstantHelper.HTTP_MESSAGE, "Update Building Successful!");
        } else {
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_BAD_REQUEST);
            response.put(ConstantHelper.HTTP_REASON, "error_update_building");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Update Building : No such Building");
        }
        return response;
    }
    
    public BuildingModel getBuilding(String buildingId) {
        BuildingModel building = buildingDatabaseHelper.findById(buildingId);
        return building;
    }

    //List
    public List<BuildingModel> buildingList() {
        List<BuildingModel> buildingList = buildingDatabaseHelper.getList();
        return buildingList;
    }
    
    //activate
    public JSONObject activate(String buildingId, JSONObject json) {
        JSONObject response = new JSONObject();
        if (json.length() != 0) {
            buildingDatabaseHelper.activate(buildingId, json.getBoolean(ConstantHelper.BUILDING_ISACTIVE));
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_OK);
            response.put(ConstantHelper.HTTP_REASON, "activate/inactivate_building_successful");
            response.put(ConstantHelper.HTTP_MESSAGE, "Activate/Inactivate Building Successful!");
        } else {
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_BAD_REQUEST);
            response.put(ConstantHelper.HTTP_REASON, "error_activate/inactivate_building");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Activate/Inactivate Building : No such Building");
        }
        return response;
    }
}
