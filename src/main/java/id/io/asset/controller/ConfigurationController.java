/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.io.asset.controller;


import id.io.asset.util.constant.ConstantHelper;
import id.io.asset.util.database.ConfigurationDatabaseHelper;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.HttpStatus;
import org.json.JSONObject;

/**
 *
 * @author permadi
 */
public class ConfigurationController extends BaseController {

    private ConfigurationDatabaseHelper configDatabaseHelper;
    
    public ConfigurationController() {
        log = getLogger(this.getClass());
        this.configDatabaseHelper = new ConfigurationDatabaseHelper();
        
    }

    public JSONObject getListConfiguration() {

        ConcurrentHashMap<String, String> configValues = configDatabaseHelper.getAllConfiguration();
        JSONObject output = new JSONObject(configValues);

        return output;
    }

//    Expected JSON Input: 
//    {
//        "IDM_SERVICE_URL":"http://<IDM_IP>:<IDM_PORT>/openidm",
//        "IDM_PWD":"openidm-admin","COOKIE_KEY":"language"
//    }
    public JSONObject createConfig(JSONObject json) {
        JSONObject response = new JSONObject();
        if (json.length() != 0) {
            Iterator<?> keys = json.keys();

            while (keys.hasNext()) {
                String key = (String) keys.next();
                configDatabaseHelper.addConfig(key, json.get(key).toString());
            }
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_OK);
            response.put(ConstantHelper.HTTP_REASON, "create_configuration_successful");
            response.put(ConstantHelper.HTTP_MESSAGE, "Create Configuration Successful!");
        } else {
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_BAD_REQUEST);
            response.put(ConstantHelper.HTTP_REASON, "error_create_configuration");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Create Configuration : No such configuration");
        }
        return response;
    }
 
    public JSONObject updateConfig(JSONObject json) {

        JSONObject response = new JSONObject();
        if (json.length() != 0) {

            Iterator<?> keys = json.keys();

            while (keys.hasNext()) {
                String key = (String) keys.next();
                configDatabaseHelper.updateConfig(key, json.get(key).toString());
            }

            ConcurrentHashMap<String, String> configValues = configDatabaseHelper.getAllConfiguration();
            JSONObject output = new JSONObject(configValues);

            return output;

        } else {

            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_BAD_REQUEST);
            response.put(ConstantHelper.HTTP_REASON, "error_update_configuration");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Update Configuration : No such configuration");

            return response;
        }
    }

    public JSONObject removeConfig(String key) {
        JSONObject json = new JSONObject();
        int result = configDatabaseHelper.removeConfig(key);
        if (result == 1) {
            json.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_OK);
            json.put(ConstantHelper.HTTP_REASON, "delete_configuration_successful");
            json.put(ConstantHelper.HTTP_MESSAGE, "Delete Configuration Successful!");

        } else {
            json.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_BAD_REQUEST);
            json.put(ConstantHelper.HTTP_REASON, "error_delete_configuration");
            json.put(ConstantHelper.HTTP_MESSAGE, "Error Delete Configuration");
        }
        return json;
    }
}
