/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.io.asset.controller;

import id.io.asset.model.BuildingModel;
import id.io.asset.util.constant.ConstantHelper;
import id.io.asset.util.database.BuildingDatabaseHelper;
import id.io.asset.util.database.UUIDGeneratorHelper;
import java.util.UUID;
import org.apache.http.HttpStatus;
import org.json.JSONObject;

/**
 *
 * @author user
 */
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
            model.setBbuildingname(json.getString(ConstantHelper.BUILDING_BUILDINGNAME));
            model.setDescription(json.getString(ConstantHelper.BUILDING_DESCRIPTION));
            model.setCityid(json.getString(ConstantHelper.BUILDING_CITYID));
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
}
