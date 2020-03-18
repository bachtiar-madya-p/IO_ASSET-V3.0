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

import id.io.asset.model.UserModel;
import id.io.asset.util.constant.ConstantHelper;
import id.io.asset.util.database.DepartmentMemberDatabaseHelper;
import id.io.asset.util.database.UUIDGeneratorHelper;
import id.io.asset.util.database.UserDatabaseHelper;
import java.util.List;
import java.util.UUID;
import org.apache.http.HttpStatus;
import org.json.JSONObject;

public class UserController extends BaseController {

    private UserDatabaseHelper userDatabaseHelper;
    private DepartmentMemberDatabaseHelper memberDatabaseHelper;
    private UUIDGeneratorHelper uuidGenerator;

    public UserController() {
        this.userDatabaseHelper = new UserDatabaseHelper();
        this.memberDatabaseHelper = new DepartmentMemberDatabaseHelper();
        this.uuidGenerator = new UUIDGeneratorHelper();
    }

    public List<UserModel> userList() {
        List<UserModel> list = userDatabaseHelper.list();
        return list;
    }

    public UserModel userById(String userId) {

        UserModel user = userDatabaseHelper.getById(userId);
        return user;
    }

    public boolean validateUsername(String username) {

        boolean result = false;

        boolean isValid = userDatabaseHelper.validateUsername(username);
        if (!isValid) {
            result = true;
        }
        return result;
    }

    public JSONObject validateActivation(String username) {
        JSONObject json = new JSONObject();

        boolean isValid = userDatabaseHelper.validateActivation(username);
        if (isValid) {
            json.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_OK);
            json.put(ConstantHelper.HTTP_MESSAGE, isValid);
        } else {
            json.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_BAD_REQUEST);
            json.put(ConstantHelper.HTTP_REASON, "account_inactive!!");
            json.put(ConstantHelper.HTTP_MESSAGE, isValid);
        }
        return json;
    }

    public JSONObject create(JSONObject json) {
        JSONObject response = new JSONObject();
        if (json.length() != 0) {
            UUID memberId = uuidGenerator.generateUUID(json.getString(ConstantHelper.DEPARTMENTMEMBER_MEMBERCODE));
            UUID userId = uuidGenerator.generateUUID(json.getString(ConstantHelper.USER_USERNAME));

            UserModel model = new UserModel();
            model.setUserid(userId.toString());
            model.setUsername(json.getString(ConstantHelper.USER_USERNAME));
            model.setAlias(json.getString(ConstantHelper.USER_ALIAS));
            model.setMemberid(memberId.toString());
            model.setMembercode(json.getString(ConstantHelper.DEPARTMENTMEMBER_MEMBERCODE));
            model.setMembername(json.getString(ConstantHelper.DEPARTMENTMEMBER_MEMBERNAME));
            model.setEmail(json.getString(ConstantHelper.DEPARTMENTMEMBER_EMAIL));
            model.setImageaddress(json.getString(ConstantHelper.DEPARTMENTMEMBER_IMAGEADDRESS));
            model.setDescription(json.getString(ConstantHelper.DEPARTMENTMEMBER_DESCRIPTION));
            model.setLevelid(json.getString(ConstantHelper.DEPARTMENTMEMBER_LEVELID));
            model.setDepartmentid(json.getString(ConstantHelper.DEPARTMENTMEMBER_DEPARTMENTID));

            memberDatabaseHelper.create(model);
            userDatabaseHelper.create(model);
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_OK);
            response.put(ConstantHelper.HTTP_REASON, "create_user_successful");
            response.put(ConstantHelper.HTTP_MESSAGE, "Create User Successful!");
        } else {
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_BAD_REQUEST);
            response.put(ConstantHelper.HTTP_REASON, "error_create_user");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Create User!!!");
        }
        return response;
    }
     
    public JSONObject activateUser(String userId, JSONObject json) {
        JSONObject response = new JSONObject();
        if (json.length() != 0) {

            userDatabaseHelper.activate(userId, json.getBoolean(ConstantHelper.USER_ISACTIVE));
            String memberId = userDatabaseHelper.getMemberId(userId);
            memberDatabaseHelper.activate(memberId, json.getBoolean(ConstantHelper.MEMBERLEVEL_ISACTIVE));

            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_OK);
            response.put(ConstantHelper.HTTP_REASON, "activate/inactivate_user_successful");
            response.put(ConstantHelper.HTTP_MESSAGE, "Activate/Inactivate User Successful!");
        } else {
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_BAD_REQUEST);
            response.put(ConstantHelper.HTTP_REASON, "error_activate/inactivate_user");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Activate/Inactivate User");
        }
        return response;
    }

    public JSONObject asAdmin(String userId, JSONObject json) {
        JSONObject response = new JSONObject();
        if (json.length() != 0) {

            String memberId = userDatabaseHelper.getMemberId(userId);
            memberDatabaseHelper.asAdmin(memberId, json.getBoolean(ConstantHelper.DEPARTMENTMEMBER_ISADMIN));

            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_OK);
            response.put(ConstantHelper.HTTP_REASON, "activate/inactivate_admin_successful");
            response.put(ConstantHelper.HTTP_MESSAGE, "Activate/Inactivate Admin Successful!");
        } else {
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_BAD_REQUEST);
            response.put(ConstantHelper.HTTP_REASON, "error_activate/inactivate_admin");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Activate/Inactivate Admin");
        }
        return response;
    }

    //delete
    public JSONObject remove(String userId) {
        JSONObject json = new JSONObject();
        int result = userDatabaseHelper.delete(userId);
        if (result == 1) {
            json.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_OK);
            json.put(ConstantHelper.HTTP_REASON, "delete_user_successful");
            json.put(ConstantHelper.HTTP_MESSAGE, "Delete User Successful!");
        } else {
            json.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_BAD_REQUEST);
            json.put(ConstantHelper.HTTP_REASON, "error_delete_user");
            json.put(ConstantHelper.HTTP_MESSAGE, "Error Delete User");
        }
        return json;
    }
    
    //update
    public JSONObject updateUser(String userId, JSONObject json) {
        JSONObject response = new JSONObject();
        if(json.length() != 0){
            UserModel model = new UserModel();
            model.setMembername(json.getString(ConstantHelper.DEPARTMENTMEMBER_MEMBERNAME));
            model.setEmail(json.getString(ConstantHelper.DEPARTMENTMEMBER_EMAIL));
            model.setImageaddress(json.getString(ConstantHelper.DEPARTMENTMEMBER_IMAGEADDRESS));
            model.setDescription(json.getString(ConstantHelper.DEPARTMENTMEMBER_DESCRIPTION));
            model.setLevelid(json.getString(ConstantHelper.DEPARTMENTMEMBER_LEVELID));
            model.setDepartmentid(json.getString(ConstantHelper.DEPARTMENT_DEPARTMENTID));
     
            int result = userDatabaseHelper.updateUser(userId, model);
            if (result != 0) {

                response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_OK);
                response.put(ConstantHelper.HTTP_REASON, "update_user_successful");
                response.put(ConstantHelper.HTTP_MESSAGE, "Update User Successful!");
            } else {
                response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_BAD_REQUEST);
                response.put(ConstantHelper.HTTP_REASON, "error_update_user");
                response.put(ConstantHelper.HTTP_MESSAGE, "Error Update User");
            }
        } else {
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_BAD_REQUEST);
            response.put(ConstantHelper.HTTP_REASON, "error_update_user");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Update User : No such User");
        }
        return response;
    }
}