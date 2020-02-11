/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.io.asset.service;

import id.io.asset.controller.OtpController;
import id.io.asset.controller.UserController;
import id.io.asset.util.constant.ConstantHelper;
import id.io.asset.util.database.ConfigurationDatabaseHelper;
import javax.ws.rs.core.Response;
import org.json.JSONObject;

/**
 *
 * @author permadi
 */
public class OtpService extends BaseService {
    
    private OtpController otpController;
    private UserController userController;
    private ConfigurationDatabaseHelper configurationDatabaseHelper;

    public OtpService() {
        this.otpController = new OtpController();
        this.userController = new UserController();
        this.configurationDatabaseHelper = new ConfigurationDatabaseHelper();
        
    }

    
    
    
}
