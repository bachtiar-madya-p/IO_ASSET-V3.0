/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.io.asset.controller;

import id.io.asset.model.CityModel;
import id.io.asset.util.constant.ConstantHelper;
import id.io.asset.util.database.CityDatabaseHelper;
import id.io.asset.util.database.ProvinceDatabaseHelper;
import id.io.asset.util.database.UUIDGeneratorHelper;
import java.util.List;
import java.util.UUID;
import org.apache.http.HttpStatus;
import org.json.JSONObject;

/**
 *
 * @author user
 */
public class CityController extends BaseController {
    
    private CityDatabaseHelper citydatabasehelper;
    private UUIDGeneratorHelper uuidGenerator;
    
    public  CityController (){
        citydatabasehelper = new CityDatabaseHelper();
        this.uuidGenerator = new UUIDGeneratorHelper();
    }
    
    //create
    public JSONObject create(JSONObject json) {
        JSONObject response = new JSONObject();
        if (json.length() != 0) {
            UUID uuid = uuidGenerator.generateUUID(json.getString(ConstantHelper.CITY_CITYNAME));
            CityModel model = new CityModel();
            model.setCityid(uuid.toString());
            model.setCityname(json.getString(ConstantHelper.CITY_CITYNAME));
            model.setProvinceid(json.getString(ConstantHelper.PROVINCE_PROVINCEID));
            citydatabasehelper.create(model);
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_OK);
            response.put(ConstantHelper.HTTP_REASON, "create_city_successful");
            response.put(ConstantHelper.HTTP_MESSAGE, "Create City Successful!");
        } else {
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_BAD_REQUEST);
            response.put(ConstantHelper.HTTP_REASON, "error_create_city");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Create City : No such City");
        }
        return response;
    }
    
    //delete
    public JSONObject delete(String cityId) {
        JSONObject json = new JSONObject();
        int result = citydatabasehelper.delete(cityId);
        if (result == 1) {
            json.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_OK);
            json.put(ConstantHelper.HTTP_REASON, "delete_city_successful");
            json.put(ConstantHelper.HTTP_MESSAGE, "Delete City Successful!");
        } else {
            json.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_BAD_REQUEST);
            json.put(ConstantHelper.HTTP_REASON, "error_delete_city");
            json.put(ConstantHelper.HTTP_MESSAGE, "Error Delete City");
        }
        return json;
    }
    
    //update
    public JSONObject update(String cityId, JSONObject json) {
        JSONObject response = new JSONObject();
        if (json.length() != 0) {
            CityModel model = new CityModel();
            model.setCityname(json.getString(ConstantHelper.CITY_CITYNAME));
            model.setProvinceid(json.getString(ConstantHelper.CITY_PROVINCEID));
            citydatabasehelper.update(cityId, model);
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_OK);
            response.put(ConstantHelper.HTTP_REASON, "update_city_successful");
            response.put(ConstantHelper.HTTP_MESSAGE, "Update City Successful!");
        } else {
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_BAD_REQUEST);
            response.put(ConstantHelper.HTTP_REASON, "error_update_city");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Update City : No such City");
        }
        return response;
    }
    
    public CityModel getCity(String cityId) {
        CityModel city = citydatabasehelper.findById(cityId);
        return city;
    }

    //List
    public List<CityModel> cityList() {
        List<CityModel> cityList = citydatabasehelper.getList();
        return cityList;
    }
    
    //activate
    public JSONObject activate(String cityId, JSONObject json) {
        JSONObject response = new JSONObject();
        if (json.length() != 0) {
            citydatabasehelper.activate(cityId, json.getBoolean(ConstantHelper.CITY_ISACTIVE));
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_OK);
            response.put(ConstantHelper.HTTP_REASON, "activate/inactivate_city_successful");
            response.put(ConstantHelper.HTTP_MESSAGE, "Activate/Inactivate City Successful!");
        } else {
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_BAD_REQUEST);
            response.put(ConstantHelper.HTTP_REASON, "error_activate/inactivate_city");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Activate/Inactivate City : No such City");
        }
        return response;
    }
}

