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
package id.io.asset.service;

import id.io.asset.controller.OtpController;
import id.io.asset.controller.UserController;
import id.io.asset.util.constant.ConstantHelper;
import id.io.asset.util.database.ConfigurationDatabaseHelper;
import javax.ws.rs.core.Response;
import org.json.JSONObject;

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
