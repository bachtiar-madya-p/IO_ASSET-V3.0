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

import id.io.asset.manager.EncryptionManager;
import id.io.asset.model.UserModel;
import id.io.asset.util.constant.ConstantHelper;
import id.io.asset.util.database.UserDatabaseHelper;
import java.sql.SQLException;
import org.apache.http.HttpStatus;
import org.json.JSONObject;

public class AuthenticationController extends BaseController {

    private UserDatabaseHelper userDatabaseHelper;

    public AuthenticationController() {
        log = getLogger(this.getClass());
        this.userDatabaseHelper = new UserDatabaseHelper();
    }

    public JSONObject authenticate(JSONObject jsonRequest) {
        JSONObject json = new JSONObject();

        String decrypPassword = EncryptionManager.encrypt(jsonRequest.getString("password"));

        UserModel user = new UserModel();
        try {
            user = userDatabaseHelper.login(jsonRequest.getString("username"), decrypPassword);
            json.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_OK);
            json.put(ConstantHelper.USER_USERID, user.getUserid());
            json.put(ConstantHelper.USER_USERNAME, user.getUsername());
            json.put(ConstantHelper.USER_MEMBERID, user.getMemberid());
            json.put(ConstantHelper.USER_MEMBERCODE, user.getMembercode());
            json.put(ConstantHelper.USER_MEMBERNAME, user.getMembername());
            json.put(ConstantHelper.USER_EMAIL, user.getEmail());
            json.put(ConstantHelper.USER_IMAGEADDRESSES, user.getImageaddress());
            json.put(ConstantHelper.USER_LEVELID, user.getLevelid());
            json.put(ConstantHelper.USER_DEPARTMENTID, user.getDepartmentid());
            json.put(ConstantHelper.USER_DESCRIPTION, user.getDescription());
            json.put(ConstantHelper.USER_ISADMIN, user.isIsadmin());
        } catch (Exception ex) {
            json.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_UNAUTHORIZED);
            json.put(ConstantHelper.HTTP_REASON, "wrong_username_or_password");
            json.put(ConstantHelper.HTTP_MESSAGE, "Wrong Username or Password");
        }

        return json;
    }

    public JSONObject createPassword(JSONObject jsonRequest) {

        JSONObject json = new JSONObject();

        String username = jsonRequest.getString("username");
        String password = jsonRequest.getString("password");

        UserModel user = userDatabaseHelper.getByUsername(username);

        if (user.getUserid() != null) {
            String encryptedPassword = EncryptionManager.encrypt(password);
            userDatabaseHelper.changePassword(username, encryptedPassword);

            json.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_OK);
            json.put(ConstantHelper.HTTP_REASON, "create_passwordt_successful");
            json.put(ConstantHelper.HTTP_MESSAGE, "Create Password Successful!");

        } else {
            json.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_BAD_REQUEST);
            json.put(ConstantHelper.HTTP_REASON, "error_create_passwoed");
            json.put(ConstantHelper.HTTP_MESSAGE, "Error Create Password");
        }
        return json;
    }
}
